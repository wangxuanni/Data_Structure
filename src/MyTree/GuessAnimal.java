package MyTree;

import java.util.Scanner;
//猜动物类游戏，运行试试
public class GuessAnimal {
    private static Scanner stdin = new Scanner(System.in);

    public static BTNode beginningTree() {
        BTNode root;
        BTNode child;

        root = new BTNode("有四条腿吗", null, null);
        child = new BTNode("有尾巴吗", null, null);
        child.setRight(new BTNode("青蛙", null, null));
        child.setLeft(new BTNode("狗", null, null));
        root.setLeft(child);

        child = new BTNode("在水下生活吗", null, null);
        child.setRight(new BTNode("人", null, null));
        child.setLeft(new BTNode("鱼", null, null));
        root.setRight(child);

        return root;
    }

    public static void play(BTNode current) {
        while (!current.isLeaf()) {
            if (query(current.getData()))
                current = current.getLeft();
            else
                current = current.getRight();
        }
        System.out.println("我猜是" + current.getData());
        if (!query("我猜的对吗"))
            learn(current);                         //把我猜错的那个动物传入learn函数里，current
        else
            System.out.println("hahaha我知道一切！");


    }

    public static void learn(BTNode current) {
        String guessAnimal;
        String correctAnimal;
        String newQuestion;
        guessAnimal = current.getData();             //把猜错的动物保存到一个string里
        System.out.println("我输啦，是什么动物？");
        correctAnimal = stdin.nextLine();           //把答案保存到一个string里
        System.out.println("请给出问题来分别" + correctAnimal + "和" + guessAnimal);
        newQuestion = stdin.nextLine() + "吗";

        current.setData(newQuestion);
        System.out.println(correctAnimal + "," + newQuestion);
        if (query("请回答")) {//如果这个问题yes是正确答案，则把它放到左边

            current.setLeft(new BTNode(correctAnimal, null, null));
            current.setRight(new BTNode(guessAnimal, null, null));
        } else {
            current.setRight(new BTNode(correctAnimal, null, null));
            current.setLeft(new BTNode(guessAnimal, null, null));
        }

        System.out.println("谢谢你教给我这个");

    }

    public static boolean query(String prompt) {
        //传入问题，加上"Y or N"，并且得到答案不分大小写的传回去
        String answer;
        System.out.println(prompt + "Y or N");
        answer = stdin.nextLine().toUpperCase();
        return answer.startsWith("Y");

    }

    public static void main(String[] args) {
        BTNode root;
        root = beginningTree();

        do {
            play(root);
        }
        while (query("你想再来玩一次吗"));

        System.out.println("那好，再见");
    }

}
