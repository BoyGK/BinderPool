package com.nullpt.lib;

//反转链表
public class MyClass2 {

    static class Node {
        int val;
        Node next;
    }

    public static void main(String[] args) {
        Node head = new Node();
        head.val = 10;
        Node next = head;
        int i = 10;
        while (i-- > 0) {
            Node temp = new Node();
            temp.val = i;
            next.next = temp;
            next = temp;
        }

        head = revs(head);

        while (head.next != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    private static Node revs(Node root) {
        Node last = null;
        Node cur = root;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = last;
            last = cur;
            cur = next;
        }
        return last;
    }
}
