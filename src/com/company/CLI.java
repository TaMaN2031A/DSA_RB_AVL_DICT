package com.company;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CLI {
    Dictionary dictionary = null;
    String type = "n/a";

    void Welcome()
    {
        System.out.println("Welcome");
    }
    void run()  throws IOException {
        Scanner sc=new Scanner(System.in);
        label:
        while(true)
        {
            while (!type.equals("AVL") && !type.equals("RB")) {
                System.out.println("Insert \"AVL\" for using AVL tree, or \"RB\" for RB tree");
                type = sc.next();
                System.out.println(type);
                dictionary = new Dictionary(type);
            }
            String[] in = new String[20];
            while(true)
            {
                try {
                    String buffer;
                    buffer = sc.nextLine();
                    StringTokenizer tokenizer = new StringTokenizer(buffer, " ");
                    in[0] = tokenizer.nextToken();
                    if(tokenizer.hasMoreElements())
                        in[1] = tokenizer.nextToken();
                    if (tokenizer.hasMoreElements())
                        in[2] = tokenizer.nextToken();
                    if (tokenizer.hasMoreElements())
                        continue;
                    if (in[0].equals("insert") || in[0].equals("delete") ||
                            in[0].equals("search") || in[0].equals("batch") || in[0].equals("get") || in[0].equals("end"))
                        break;
                    else
                        System.out.println("Insert valid input");
                }
                catch (Exception e)
                {
                    System.out.println("Please write Input");
                }
            }
            try {
                switch (in[0]) {
                    case "insert":
                        dictionary.insert(in[1]);
                        break;
                    case "delete":
                        dictionary.delete(in[1]);
                        break;
                    case "search":
                        dictionary.search(in[1]);
                        break;
                    case "batch":
                        if (in[1].equals("insert"))
                            dictionary.BashInsert(in[2]);
                        else
                            dictionary.BashDelete(in[2]);
                        break;
                    case "get":
                        if (in[1].equals("height"))
                            dictionary.getHeight();
                        else
                            dictionary.getSize();
                        break;
                    default:
                        dictionary.ends();
                        break label;
                }


            }
            catch (Exception e)
            {
                System.out.println("Operation failed, insert right input");
                System.out.println(e);
            }
        }

    }

}
