package MyTree;
//二叉查找树节点类
    public class BSTNode<K, V> {
        public K key;
        public V value;
        public BSTNode<K, V> left;
        public BSTNode<K, V> right;
        public BSTNode<K, V> parent;
        public boolean isLeftChild;
        public int height;
        public int num;
        public boolean isRed = true;  // 后面才学习的红黑树

        public BSTNode() {
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right, BSTNode<K, V> parent) {
            super();
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
            return (isRed ? "红色" : "黑色") + " [" + key + "]<-" + (parent == null ? "" : parent.key);
        }
    }
