package MyStack;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
public class MyType {

public static int middle(int[] data) {

   return data[data.length / 2];
    }



    public static void main(String[] args) {
 int []data =new int[6];
        for (int i = 0; i < data.length; i++) {
            data[i] =i;
        }

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.println();
        }

        MyType m=new MyType();

        System.out.println(MyType.middle(data));



    }

}

