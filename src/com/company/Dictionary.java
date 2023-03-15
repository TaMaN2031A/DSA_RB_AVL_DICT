package com.company;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {
    Tree_Interface tree;
    Factory factory = new Factory();
    Dictionary(String type)
    {
        tree = factory.getTree(type);
    }
    void insert(String node) throws IOException {
        tree.insert(node);
    }
    void delete(String node) throws IOException {
        tree.delete(node);
    }
    void search(String node)
    {
        tree.search(node);
    }
    void BashInsert(String route) throws IOException {
        File file = new File(route);
       // System.out.println(route);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String input = sc.next();
            insert(input);
        }
        System.out.println("Batch insert Done");
    }
    void BashDelete(String route) throws IOException {
        File file = new File(route);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String input = sc.next();
            delete(input);
        }
        System.out.println("Batch insert Done");
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
