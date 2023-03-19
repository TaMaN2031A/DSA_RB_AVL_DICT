package RB;

import com.company.Tree_Interface;

import java.io.FileWriter;
import java.io.IOException;

public class RbTree<T extends Comparable<? super T>> implements Tree_Interface<T> {

    String insertionTimeStr ="Hello\n", deletionTimeStr="Hello\n";
    int size = 0;
    RbNode<T> begin = new RbNode<>(null, null, null, null, 1);
    RbNode<T> root = new RbNode<>(begin, null, null, null, 0);
    RbNode<T> leaf = new RbNode<>(root, null, null, null, 0);

    public RbTree() {
        begin.right = root;
        begin.left = root;
        root.right = leaf;
        root.left = leaf;
        root.setColour(true);
        leaf.setColour(true);
    }

    public void rotateRight(RbNode<T> node) {
        RbNode<T> child = node.left;
        child.parent = node.parent;
        if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        node.left = child.right;
        child.right.parent = node;
        child.right = node;
        node.parent = child;
        node.height = Math.max(node.right.height + 1, node.left.height + 1);
        child.height = Math.max(child.left.height + 1, child.right.height + 1);
    }

    public void rotateLeft(RbNode<T> node) {
        RbNode<T> child = node.right;
        if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        node.right = child.left;
        child.left.parent = node;
        child.left = node;
        node.parent = child;
        node.height = Math.max(node.left.height + 1, node.right.height + 1);
        child.height = Math.max(child.right.height + 1, child.left.height + 1);
    }

    public RbNode<T> searchInternal(T node) {
        RbNode<T> rbNode = root, parent = root;
        while (rbNode.getValue() != null) {
            if (node.compareTo(rbNode.getValue()) > 0) {
                parent = rbNode;
                rbNode = rbNode.right;
            } else if (node.compareTo(rbNode.getValue()) < 0) {
                parent = rbNode;
                rbNode = rbNode.left;
            } else {
                break;
            }
        }
        return parent;
    }

    public boolean insertInternal(T node) {
        RbNode<T> parent = searchInternal(node);
        if (parent.right.getValue().compareTo(node) == 0 || parent.left.getValue().compareTo(node) == 0) {
            return false;
        } else if (size == 0) {
            root.setValue(node);
            size = 1;
            root.height = 1;
        } else {
            RbNode<T> rbNode = new RbNode<>(parent, leaf, leaf, node, 1);
            if (node.compareTo(parent.getValue()) > 0) parent.right = rbNode;
            else parent.left = rbNode;
            parent.height = Math.max(parent.left.height + 1, parent.right.height + 1);
            RbNode<T> ancestor;
            while (rbNode != root && parent != root) {
                ancestor = parent.parent;
                ancestor.height = Math.max(ancestor.left.height + 1, ancestor.right.height + 1);
                if (parent.isBlack()) {
                    while (rbNode != root) {
                        rbNode = rbNode.parent;
                        rbNode.height = Math.max(rbNode.left.height + 1, rbNode.right.height + 1);
                    }
                }
                else if (!ancestor.left.isBlack() && !ancestor.right.isBlack()) {
                    ancestor.right.setColour(true);
                    ancestor.left.setColour(true);
                    ancestor.setColour(false);
                    rbNode = ancestor;
                    parent = rbNode.parent;
                } else {
                    ancestor.setColour(false);
                    if (parent == ancestor.left) {
                        if (rbNode == parent.right) {
                            rbNode.setColour(true);
                            rotateLeft(parent);
                            parent = parent.parent;
                        } else {
                            parent.setColour(true);
                        }
                        rotateRight(ancestor);
                    } else {
                        if (rbNode == parent.left) {
                            rbNode.setColour(true);
                            rotateRight(parent);
                            parent = parent.parent;
                        } else {
                            parent.setColour(true);
                        }
                        rotateLeft(ancestor);
                    }
                    root = begin.left;
                    begin.right = root;
                    rbNode = parent;
                    parent = rbNode.parent;
                    rbNode.height = Math.max(rbNode.left.height + 1, rbNode.right.height + 1);
                    parent.height = Math.max(parent.left.height + 1, parent.right.height + 1);
                }
            }
            if (rbNode == root && !root.isBlack()) root.setColour(true);
            size++;
            root.height = Math.max(root.right.height + 1, root.left.height + 1);
        }
        return true;
    }

