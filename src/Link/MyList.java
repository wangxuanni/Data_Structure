package Link;

import java.util.Iterator;

/**
 * @parameter:
 * @return:
 * @description:链表类接口，约定方法
 * @author: Wang
 * @create: 2019-04-11 19:05
 **/
public interface MyList<T> extends Iterator<T> {
    void add(int element);

    /**
     * 删除相同元素
     */
    void deleteByEle(int element);

    /**
     * 根据索引删除元素
     */
    void deleteByIdx(int index);

    boolean contains(int target);

}
