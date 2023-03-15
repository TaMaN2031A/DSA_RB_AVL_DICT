package RB;

import com.company.Tree_Interface;

import java.io.FileWriter;
import java.io.IOException;

public class RbTree<T extends Comparable> implements Tree_Interface<T> {
    String insertionTimeStr ="Hallo\n", deletionTimeStr="Hallo\n";
    @Override
    public void insert(T node) {
        long start = System.currentTimeMillis();

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
    public void search(T node) {
        System.out.println("Search in RB for "+node);
    }

    @Override
    public int getSize() {
        System.out.println("Getsize in RB");
        return 0;
    }

    @Override
    public int getHeight() {
        System.out.println("GetHeight in RB");
        return 0;
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
