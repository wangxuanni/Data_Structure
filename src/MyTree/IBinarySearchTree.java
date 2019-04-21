package MyTree;

public interface IBinarySearchTree<K,V> {
    BSTNode<K,V>insert(K k,V v);
    void remove(K key);
    K successor(K x);
    K predecessor(K x);
    int getSize();
    int getHeight();
}
