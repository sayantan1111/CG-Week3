public class CustomHashMap {
    private static class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] table;
    private int capacity;

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Node[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, int value) {
        int index = hash(key);
        Node head = table[index];
        Node newNode = new Node(key, value);

        if (head == null) {
            table[index] = newNode;
            return;
        }

        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }
        prev.next = newNode;
    }

    public Integer get(String key) {
        int index = hash(key);
        Node head = table[index];

        Node current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(String key) {
        int index = hash(key);
        Node head = table[index];

        if (head == null) {
            return;
        }

        if (head.key.equals(key)) {
            table[index] = head.next;
            return;
        }

        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}