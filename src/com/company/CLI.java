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
        while(true)
        {
            while (!type.equals("AVL") && !type.equals("RB")) {
                System.out.println("Insert \"AVL\" for using AVL tree, or \"RB\" for RB tree");
                type = sc.next();
                System.out.println(type);
                dictionary = new Dictionary(type);
            }
            String in[] = new String[20];
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
                }
                catch (Exception e)
                {
                    System.out.println("Please write Input");
                }
            }
            try {
                if (in[0].equals("insert")) {
                    dictionary.insert(in[1]);
                } else if (in[0].equals("delete")) {
                    dictionary.delete(in[1]);
                } else if (in[0].equals("search")) {
                    System.out.println(dictionary.search(in[1]));
                } else if (in[0].equals("batch")) {
                    if (in[1].equals("insert"))
                        dictionary.BashInsert(in[2]);
                    else
                        dictionary.BashDelete(in[2]);
                } else if (in[0].equals("get")) {
                    if (in[1].equals("height"))
                        dictionary.getHeight();
                    else
                        dictionary.getSize();
                } else
                {
                    dictionary.ends();
                    break;
                }


            }
            catch (Exception e)
            {
                System.out.println("Operation failed, insert right input");
            }
        }

    }

}
