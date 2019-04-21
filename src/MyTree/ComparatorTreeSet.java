package MyTree;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorTreeSet {
    public static void main(String[] args) {
        Comparator<Integer> c=new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2) {
                //i1=3,i2=4,�ӷ�������ô��i1����i1-i2�ǵ�����Ϊ���˳����ȷ�����ĸ�����
                return i2-i1;//���� 1С��2 ����1����2
            }
        };
        TreeSet<Integer> ts=new TreeSet<>(c);
        for (int i = 0; i < 10 ;i++) {
           ts.add(i);

        }
        System.out.println(ts);
    }
}
