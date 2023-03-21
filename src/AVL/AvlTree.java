package AVL;

import RB.RbNode;
import com.company.Tree_Interface;

import java.io.FileWriter;
import java.io.IOException;

public class AvlTree<T extends Comparable<T>> implements Tree_Interface<T>{

    String insertionTimeStr ="Hallo\n", deletionTimeStr="Hallo\n";
    RbNode<T> begin = new RbNode<>(null, null, null, null, 1);
    RbNode<T> root = new RbNode<>(begin, null, null, null, 0);
    RbNode<T> leaf = new RbNode<>(root, null, null, null, 0);



    @Override
    public boolean insert(T node) throws IOException {
        long start = System.currentTimeMillis();

        System.out.println("Insert in AVL: "+node);
        //
        long end = System.currentTimeMillis();
        insertionTimeStr+=("Took: " +(end-start) + " ms\n");
        return true;
    }
    public RbNode<T> getRoot() {
        return root;
    }

    @Override
    public boolean delete(T node) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("Delete in AVL element: "+node);
        long end = System.currentTimeMillis();
        insertionTimeStr+=("Took: " +(end-start) + " ms\n");
        return true;
    }

    @Override
    public boolean search(T node) {
        System.out.println("Search in AVL for "+node);
        return true;
    }



    @Override
    public int getSize() {
        System.out.println("Getsize in AVL");
        return 0;
    }

    @Override
    public int getHeight() {
        System.out.println("GetHeight in AVL");
        return 0;
    }
    @Override
    public void ends() throws IOException {
        FileWriter insertionTime, deletionTime;
        System.out.println("Writing ");

        insertionTime = new FileWriter("insertion_in_avl.txt");

        insertionTime.write(insertionTimeStr);
        System.out.println("Writing done");

        insertionTime.close();
        deletionTime = new FileWriter("deletion_in_avl.txt");
        deletionTime.write(deletionTimeStr);
        deletionTime.close();
    }
}
