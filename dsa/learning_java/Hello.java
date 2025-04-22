public class Hello {
    // Function to insert a node into an empty
    // circular singly linked list
    static Node insertInEmptyList(Node last, int data) {
        if (last != null) return last;

        // Create a new node
        Node newNode = new Node(data);

        // Point newNode to itself
        newNode.next = newNode;

        // Update last to point to the new node
        last = newNode;
        return last;
    }

    // Function to print the list
    static void printList(Node last) {
        if (last == null) return;

        // Start from the head node
        Node head = last.next;
        while (true) {
            System.out.print(head.data + " ");
            head = head.next;
            if (head == last.next) break;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node last = null;

        // Insert a node into the empty list
        last = insertInEmptyList(last, 1);

        // Print the list
        System.out.print("List after insertion: ");
        printList(last);
    }
}
