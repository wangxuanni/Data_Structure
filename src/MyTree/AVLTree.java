package MyTree;

public class AVLTree<K, V> extends BinarySearchTree<K, V> implements IBinarySearchTree<K, V> {
    public static void main(String[] args) {
        AVLTree avlt = new AVLTree();
        avlt.insert(2, 1);
        avlt.insert(6, 1);
        avlt.insert(9, 1);
        avlt.insert(23, 1);
        avlt.insert(11, 1);
        avlt.insert(4, 1);
//toString�����ɵ��ò�α������
        System.out.println(avlt);
        System.out.println("------------");

     /*   avlt.remove(4);
        System.out.println(avlt);

        System.out.println("------------");*/
    }


    @Override
    public BSTNode<K, V> insert(K key, V value) {
        // �Ȱ�bst�ķ�ʽ�����룬�ٵ���
        BSTNode<K, V> nnode = super.insert(key, value);
        // �����ҵ���һ����ƽ�������p
        BSTNode<K, V>[] pqs = firstUnbalance(nnode);
        if (null != pqs) {// ��ƽ��
            // System.out.println(pqs[0].key);
            reBalance(pqs);
        }
        return nnode;
    }

    /* ��pqs����״������������������ */
    private void reBalance(BSTNode<K, V>[] pqs) {
        if (pqs == null)
            return;
        BSTNode p = pqs[0];// ��ƽ����Ǹ�����
        BSTNode q = pqs[1];// p���ӽڵ�
        BSTNode s = pqs[2];// q���ӽڵ�

        if (q.isRight() && s.isRight()) {// �����ͣ���pΪ������ʱ����ת
            leftRotate(p, q);  // ������ǰ��Ĳ���
            // reBalance(firstUnbalance(q));
        } else if (q.isLeft() && s.isLeft()) {// �����ͣ���pΪ����˳ʱ����ת
            rightRotate(p, q);
            // reBalance(firstUnbalance(q));
        } else if (q.isLeft() && s.isRight()) {// ������

            leftRotate(q, s);// q��s��������Ϊ������
            rightRotate(p, s);
            // reBalance(firstUnbalance(s));
        } else {// ������

            rightRotate(q, s);
            leftRotate(p, s);
            // reBalance(firstUnbalance(s));
        }
    }

    private BSTNode<K, V>[] firstUnbalance(BSTNode<K, V> n) {
        if (n == null)
            return null;
        BSTNode s = n;
        BSTNode p = n.parent;
        if (p == null)
            return null;
        BSTNode g = p.parent;
        if (g == null)
            return null;
        if (unBalance(g)) {// ��ƽ����
            return new BSTNode[]{g, p, s};
        } else {
            return firstUnbalance(p);
        }

    }

    private void reBalance(BSTNode<K, V> node) {
        if (node == null)
            return;

        BSTNode<K, V> right = node.right;
        BSTNode<K, V> left = node.left;
        int hOfRight = getHeight(right);
        int hOfleft = getHeight(left);

        if (hOfRight - hOfleft >= 2) {// �Ҳ��
            leftRotate(node, right);// ����
            reBalance(right);
        } else if (hOfRight - hOfleft <= -2) {
            rightRotate(node, left);
            reBalance(left);
        } else {
            reBalance(node.parent);
        }
    }

    protected boolean unBalance(BSTNode g) {
        if (g == null) return false;
        int minus = getHeight(g.left) - getHeight(g.right);
        return Math.abs(minus) > 1
                || unBalance(g.right)
                || unBalance(g.left);
    }

    public void remove2(K key) {
    }

    @Override
    public void remove(K key) {
        BSTNode<K, V> node = super.lookupNode(key);
        if (node == null)
            return;

        BSTNode<K, V> parent = node.parent;
        BSTNode<K, V> left = node.left;
        BSTNode<K, V> right = node.right;

        if (left == null && right == null) {// leaf node
            super.removeNode(node);
            reBalance(parent);    // ����ƽ��
        } else if (right == null) {// has only left child.�����滻����

            BSTNode<K, V> predecessor = maxNode(left);
            BSTNode<K, V> parentOfPredecessor = predecessor.parent;
            super.removeNode(predecessor);
            node.key = predecessor.key;
            node.value = predecessor.value;
            reBalance(parentOfPredecessor);

        } else {// ���Һ���,�ҵ�����������С
            BSTNode<K, V> successor = minNode(right);
            BSTNode<K, V> parentOfSuccessor = successor.parent;
            // minNode must be leaf node
            super.removeNode(successor);
            node.key = successor.key;
            reBalance(parentOfSuccessor);
        }
    }

}