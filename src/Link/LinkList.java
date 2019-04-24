package Link;
//¡¥±Ì¿‡
public class LinkList<T> implements MyList<T> {
    Node head;
    Node last;
    int size = 0;

    public static void main(String[] args) {
        LinkList in = new LinkList();
        in.add(1);
        in.add(2);
        in.add(3);
        in.add(4);

        in.deleteByIdx(0);

        System.out.println(in);
    }

    public LinkList() {
    }


    @Override
    public void add(int data) {
        if (head == null) {
            head = new Node(data);
            size++;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new Node(data);
            size++;
        }
    }


    @Override
    public void deleteByEle(int element) {
        Node p = head;
        Node pre = head;
        while (p != null) {
            if (element == p.data) {
                if (p == head) {
                    head = head.next;
                } else {
                    pre.next = p.next;
                    break;
                }
            }
            pre = p;
            p = p.next;
        }
        size--;
    }

    @Override
    public void deleteByIdx(int index) {
        if (index < 0 || index > size - 1) {
            return;
        }
        Node p = head;
        Node pre = head;
        int i = 0;
        while (p != null) {
            if (i == index) {
                if (p == head) {
                    head = head.next;
                }
                pre.next = p.next;
                break;
            }
            pre = p;
            p = p.next;
            i++;
        }
        size--;

    }

    @Override
    public boolean contains(int target) {
        return false;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        while (head.next != null) {
            sb.append(head.data + ",");
            head = head.next;
        }
        sb.append(head.data);

        return String.valueOf(sb);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
