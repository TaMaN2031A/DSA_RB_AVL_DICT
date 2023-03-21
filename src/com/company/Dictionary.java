package com.company;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Interfaces.Tree_Interface;

public class Dictionary {
    Tree_Interface tree;
    Factory factory = new Factory();
    long start,end;
    FileWriter writer;
    String type;
    Dictionary(String type)
    {   this.type=type;
        tree = factory.getTree(type);
    }
    int insert(String node) throws IOException {
        start=System.currentTimeMillis();
        int ans=tree.insert(node);
        end=System.currentTimeMillis();
        if(ans==1){
            System.out.println("Insertion Time is "+(end-start)+" ms");
        }
        if(type.equals("AVL")){
            writer = new FileWriter("insertion_in_avl.txt",true);
            writer.write("Time taken to insert the given key (if inserted) is "+(end-start)+" ms\n");
            writer.close();
        }else{
            writer = new FileWriter("insertion_in_rb.txt",true);
            writer.write("Time taken to insert the given key (if inserted) is "+(end-start)+" ms\n");
            writer.close(); 
        }
        return ans;
    }
    int delete(String node) throws IOException {
        start=System.currentTimeMillis();
        int ans=tree.delete(node);
        end=System.currentTimeMillis();
        if(ans==1){
            System.out.println("Deletion Time is "+(end-start)+" ms");
        }    
        if(type.equals("AVL")){
            writer = new FileWriter("deletion_in_avl.txt",true);
            writer.write("Time taken to delete the given key (if deleted) is "+(end-start)+" ms\n");
            writer.close();
        }else{
            writer = new FileWriter("deletion_in_rb.txt",true);
            writer.write("Time taken to delete the given key (if deleted) is "+(end-start)+" ms\n");
            writer.close(); 
        }
        return ans;
    }
    void search(String node) throws IOException
    {
        start=System.currentTimeMillis();
        tree.search(node);
        end=System.currentTimeMillis();
        System.out.println("Searching Time is "+(end-start)+" ms");
        if(type.equals("AVL")){
            writer = new FileWriter("search_in_avl.txt",true);
            writer.write("Time taken to search for the given key is "+(end-start)+" ms\n");
            writer.close();
        }else{
            writer = new FileWriter("search_in_rb.txt",true);
            writer.write("Time taken to search for the given key is "+(end-start)+" ms\n");
            writer.close(); 
        }

    }
    int getSize()
    {   start=System.currentTimeMillis();
        int ans=tree.TreeSize();
        end=System.currentTimeMillis();
        System.out.println("Time to get the size is "+(end-start)+" ms");
        return ans;
    }
    int getHeight()
    {   
        start=System.currentTimeMillis();
        int ans=tree.TreeHeight();
        end=System.currentTimeMillis();
        System.out.println("Time to get the height of backend tree is "+(end-start)+" ms");
        return ans;
    }    
    
    void BatchInsert(String route) throws IOException {
        int count=0,filesize=0;
        File file = new File(route);
        Scanner sc = new Scanner(file);
        start=System.currentTimeMillis();
        while(sc.hasNextLine())
        {   filesize++;
            String input = sc.nextLine();
            count+=tree.insert(input);
        }
        end=System.currentTimeMillis();
        System.out.println("\nBatch insertion Done Successfully!");
        System.out.println(count+" new keys inserted!");
        System.out.println((filesize-count)+" keys already exist in the Dictionary!");
        System.out.println("\nTime of insertion is : "+(end-start)+" ms");
        if(type.equals("AVL")){
            writer = new FileWriter("insertion_in_avl.txt",true);
            writer.write("Time taken to insert batch of size "+filesize+ " is "+(end-start)+" ms\n");
            writer.close();
        }else{
            writer = new FileWriter("insertion_in_rb.txt",true);
            writer.write("Time taken to insert batch of size "+filesize+ " is "+(end-start)+" ms\n");
            writer.close(); 
        }
    }
    void BatchDelete(String route) throws IOException {
        int count=0,filesize=0;
        File file = new File(route);
        Scanner sc = new Scanner(file);
        start=System.currentTimeMillis();
        while(sc.hasNextLine())
        {   filesize++;
            String input = sc.nextLine();
            count+=tree.delete(input);
        }
        end=System.currentTimeMillis();
        System.out.println("\nBatch Deletion Done Successfully!");
        System.out.println(count+" keys deleted!");
        System.out.println((filesize-count)+" keys haven't been found in the Dictionary!");
        System.out.println("\nTime of deletion is : "+(end-start)+" ms");
        if(type.equals("AVL")){
            writer = new FileWriter("deletion_in_avl.txt",true);
            writer.write("Time taken to delete batch of size "+filesize+ " is "+(end-start)+" ms\n");
            writer.close();
        }else{
            writer = new FileWriter("deletion_in_rb.txt",true);
            writer.write("Time taken to delete batch of size "+filesize+ " is "+(end-start)+" ms\n");
            writer.close(); 
        }
    }
    void ends() throws IOException {
        System.out.println("\033[0;31mExecution Times have been writen in files!\033[0m");
        System.out.println("\033[0;32m\nThanks for Using Our Dictionary\033[0m");

    }

}
