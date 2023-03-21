package com.company;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary<T extends Comparable<T>> {
    Tree_Interface tree;
    Factory factory = new Factory();
    Dictionary(String type)
    {
        tree = factory.getTree(type);
    }
    boolean insert(String node) throws IOException {
        return tree.insert(node);
    }
    boolean delete(String node) throws IOException {
        return tree.delete(node);
    }
    boolean search(String node)
    {
        return tree.search(node);
    }
    int BashInsert(String route) throws IOException {
        File file = new File(route);
        int added = 0;
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String input = sc.next();
            boolean state = insert(input);
            if(state)
            {
                added++;
            }
        }
        System.out.println("Batch insert Done ");
        System.out.println("Number of successful insertions is: " + added);
        return added;
    }
    int BashDelete(String route) throws IOException {
        File file = new File(route);
        Scanner sc = new Scanner(file);
        int deleted = 0;
        while(sc.hasNextLine())
        {
            String input = sc.next();
            if(delete(input))
                deleted++;
        }
        System.out.println("Batch delete Done");
        System.out.println("Number of successful deletions is: " + deleted);
        return deleted;
    }
    void ends() throws IOException {
        tree.ends();
    }
    int getSize()
    {
        return tree.getSize();
    }
    int getHeight()
    {
        return tree.getHeight();
    }
}
