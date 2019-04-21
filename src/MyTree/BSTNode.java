package MyTree;

public class BSTNode<K,V> {
    public K key;
    public V value;
    public BSTNode<K, V> left;
    public BSTNode<K, V> right;
    public BSTNode<K, V> parent;
    public boolean isLeftChild;
    public int height;
    public int num;

    public BSTNode() {
    }

    public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right, BSTNode<K, V> parent) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    public boolean isLeft() {
        return isLeftChild;
    }

    public boolean isRight() {
        return !isLeftChild;
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
