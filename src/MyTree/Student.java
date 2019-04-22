package MyTree;

import java.util.*;
//Ñ§ÉúÀà
public class Student {
    public static void main(String[] args) {
        Student s1=new Student("tom",13);
        Student s2=new Student("rose",4);
        List ss=new ArrayList();
        ss.add(s1);
        ss.add(s2);
        Collections.sort(ss,(Student o1, Student o2)-> o1.rank-o2.rank);
        System.out.println(ss.toString());
    }
    String name;
    int rank;


    public Student(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name ;
    }
}
