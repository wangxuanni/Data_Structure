package MyTree;

import java.util.Scanner;
//�¶�������Ϸ����������
public class GuessAnimal {
    private static Scanner stdin = new Scanner(System.in);

    public static BTNode beginningTree() {
        BTNode root;
        BTNode child;

        root = new BTNode("����������", null, null);
        child = new BTNode("��β����", null, null);
        child.setRight(new BTNode("����", null, null));
        child.setLeft(new BTNode("��", null, null));
        root.setLeft(child);

        child = new BTNode("��ˮ��������", null, null);
        child.setRight(new BTNode("��", null, null));
        child.setLeft(new BTNode("��", null, null));
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
        System.out.println("�Ҳ���" + current.getData());
        if (!query("�ҲµĶ���"))
            learn(current);                         //���Ҳ´���Ǹ����ﴫ��learn�����current
        else
            System.out.println("hahaha��֪��һ�У�");


    }

    public static void learn(BTNode current) {
        String guessAnimal;
        String correctAnimal;
        String newQuestion;
        guessAnimal = current.getData();             //�Ѳ´�Ķ��ﱣ�浽һ��string��
        System.out.println("����������ʲô���");
        correctAnimal = stdin.nextLine();           //�Ѵ𰸱��浽һ��string��
        System.out.println("������������ֱ�" + correctAnimal + "��" + guessAnimal);
        newQuestion = stdin.nextLine() + "��";

        current.setData(newQuestion);
        System.out.println(correctAnimal + "," + newQuestion);
        if (query("��ش�")) {//����������yes����ȷ�𰸣�������ŵ����

            current.setLeft(new BTNode(correctAnimal, null, null));
            current.setRight(new BTNode(guessAnimal, null, null));
        } else {
            current.setRight(new BTNode(correctAnimal, null, null));
            current.setLeft(new BTNode(guessAnimal, null, null));
        }

        System.out.println("лл��̸������");

    }

    public static boolean query(String prompt) {
        //�������⣬����"Y or N"�����ҵõ��𰸲��ִ�Сд�Ĵ���ȥ
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
        while (query("����������һ����"));

        System.out.println("�Ǻã��ټ�");
    }

}
