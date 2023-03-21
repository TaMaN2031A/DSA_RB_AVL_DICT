package com.company;

import RB.RbTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static java.lang.Math.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.Math;
class DictionaryTest {

    @Test
    void testSizeAfterAdding197DifferentElementIn4000Words() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        int added = dictionary.BatchInsert("F:\\Trialforjava.txt");
        dictionary.ends();
        System.out.println(dictionary.getSize());
        assertEquals(added, dictionary.getSize());

    }

    @Test // Case of insert one node in tree then remove it, search for it & get size before after
    void testCornerCase1RB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        dictionary.insert("Test");
        assertEquals(1,dictionary.getSize());
        dictionary.delete("Test");
        assertEquals(0,dictionary.getSize());

    }
    @Test
    void testCornerCase1AVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        dictionary.insert("Test");
        assertEquals(1,dictionary.getSize());
        dictionary.delete("Test");
        assertEquals(0,dictionary.getSize());
    }
    @Test // Adding the same node 4000 times checking height and size
    void testCornerCaseTwoRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        for(int i = 0; i < 4000; i++)
        {
            dictionary.insert("a");
        }
        assertEquals(1, dictionary.getSize());
        assertEquals(0, dictionary.getHeight());

    }
    @Test
    void testCornerCaseTwoAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        for(int i = 0; i < 4000; i++)
        {
            dictionary.insert("a");
        }
        assertEquals(1, dictionary.getSize());
        assertEquals(0, dictionary.getHeight());
    }

    @Test // Batch inserting same Data
    void batchInsertingTheSameDataRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        int added = dictionary.BatchInsert("F:\\200.txt");
        assertEquals(200, added);
        int added2 = dictionary.BatchInsert("F:\\200.txt");
        assertEquals(0, added2);
    }
    @Test
    void batchInsertingTheSameDataAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        int added = dictionary.BatchInsert("F:\\200.txt");
        assertEquals(200, added);
        int added2 = dictionary.BatchInsert("F:\\200.txt");
        assertEquals(0, added2);
    }


    @Test // Batch inserting different 200, then deleting only 100 of them, checking size, ToBeDone insha'allah
    void batchInsert200ThenBatchDeleteHalfOfThemRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        int added = dictionary.BatchInsert("F:\\200.txt");
        assertEquals(200, added);
        int deleted = dictionary.BatchDelete("F:\\100.txt");
        assertEquals(added-deleted, dictionary.getSize());
    }
    @Test
    void batchInsert200ThenBatchDeleteHalfOfThemAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        int added = dictionary.BatchInsert("F:\\200.txt");
        assertEquals(200, added);
        int deleted = dictionary.BatchDelete("F:\\100.txt");
        assertEquals(added-deleted, dictionary.getSize());
    }

    // Checking Height is less than 2lgn in RB, less than lgn+1 in AVL
    @Test
    void checkHeightTestCaseRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        int added = dictionary.BatchInsert("F:\\200.txt");
        System.out.println(dictionary.getHeight());
        System.out.println(2*(log(dictionary.getSize())/log(2)));
        Assertions.assertTrue(dictionary.getHeight()<=2*(log(dictionary.getSize())/log(2)));
    }

    @Test
    void checkHeightTestCaseAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        int added = dictionary.BatchInsert("F:\\200.txt");
        System.out.println(dictionary.getHeight());
        System.out.println(2*(log(dictionary.getSize())/log(2)));
        Assertions.assertTrue(dictionary.getHeight()<=log(dictionary.getSize())/log(2)+1);
    }

    @Test
    void deletingSameData200TimesAfterAddingItRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        dictionary.insert("0");
        int removed = dictionary.BatchDelete("F:\\0.txt");
        dictionary.ends();
        assertEquals(1, removed);
    }

    @Test
    void deletingSameData200TimesAfterAddingItAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        dictionary.insert("0");
        int removed = dictionary.BatchDelete("F:\\0.txt");
        assertEquals(1, removed);
    }

    @Test
    void testing_The_Time_In_Insertion_Deletion_SearchingRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        dictionary.BatchInsert("F:\\200.txt");
        String x = "a", y = "b";
        for(int i = 0; i < 200; i++)
        {
            if(i < 100) {
                dictionary.search(x);
                x += "a";
                dictionary.delete(x);
            }
            else
            {
                dictionary.search(y);
                y += "b";
                dictionary.delete(y);
            }
        }
        dictionary.BatchInsert("F:\\200.txt");
        dictionary.BatchDelete("F:\\200.txt");
        dictionary.ends();
    }

    @Test
    void testing_The_Time_In_Insertion_Deletion_SearchingAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        dictionary.BatchInsert("F:\\200.txt");
        String x = "a", y = "b";
        for(int i = 0; i < 200; i++)
        {
            if(i < 100) {
                dictionary.search(x);
                x += "a";
                dictionary.delete(x);
            }
            else
            {
                dictionary.search(y);
                y += "b";
                dictionary.delete(y);
            }
        }
        dictionary.BatchInsert("F:\\200.txt");
        dictionary.BatchDelete("F:\\200.txt");
        dictionary.ends();
    }


    @Test
    void Searching_Non_Existing_NodeRB() throws IOException {
        Dictionary dictionary = new Dictionary("RB");
        dictionary.insert("a");
        dictionary.delete("a");
        Assertions.assertFalse(dictionary.search("a"));
    }
    @Test
    void Searching_Non_Existing_NodeAVL() throws IOException {
        Dictionary dictionary = new Dictionary("AVL");
        dictionary.insert("a");
        dictionary.delete("a");
        Assertions.assertFalse(dictionary.search("a"));
    }
        // Running out of ideas el sara7, na2es 4 3ala el 20, matroken l 25oya el m7nksh
        // 4aylen kimo tarek l 3oza, 20 test cases al7amdullilah

}