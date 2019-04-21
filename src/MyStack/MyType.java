package MyStack;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
//改成泛型就是不行
public class MyType {

/*
    static <T>T middle(T[] data) {

        if (data.length >= 0) {
            System.out.println("中间数是：" + data[data.length / 2]);
        }
        return data[data.length / 2];
    }*/
public static int middle(int[] data) {

   return data[data.length / 2];
    }





    public static void main(String[] args) {//忘记加static这种错误是怎么犯的
 int []data =new int[6];
        for (int i = 0; i < data.length; i++) {
            data[i] =i;
        }

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.println();
        }

        MyType m=new MyType();

        System.out.println(MyType.middle(data));//为什么这里不是data[]



    }

}

