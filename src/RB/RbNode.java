package RB;

public class RbNode<T extends Comparable> {
    private boolean colourBlack = false;
    public RbNode parent;
    public RbNode right;
    public RbNode left;
    private T value;

    public RbNode(RbNode parent, RbNode right, RbNode left, T value) {
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
