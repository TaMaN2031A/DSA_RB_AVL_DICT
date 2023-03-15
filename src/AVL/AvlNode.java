package AVL;

public class AvlNode<T extends Comparable<T>> implements Comparable<AvlNode<T>>{

    private int  height;
    private AvlNode left, right;
    private T data;

    @Override
    public int compareTo(AvlNode<T> o) {
        return this.data.compareTo(o.data);
    }

}
