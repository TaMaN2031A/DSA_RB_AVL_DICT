package RB;

import com.company.Tree_Interface;

import java.io.FileWriter;
import java.io.IOException;

public class RbTree<T extends Comparable> implements Tree_Interface<T> {

    String insertionTimeStr ="Hello\n", deletionTimeStr="Hello\n";
    int size = 0, height = 0;
    RbNode<T> begin = new RbNode<>(null, null, null, null);
    RbNode<T> root = new RbNode<>(begin, null, null, null);
    RbNode<T> leaf = new RbNode<>(root, null, null, null);

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
    }

    @Override
    public void insert(T node) {
        long start = System.currentTimeMillis();
        RbNode<T> rbNode = root, parent = root;
        boolean found = false;
        while (rbNode.getValue() != null) {
            if (node.compareTo(rbNode.getValue()) > 0) {
                parent = rbNode;
                rbNode = rbNode.right;
            } else if (node.compareTo(rbNode.getValue()) < 0) {
                parent = rbNode;
                rbNode = rbNode.left;
            } else {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println(node + "was already inserted");
        } else if (size == 0) {
            root.setValue(node);
            size = 1;
            height = 1;
            System.out.println("Insert in RB: " + node);
        } else {
            int depth = 1;
            rbNode = new RbNode<>(parent, leaf, leaf, node);
            if (node.compareTo(parent.getValue()) > 0) parent.right = rbNode;
            else parent.left = rbNode;
            RbNode<T> ancestor;
            while (rbNode != root && parent != root) {
                ancestor = parent.parent;
                if (parent.isBlack()) {
                    while (rbNode != root) {
                        depth++;
                        rbNode = rbNode.parent;
                    }
                }
                else if (!ancestor.left.isBlack() && !ancestor.right.isBlack()) {
                    ancestor.right.setColour(true);
                    ancestor.left.setColour(true);
                    ancestor.setColour(false);
                    rbNode = ancestor;
                    parent = rbNode.parent;
                    depth += 2;
                } else {
                    depth = 0;
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
                }
            }
            if (rbNode == root && !root.isBlack()) root.setColour(true);
            else if (rbNode != root) depth++;
            size++;
            height = Math.max(depth, height);
            System.out.println("Insert in RB: " + node);
        }
        long end = System.currentTimeMillis();
        insertionTimeStr+=("Took: " + (end-start) + " ms\n");
    }

    @Override
    public void delete(T node) {
        long start = System.currentTimeMillis();

        System.out.println("Delete in RB element: "+node);
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
        System.out.println("Get height in RB = " + height);
        return height;
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
