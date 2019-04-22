package MyJavaApi;
//未实现红黑树的HashMap
public class MyHashMap<K, V> {


    public static void main(String[] args) {
        MyHashMap m = new MyHashMap();
        //   HashMap m1=new HashMap();

        m.put("2", 39);
        m.put("3", 119);
        m.put("2", 19);
        m.put("2", 49);
        m.put("2", 3);
        m.put("4", 3);
        m.remove("2");
        System.out.println(m.containValue(2));
        System.out.println(m.toString());
    }

    Node[] bucket = new Node[16];
    int size = 0;


    public class Node<K, V> {
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object valus) {
            this.key = key;
            this.value = valus;
        }
    }

    public boolean containKey(K key) {
        int index = hash(key);
        if (bucket[index] == null) {
            return false;
        }
        if (bucket[index].key==key){
            return true;
        }
        Node p = bucket[index];
        while (p.next != null) {
            if (p.next.key==key) {
                return true;
            }
                p=p.next;
        }
        return false;
    }
    public boolean containValue(V value) {
        for (Node node : bucket) {
            if (node != null) {
                if (node.value == value) {
                    return true;
                }
                while (node.next != null) {
                    if (node.next.value == value) {
                        return true;
                    }
                    node = node.next;
                }
                return false;
            }}
            return false; }


    public void remove(K key) {
        int index = hash(key);
        if (bucket[index] == null) {
            return;
        }
        Node p = bucket[index];
        Node pre = p;//注意这里
        while (p.next != null) {
            if (p.key == key) {
                if (p == bucket[index]) {
                    bucket[index] = p.next;
                } else {
                    pre.next = p.next;
                }
            }
            pre = p;
            p = p.next;

        }
        bucket[index] = null;
    }

    public int hash(K key) {
        return key.hashCode() % 15;
    }


    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        int index = hash(key);
        if (bucket[index] == null) {
            bucket[index] = node;
            size++;
        } else {
            Node p = bucket[index];
            while (p.next != null) {
                if (p.key == node.key && p.equals(node)) {
                    p.value = node.value;
                    break;
                }
                p = p.next;
            }
            p.next = node;
            size++;
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length; i++) {
            Node n = bucket[i];
            if (n != null) {
                sb.append("key:" + n.key + " value:" + n.value + "\n");
                while (n.next != null) {
                    n = n.next;
                    sb.append("key:" + n.key + " value:" + n.value + "\n");
                }
            }
        }
        return sb.toString();
    }
}
