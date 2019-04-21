package MyTree;

import java.util.Comparator;
import java.util.function.Consumer;

public class BinarySearchTree<K, V> implements IBinarySearchTree<K, V> {
    public BSTNode<K, V> root;
    protected int size;
    private Comparator comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree(BSTNode<K, V> root) {
        this.root = root;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        bst.insert(5, 1);
        bst.insert(4, 1);
        bst.insert(3, 1);
        bst.insert(2, 1);
        bst.insert(11, 1);
        bst.insert(23, 1);
        bst.insert(32, 1);
        // System.out.println(bst.minNode(bst.root).key); System.out.println("size:" + bst.size);
        bst.removeNode(bst.root.left);
        bst.removeNode(bst.root.left.left);
        bst.removeNode(bst.root.right.right);
        bst.inorder(root -> {
            System.out.println(root.toString());
        });

    }

    @Override
    public void remove(K key) {
        BSTNode<K, V> curr = root;
        while (curr != null) {
            if (key == curr) {
                break;
            }
        }

    }

    protected BSTNode<K, V> minNode(BSTNode<K, V> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    protected void removeNode(BSTNode<K, V> x) {
        if (x != null) {
            if (x.left == null && x.right == null) {
                if (x.parent == null) {
                    root = null;
                    return;
                }
                if (x.isLeftChild) {
                    x.parent.left = null;
                } else {
                    x.parent.right = null;
                }
                x.parent = null;
                x = null;
            }else if (x.left == null) {
                if (x.isLeftChild) {
                    BSTNode<K, V> c = x.right;
                    BSTNode<K, V> parent = x.parent;
                    parent.left = c;
                    c.isLeftChild = true;
                    c.parent = parent;
                } else {
                    if (x.parent != null) {
                        x.parent.right = x.right;
                        x.right.parent = x.parent;
                    } else {
                        root = x.right;
                    }
                }
                x = null;

            } else if (x.right == null) {
                if (x.isLeftChild) {
                    x.parent.left = x.left;
                    x.left.parent = x.parent;
                } else {
                    if (x.parent != null) {
                        x.parent.right = x.left;
                        x.left.isLeftChild = false;
                        x.left.parent = x.parent;
                    } else {
                        root = x.left;
                    }
                }
                x = null;
            } else {//左右都有孩子
                BSTNode<K, V> minRight = minNode(x);
                x = minRight;
                removeNode(minRight);
            }
            size--;
        }

    }

    public BSTNode<K, V> insert(K key, V value) {
        if (!(key instanceof Comparable)) {
            throw new ClassCastException();
        }
        BSTNode<K, V> parent = null;
        BSTNode<K, V> curr = root;

        while (curr != null) {
            parent = curr;
            if (compare(key, curr.key) < 0) {
                curr = curr.left;
            } else if (compare(key, curr.key) > 0) {
                curr = curr.right;

            } else {
                curr.value = value;
                return curr;
            }
        }
        curr = new BSTNode(key, value, null, null, null);
        curr.parent = parent;
        if (parent == null) {
            root = curr;
        } else if (compare(key, parent.key) < 0) {
            parent.left = curr;
            curr.isLeftChild = true;
        } else {
            parent.right = curr;
            curr.isLeftChild = false;
        }
        size++;
        updateHeight(curr);
        return curr;
    }

    private void updateHeight(BSTNode<K, V> curr) {
        if (curr.parent == null) return;//util root

        BSTNode<K, V> p = curr.parent;
        if (p.height == curr.height) {
            p.height++;
            updateHeight(p);//递归
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private int compare(K key1, K key2) {
        if (null == comparator) {
            return ((Comparable) key1).compareTo((Comparable) key2);
        } else {
            return comparator.compare(key1, key2);
        }
    }


    @Override
    public K successor(K x) {
        return null;
    }

    @Override
    public K predecessor(K x) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    public void inorder(Consumer<K> con) {
        if (root != null)
            // inorder2(root, con);
            inorder(root, con);
    }

    /* 递归形式 */
    private void inorder(BSTNode<K, V> p, Consumer<K> con) {
        if (p != null) {
            inorder(p.left, con);
            con.accept(p.key);
            inorder(p.right, con);
        }
    }
/*    private void inOrder(BSTNode <K,V>p) {
        if (p!= null) {

            inOrder(p.left);

            System.out.println(p.key);

            inOrder(p.right);
        }

    }*/


}
