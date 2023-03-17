package RB;

public class RbNode<T extends Comparable> {
    private boolean colourBlack = false;
    public RbNode<T> parent;
    public RbNode<T> right;
    public RbNode<T> left;
    private T value;

    public RbNode(RbNode<T> parent, RbNode<T> right, RbNode<T> left, T value) {
        this.parent = parent;
        this.right = right;
        this.left = left;
        this.value = value;
    }

    public void setColour(boolean isBlack) {
        this.colourBlack = isBlack;
    }

    public boolean isBlack() {
        return colourBlack;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
