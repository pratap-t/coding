class Node {
    int data;
    Node next;

    Node(int new_data) {
        data = new_data;
        next = null;
    }
}

public class insertNode {
    // Insert a Node after a given Node in Linked List
    public static Node insertAfter(Node head, int newdata, int key) {
        Node curr = head;

        while (curr != null) {
            if(curr.data == key)
                break;
            curr = curr.next;
        }

        if (curr == null) {
            System.out.println("Node not found");
            return head;
        }

        Node newNode = new Node(newdata);
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }
    // Insert a Node at front of a Linked List
    public static Node insertAtFront(Node head, int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;

        return new_node;
    }
    // Insert a Node before a Given Node in Linked List
    public static Node insertBeforeNode(Node head, int newdata, int key) {

        Node curr = head;
        Node prev = head;
        while (curr != null && curr.data != key) {
            prev = curr;
            curr = curr.next;            
        }

        if (curr != null) {
            Node new_Node = new Node(newdata);
            prev.next = new_Node;
            new_Node.next = curr;
        }
        
        return head;
    }
    // Insert a node at a specific position in a linked list
    public static Node insertAtSpecificPosition(Node head, int newData, int position) {
        if (position < 1)
            return head;
        if (position == 1) {
            Node newNode = new Node(newData);
            newNode.next = head;
            return newNode;
        }
        Node curr = head;
        for(int i = 1; i < position - 1 && curr != null; i++) {
            curr = curr.next;
        }
        if (curr == null)
            return head;
        Node newNode = new Node(newData);
        newNode.next = curr.next;
        curr.next = newNode;

        return head;
    }
    // Insert a Node at the End of Linked List
    public static Node insertAtEnd(Node head, int newData) {
        Node NewNode = new Node(newData);
        if (head == null) {
            return NewNode;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = NewNode;
        return head;
    }
    // Print all nodes of Linked List
    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(" " + curr.data);
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(3);
        head.next.next = new Node(4);
        head.next.next.next = new Node(5);

        System.out.println("original list");
        printList(head);
        System.out.println("After inserting node at the front");
        head = insertAtFront(head, 200);
        printList(head);
        System.out.println("After inserting node after a key");
        head = insertAfter(head, 1234, 3);
        printList(head);
        System.out.println("Inserting node before a key");
        head = insertBeforeNode(head, 4321, 2);
        printList(head);
        System.out.println("Insert a node at a specific position");
        head = insertAtSpecificPosition(head, 9876, 2);
        printList(head);
        System.out.println("Insert a node at end of a linked list");
        head = insertAtEnd(head, 1111);
        printList(head);
    }
}