package MyStack;
//数组实现队列
public class MyQueue {

    String []sta=new String[7];
    public int length=0;

    public void push(String data){
        sta[length]=data;
        length++;

    }

    public String pop(){
        String getdata = null;
        if(length!=0){
            sta[length-1]=getdata;
            length--;
        }
        else{
            System.out.println("栈内无元素");
        }

        return getdata;
    }

    public void display(){
        if(length!=0){
            System.out.println("栈的内共有元素："+length);
            for(int i=0;i<length;i++){
                System.out.println(sta[i]);//z正着取

            }}
    }
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        MyQueue mq = new MyQueue();
        mq.push("111");
        mq.push("222");
        mq.push("333");

        //mq.pop();

        mq.display();


    }}

