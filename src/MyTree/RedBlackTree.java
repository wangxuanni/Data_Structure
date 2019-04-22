package MyTree;

import java.util.List;
//�����
public class RedBlackTree<K, V> extends BinarySearchTree<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    @Override
    public BSTNode<K, V> insert(K key, V value) {
        BSTNode<K, V> newNode = super.insert(key, value);
        fixAfterInsert2(newNode.parent, newNode);
        colorBlack(root);// ���ڵ�Ⱦ��
        size++;
        return newNode;
    }

    /**
     * ��������ƽ�⣬�������ģʽƥ���д���� y x z A B C D
     *
     * @param parent �����ڵ�ĸ��ڵ�
     * @param newNode �����ڵ�
     * @return
     */
    private void fixAfterInsert(BSTNode parent, BSTNode newNode) {
        if (parent == null) {
            root = newNode;
            return;
        }
        if (colorOf(parent) == RED && colorOf(newNode) == RED) {
            // ��λ�Դ��������������ABC��xyz���ã�Ȼ��ͳһ����
            BSTNode A = null, B = null, C = null, D = null, x = null, y = null, z = null;
            // case 1 ����
            /*
             *
             * z y D x C A B
             */
            if (parent.isLeftChild && newNode.isLeftChild) {
                x = newNode;
                A = x.left;
                B = x.right;

                y = parent;
                C = y.right;

                z = y.parent;
                D = z.right;

                changePeek(y, z);
            }
            // case 2 ����
            /*
             *
             * x A y B z C D --> A x B y C z D
             */
            else if (!parent.isLeftChild && !newNode.isLeftChild) {
                z = newNode;
                C = z.left;
                D = z.right;

                y = z.parent;
                B = y.left;

                x = y.parent;
                A = x.left;

                changePeek(y, x);
            }
            // case 3 ����
            else if (parent.isLeftChild && !newNode.isLeftChild) {
                y = newNode;
                B = y.left;
                C = y.right;

                x = y.parent;
                A = x.left;

                z = x.parent;
                D = z.right;

                changePeek(y, z);
            }
            // case 4 ����
            else if (!parent.isLeftChild && newNode.isLeftChild) {
                y = newNode;
                B = y.left;
                C = y.right;

                z = y.parent;
                D = z.right;

                x = z.parent;
                A = x.left;

                changePeek(y, x);
            }
            // ------------------ͳһ��Ϊһ����ʽ�����������Ӳ�Ⱦɫ----------------------
            x.parent = y;
            z.parent = y;
            y.left = x;
            y.right = z;
            x.left = A;
            if (A != null) {
                A.parent = x;
                A.isLeftChild = true;
            }
            x.right = B;
            if (B != null) {
                B.parent = x;
                B.isLeftChild = false;
            }
            z.left = C;
            z.right = D;
            if (C != null) {
                C.parent = z;
                C.isLeftChild = true;
            }
            if (D != null) {
                D.parent = z;
                D.isLeftChild = false;
            }
            x.isLeftChild = true;
            z.isLeftChild = false;
            colorBlack(x);
            colorBlack(z);
            colorRed(y);
            // �ݹ�����׷��
            fixAfterInsert(y.parent, y);
        }

    }

    private void fixAfterInsert2(BSTNode parent, BSTNode newNode) {
        if (parent == null) {
            root = newNode;
            return;
        }
        if (colorOf(parent) == RED) {
            // uncle������Ϊ��ɫ
            BSTNode grand = parent.parent;
            BSTNode uncle = parent.isLeftChild ? grand.right : grand.left;
            // uncleΪ��
            if (uncle != null && colorOf(uncle) == RED) {
                colorRed(grand);
                colorBlack(parent);
                colorBlack(uncle);
                fixAfterInsert2(grand.parent, grand);
            } else {// uncleΪ��=uncleΪ��
                if (parent.isLeftChild && newNode.isLeftChild) {// ������
                    colorRed(grand);
                    colorBlack(parent);
                    rightRotate(grand, parent);
                } else if (parent.isLeftChild && !newNode.isLeftChild) {// ������
                    leftRotate(parent, newNode);
                    colorRed(grand);
                    colorBlack(newNode);
                    rightRotate(grand, newNode);
                } else if (!parent.isLeftChild && !newNode.isLeftChild) {// ������
                    colorRed(grand);
                    colorBlack(parent);
                    leftRotate(grand, parent);
                } else {// ������
                    rightRotate(parent, newNode);
                    colorRed(grand);
                    colorBlack(newNode);
                    leftRotate(grand, newNode);
                }
            }
        }
    }

    /**
     * �л����㣬��ʩnewPeekΪ�¶���
     *
     * @param newPeek
     *            �¶���
     * @param oldPeek
     *            �ɶ���
     */
    private void changePeek(BSTNode newPeek, BSTNode oldPeek) {
        newPeek.parent = oldPeek.parent;
        newPeek.isLeftChild = oldPeek.isLeftChild;
        if (oldPeek.parent != null) {
            if (oldPeek.isLeftChild)
                oldPeek.parent.left = newPeek;
            else
                oldPeek.parent.right = newPeek;
        } else {
            root = newPeek;
        }
    }

    private void colorRed(BSTNode node) {
        if (null != node)
            node.isRed = true;
    }

    private void colorBlack(BSTNode node) {
        if (null != node)
            node.isRed = false;
    }

    /**
     * �����ɾ�����޸� 1��˫֧ת��֧ 2��ɾ��D��������N 3��DΪ�ڣ����޸� 4��NΪ�죬�ܼ򵥣�N��ڼ��ɣ� NΪ�ڣ�ϵ�и��ӵ��޸�
     *
     * @param key
     */
    @Override
    public void remove(K key) {
        BSTNode toDelete = lookupNode(key);
        if (toDelete == null)
            return;
        size--;

        // ������ϸ���ڲ��ڵ㣬�������Ԫ�ص����ݵ���ɾ�ڵ㣬Ȼ��toDeleteָ���̣��ϲ�������һͬ����
        if (toDelete.left != null && toDelete.right != null) {
            BSTNode s = successor(toDelete);// ����������������
            toDelete.key = s.key;
            toDelete = s; // pָ�����̣��Ǵ�ɾ����
        } // toDelete has 2 children

        // ��ʱ��toDeleteһ���ǵ�֧��������Ҷ��
        // ���ڶ����ɾ�ڵ��
        BSTNode N = (toDelete.left != null ? toDelete.left : toDelete.right);
        // N����������toDelete��
        if (N != null) {
            // -------��һ���Ƕ������-----------
            // Link N to parent
            N.parent = toDelete.parent;
            if (toDelete.parent == null) {
                root = N;
                colorBlack(N);
            } else if (toDelete.isLeft()) { // p������
                toDelete.parent.left = N;
                N.isLeftChild = true;
            } else { // p���Һ���
                toDelete.parent.right = N;
                N.isLeftChild = false;
            }

            // Null out links so they are OK to use by fixAfterDeletion.
            toDelete.left = toDelete.right = toDelete.parent = null;
            // -------��һ���Ƕ������ end-----------

            // toDeleteΪ�ڲ���Ҫ�޸�
            if (colorOf(toDelete) == BLACK)
                fixAfterDeletion(N);
        } // toDelete has 1 children
        else if (toDelete.parent == null) { // toDelete��Ҷ�ӣ�1.���Ǹ����� if it is the
            // only node.
            root = null;// ��ɿ���
        } else { // toDelete��Ҷ�ӣ�2.���Ǹ���û�ж�����.
            // toDeleteΪ�ڲ���Ҫ�޸�
            if (colorOf(toDelete) == BLACK)
                fixAfterDeletion(toDelete);// ���޸���cut��

            // ���´���ִ���е�Ҷ�ӵĶ���
            if (toDelete.parent != null) {
                if (toDelete == toDelete.parent.left)
                    toDelete.parent.left = null;
                else if (toDelete == toDelete.parent.right)
                    toDelete.parent.right = null;
                toDelete.parent = null;
            }
        }

    }

    private void fixAfterDeletion(BSTNode N) {
        if (colorOf(N) == RED) {// NΪ�죬�򵥱�ڼ���
            colorBlack(N);
        }
        // NΪ�ڣ���double black��ɾ���ڵ�Ͷ���ڵ㶼Ϊ�ڣ��������������������
        // case1��N���µĸ��ڵ㣬��NΪ��ɫ��û���κ��ƻ�
        else if (N.parent == null) {
        } else {// Ϊ�ڣ��Ҳ��Ǹ��ڵ�
            case2(N);
        }
    }

    /*-------���2���ֵ�Ϊ��ɫ��ת��Ϊ�ֵ�Ϊ��-------*/
    private void case2(BSTNode N) {
        BSTNode parent = N.parent;
        BSTNode sib = sib(N, parent);
        if (colorOf(sib) == RED) {
            colorBlack(sib);
            colorRed(parent);
            if (N.isLeft())
                leftRotate(parent, N);
            else
                rightRotate(parent, N);
        }
        case3(N);// sib must be black.
    }

    private BSTNode sib(BSTNode N, BSTNode parent) {
        BSTNode sib;
        if (N.isLeft()) {
            sib = parent.right;
        } else {
            sib = parent.left;
        }
        return sib;
    }

    /*-------���3���ֵ�Ϊ�ڵ�ǰ���£������ֵܵ�˫��Ϊ�ڣ��ֵܿ��Ա�Ⱦ�죩
     * 1.��Ϊ��ɫ��˫��Ϊ�ڻ��߲�Ϊ�ڶ�����case4
     * 2.��Ϊ��ɫ���ֵ�Ⱦ�죬�ݹ鸸�ڵ�*/
    private void case3(BSTNode N) {
        BSTNode parent = N.parent;
        BSTNode sib = sib(N, parent);
        /* 2.��Ϊ��ɫ���ֵ�Ⱦ�죬�ݹ鸸�ڵ� */
        if (colorOf(parent) == BLACK && (sib.left == null || colorOf(sib.left) == BLACK)
                && (sib.right == null || colorOf(sib.right) == BLACK)) {
            colorRed(sib);
            fixAfterDeletion(parent);
        } else {
            case4(N);
        }
    }

    /*-------���4.1��PΪ�죬�ֵ�Ϊ�ڣ����ֵܵ�˫��Ϊ�ڡ������ַ�ɫ������
     * 4.2 P����ߺڣ��ֵ�Ϊ�ڣ�������һ��Ϊ�죬��ת�Ƶ�case5*/
    private void case4(BSTNode N) {
        BSTNode parent = N.parent;
        BSTNode sib = sib(N, parent);
        if (colorOf(parent) == RED && colorOf(sib.left) == BLACK && colorOf(sib.right) == BLACK) {
            colorRed(sib);
            colorBlack(parent);// �պ�ƽ��
        } else {
            case5(N);
        }
    }

    /*-------���5���ֵ����ڵĺ���Ϊ�죬ͨ����תת��Ϊcase6������ĺ���Ϊ��*/
    private void case5(BSTNode N) {
        BSTNode parent = N.parent;
        BSTNode sib = sib(N, parent);
        if (N.isLeft() && colorOf(sib.right) == BLACK) {
            // s->color = RED;
            // s->left->color = BLACK;
            // rotate_right(s);
            colorBlack(sib.left);
            colorRed(sib);
            rightRotate(sib, sib.left);// �ֵܵ���ຢ�ӱ�Ϊ��ɫ
        } else if (N.isRight() && colorOf(sib.left) == BLACK) {
            colorRed(sib);
            colorBlack(sib.right);
            leftRotate(sib, sib.right);
        }
        case6(N);
    }

    /*-------���6�ֵ�����ĺ���Ϊ��
     * �ֵ�ȾΪ���ڵ����ɫ
     * ���ڵ�Ⱦ��
     * ���ڵ���ת*/
    private void case6(BSTNode N) {
        BSTNode parent = N.parent;
        BSTNode sib = sib(N, parent);
        setColor(sib, colorOf(parent));
        colorBlack(parent);
        if (N.isLeft()) {
            colorBlack(sib.right);
            leftRotate(parent, sib);
        } else {
            colorBlack(sib.left);
            rightRotate(parent, sib);
        }
    }

    private void setColor(BSTNode sib, boolean colorOf) {
        if (sib != null)
            sib.isRed = colorOf;
    }

    private BSTNode rightOf(BSTNode parent) {
        return parent.right;
    }

    private BSTNode leftOf(BSTNode parent) {
        return parent.left;
    }

    private boolean colorOf(BSTNode x) {
        if (x == null)
            return false;
        return x.isRed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<List<BSTNode<K, V>>> lists = super.levelOrder();
        for (List<BSTNode<K, V>> l : lists) {
            for (BSTNode<K, V> n : l) {
                sb.append(n.toString() + "\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
