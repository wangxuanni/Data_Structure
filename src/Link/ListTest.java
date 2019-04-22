package Link;

import java.util.HashSet;
//链表常见题目
public class ListTest {
    public static void main(String[] args) {
//　{7,4,0,7,5},{2,7,2,3,4}   返回：{9,1,3,0,0,1}
        LinkList in = new LinkList();
        in.add(7);
        in.add(4);
        in.add(0);
        in.add(7);
        in.add(5);
        LinkList in2 = new LinkList();
        in2.add(2);
        in2.add(7);
        in2.add(7);
        in2.add(7);
        in2.add(7);
        in2.add(2);
        in2.add(3);
        in2.add(4);
        deleteCopy(in2);
        System.out.println(in2);

    }//1、两个链表加法

    static void deleteCopy(LinkList ll) {
        HashSet hs = new HashSet();
        Node p = ll.head;
        Node pre = p;
        while (p.next != null) {
            if (hs.contains(p.data)) {
                if (p == ll.head) {
                    ll.head = ll.head.next;
                } else {
                    pre.next = p.next;
                }
            }else {
                hs.add(p.data);
                pre=p;
            }
           // pre=p;
            p = p.next;
        }
    }

    static Node plusAB(Node a, Node b, int i) {
        if (a == null && b == null && i == 0) {
            return null;
        }
        int result = i;
        if (a != null) {
            result += a.data;
        }
        if (b != null) {
            result += b.data;
        }
        Node r = new Node();
        r.data = result % 10;

        r.next = plusAB(a == null ? null : a.next, b == null ? null : b.next, result >= 10 ? 1 : 0);
        return r;
    }

    static Node huan(Node head) {
        Node f = head;
        Node s = head;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                break;
            }
        }
        if (f == null || f.next == null) {
            return null;
        }
        Node p = head;
        while (p != s) {
            s = s.next;
            p = p.next;
        }
        return s;
    }

    static int huanHS(Node head) {
        HashSet hs = new HashSet();
        Node p = head;
        while (true) {
            if (hs.contains(p.data)) {
                return p.data;
            } else {
                hs.add(p.data);
                p = p.next;
            }
        }
    }


    static Node partionByE(int i, LinkList ll) {
        Node p = ll.head;
        Node leftFirst = null;
        Node leftTail = null;
        Node rightFirst = null;
        Node rightTail = null;
        while (p != null) {
            int pValue = p.data;
            if (pValue < i) {
                if (leftFirst == null) {
                    leftFirst = p;
                    leftTail = p;
                } else {
                    leftTail.next = p;
                    leftTail = leftTail.next;
                }
            } else {
                if (rightFirst == null) {

                    rightFirst = p;
                    rightTail = p;
                } else {
                    rightTail.next = p;
                    rightTail = rightTail.next;
                }
            }
            p = p.next;
        }
        if (leftFirst == null) {
            return rightFirst;
        }
        leftTail.next = rightFirst;
     /*
      为什么要处理尾指针？
      if (rightTail != null) {
            rightTail.next = null;
        }*/
        return leftFirst;
    }


    static void deleteSomeNode(Node n) {
        if (n.next == null) {
            return;//n是最后一个，没法删
        }
        n.data = n.next.data;
        n.next = n.next.next;
    }

    static Object showK(int k, LinkList ll) {
        if (k > ll.size - 1 || k <= 0) {
            return null;
        }
        int count = 0;
        Node q = ll.head;//快指针
        Node s = ll.head;
        while (count < k) {
            q = q.next;
            count++;
        }
        while (q != null) {
            q = q.next;
            s = s.next;
        }
        return s.data;
    }

}
