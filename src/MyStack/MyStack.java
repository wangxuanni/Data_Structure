package MyStack;
//数组实现栈
public class MyStack {

    String []sta=new String[7];
    public int length=0;

    public void push(String data){
        sta[length++]=data;


    }

    public String pop(){
      return sta[--length];
    }

    public void display(){
        if(length!=0){
            System.out.println("现在栈的内共有元素："+length);
            for(int i=1;i<=length;i++){             //此处从1开始
                System.out.println(sta[length-i]);//倒着取

            }}
    }
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        MyStack ms = new MyStack();
        ms.push("111");
        ms.push("222");
        ms.push("333");

        String s= ms.pop();
        System.out.println("pop出来的元素为"+s);
        ms.display();



    }
}
