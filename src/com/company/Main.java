package com.company;

import AVL.AvlTree;
import RB.RbTree;

import java.io.IOException;

public class Main {
    static int x;
    public static void main(String[] args) throws IOException {
        CLI cli = new CLI();
        cli.Welcome();
        cli.run();
    }
}
