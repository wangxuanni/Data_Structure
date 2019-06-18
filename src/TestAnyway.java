import java.util.Arrays;

//临时测试类
public class TestAnyway {
    public static void main(String[] args) {
        int[] array={3,4,5,1,2};
        int min=0;
        int max=array.length-1;
        while(min<max){
            int mid=(min+max)/2;
            if(min==max-1){
                break;
            }else if(array[mid]>array[max]){
                min=mid;
            }else if(array[mid]<array[max]){

                max=mid;
            }
        }
        System.out.println(array[max]);
    }

}