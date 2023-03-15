package com.company;

import java.io.IOException;

public interface Tree_Interface<T> {
    void insert(T node) throws IOException;
    void delete(T node) throws IOException;
    void search(T node);
    int getSize();
    int getHeight();
    void ends() throws IOException;
}
