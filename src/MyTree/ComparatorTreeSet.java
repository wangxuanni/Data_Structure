package MyTree;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorTreeSet {
    public static void main(String[] args) {
        Comparator<Integer> c=new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2) {
                //i1=3,i2=4,加法无论怎么样i1都大。i1-i2是倒序，因为如果顺序正确反而的负数。
                return i2-i1;//负数 1小于2 整数1大于2
            }
        };
        TreeSet<Integer> ts=new TreeSet<>(c);
        for (int i = 0; i < 10 ;i++) {
           ts.add(i);

        }
        System.out.println(ts);
    }
}
