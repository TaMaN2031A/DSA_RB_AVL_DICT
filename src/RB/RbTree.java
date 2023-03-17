package RB;

import com.company.Tree_Interface;

import java.io.FileWriter;
import java.io.IOException;

public class RbTree<T extends Comparable> implements Tree_Interface<T> {
    String insertionTimeStr ="Hallo\n", deletionTimeStr="Hallo\n";
    int size = 0, height = 0;
    RbNode root = new RbNode(null, null, null, null);
    RbNode leaf = new RbNode(root, null, null, null);

    public RbTree() {
        root.parent = root;
        root.right = leaf;
        root.left = leaf;
        root.setColour(true);
    }

    public void rotateRight(RbNode node) {
        RbNode child = node.left;
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

    public void rotateLeft(RbNode node) {
        RbNode child = node.right;
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
        RbNode rbNode = root, parent = root;
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
        } else {
            rbNode.parent = parent;
        }
        System.out.println("Insert in RB: "+node);
        long end = System.currentTimeMillis();
        insertionTimeStr+=("Took: " +(end-start) + " ms\n");
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
        RbNode rbNode = root;
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
        System.out.println("Search in RB for "+node);
        return found;
    }

    @Override
    public int getSize() {
        System.out.println("Getsize in RB");
        return size;
    }

    @Override
    public int getHeight() {
        System.out.println("GetHeight in RB");
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