    @Override
    public void insert(T node) {
        long start = System.currentTimeMillis();
        boolean isInserted = insertInternal(node);
        if (!isInserted) System.out.println(node + " was already inserted");
        else System.out.println("Insert in RB: " + node);
        long end = System.currentTimeMillis();
        insertionTimeStr+=("Took: " + (end-start) + " ms\n");
    }

    public RbNode<T> getLeftMostRight(RbNode<T> node) {
        RbNode<T> leftMost = node.right;
        while (leftMost.left != leaf) {
            leftMost = leftMost.left;
        }
        return leftMost;
    }

    public boolean deleteInternal(T node) {
        RbNode<T> parent = searchInternal(node);
        if (parent.right.getValue().compareTo(node) != 0 && parent.left.getValue().compareTo(node) != 0) {
            return false;
        } else if (size == 1) {
            root.setValue(null);
            size = 0;
            root.height = 0;
        } else {
            RbNode<T> rbNode;
            if (parent.right.getValue().compareTo(node) == 0) rbNode = parent.right;
            else rbNode = parent.left;
            if (rbNode.left != leaf && rbNode.right != leaf){
                parent = rbNode;
                rbNode = getLeftMostRight(rbNode);
                parent.setValue(rbNode.getValue());
                parent = rbNode.parent;
            }
            if (rbNode.left == leaf) {
                if (rbNode.right != leaf && rbNode.right.left != leaf) {
                    rbNode.setValue(rbNode.right.left.getValue());
                    rbNode.right.left.parent = null;
                    rbNode.right.left.right = null;
                    rbNode.right.left.left = null;
                    rbNode.right.left = leaf;
                } else {
                    if (parent.right == rbNode) parent.right = rbNode.right;
                    else parent.left = rbNode.right;
                    if (rbNode.right != leaf) {
                        rbNode.right.parent = parent;
                    }
                    rbNode.parent = null;
                    rbNode.right = null;
                    rbNode.left = null;
                }
            } else {
                if (rbNode.left.right != leaf) {
                    rbNode.setValue(rbNode.left.right.getValue());
                    rbNode.left.right.parent = null;
                    rbNode.left.right.right = null;
                    rbNode.left.right.left = null;
                    rbNode.left.right = leaf;
                } else {
                    if (parent.right == rbNode) parent.right = rbNode.right;
                    else parent.left = rbNode.right;
                    rbNode.left.parent = parent;
                    rbNode.parent = null;
                    rbNode.right = null;
                    rbNode.left = null;
                }
            }
        }
        return true;
    }

    @Override
    public void delete(T node) {
        long start = System.currentTimeMillis();
        boolean isDeleted = deleteInternal(node);
        if (isDeleted) System.out.println("Delete in RB element: " + node);
        else System.out.println(node + " not found");
        long end = System.currentTimeMillis();
        insertionTimeStr+=("Took: " +(end-start) + " ms\n");
    }

    @Override
    public boolean search(T node) {
        RbNode<T> rbNode = root;
        boolean found = false;
        while (rbNode.getValue() != null) {
            if (node.compareTo(rbNode.getValue()) > 0) {
                rbNode = rbNode.right;
            } else if (node.compareTo(rbNode.getValue()) < 0) {
                rbNode = rbNode.left;
            } else {
                found = true;
                break;
            }
        }
        System.out.println("Search in RB for " + node + " = " + found);
        return found;
    }

    @Override
    public int getSize() {
        System.out.println("Get size in RB = " + size);
        return size;
    }

    @Override
    public int getHeight() {
        System.out.println("Get height in RB = " + root.height);
        return root.height;
    }

    public void ends() throws IOException {

        FileWriter insertionTime, deletionTime;
        System.out.println("Writing ");

        insertionTime = new FileWriter("insertion_in_rb.txt");

        insertionTime.write(insertionTimeStr);
        System.out.println("Writing done");

        insertionTime.close();
        deletionTime = new FileWriter("deletion_in_rb.txt");
        deletionTime.write(deletionTimeStr);
        deletionTime.close();
    }
}
