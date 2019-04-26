package MyTree;

import java.util.List;
import java.util.function.Consumer;

//����������ӿ�
    public interface IBinarySearchTree<K, V> {
        /**
         * �����ڵ�
         * @param k �ؼ���
         * @param v ֵ
         */
        BSTNode<K, V> insert(K k, V v);

        /**
         * �������
         * @param con �������������ÿ��Ԫ�صĺ���
         */
        void inorder(Consumer<K> con);

        /**
         * ����Ԫ��
         * @param key
         * @return
         */
        V lookupValue(K key);

        /**
         * ��ȡ��С�ؼ���
         * @return
         */
        K min();

        /**
         * ��ȡ���ؼ���
         * @return
         */
        K max();

        /**
         * �Ƴ��ؼ��ֶ�Ӧ�Ľڵ�
         * @param key
         */
        void remove(K key);

        /**
         * x�ĺ�̡�����x��ĵ�һ��Ԫ�� 1����������������Сֵ
         * 2��û����������������׷�ݣ�ֱ��ĳ�����Ƚڵ������ӣ�����������Ƚڵ�ĸ��ڵ㣬������x�ĺ��
         *
         * @param x
         * @return
         */
        K successor(K x);

        /**
         * ǰ��
         * @param x �ؼ���
         * @return
         */
        K predecessor(K x);

        boolean isBalance();

        /**
         * ���ؽڵ���
         * @return
         */
        int getSize();

        /**
         * �߶�
         * @return
         */
        int getHeight();

        List<List<BSTNode<K, V>>> levelOrder();
    }
