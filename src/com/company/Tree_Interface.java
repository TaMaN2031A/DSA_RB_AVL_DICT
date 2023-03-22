package com.company;

import java.io.IOException;

public interface Tree_Interface<T> {
    boolean insert(T node) throws IOException;
    boolean delete(T node) throws IOException;
    boolean search(T node);
    int getSize();
    int getHeight();
    void ends() throws IOException;
}
