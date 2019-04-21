package Link;

import java.util.Iterator;

/**
 * @parameter:
 * @return:
 * @description:
 * @author: Wang
 * @create: 2019-04-11 19:05
 **/
public interface MyList<T> extends Iterator<T> {
    void add(int element);

    /**
     * ɾ����ͬԪ��
     */
    void deleteByEle(int element);

    /**
     * ��������ɾ��Ԫ��
     */
    void deleteByIdx(int index);

    boolean contains(int target);

}
