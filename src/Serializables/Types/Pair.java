package Serializables.Types;

public class Pair<L,R> {
    final private L left;
    final private R right;
    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
