import java.util.Stack;

public class insertNodePractice {

    // #1. Insert a Node at front of Linked List
    public static Node insertAtFront(Node head, int newdata) {
      Node newNode = new Node(newdata);
      newNode.next = head;
      return newNode;
    }
    
    // #2. Insert a Node after a given Node in a Linked List
    public static Node insertAfterNode(Node head, int newdata, int key) {
      Node current = head;
      while (current != null) {
        if (current.data == key)
          break;
        current = current.next;
      }
      
      if (current == null){
        System.out.println("Node not found");
        return head;
      }
      
      Node newNode = new Node(newdata);
      newNode.next = current.next;
      current.next = newNode;
      return head;
    }
    
    // #3. Insert a Node before a given Node in a Linked List
    public static Node insertBeforeNode(Node head, int newdata, int key) {
      Node current = head;
      Node prev = head;
      
      while(current != null && current.data != key) {
        prev = current;
        current = current.next;
      }
      if (current != null) {
        Node newNode = new Node(newdata);
        prev.next = newNode;
        newNode.next = current;
      }
      return head;
    }
    
    // #4. Insert a Node at specific position in a Linked List
    public static Node insertSpecificNode(Node head, int newdata, int pos) {
      if (pos < 1)
        return head;
      if (pos == 1) {
        Node newNode = new Node(newdata);
        newNode.next = head;
        return newNode;
      }
      Node curr = head;
      for(int i = 1; i < pos - 1 && curr != null; i++) {
        curr = curr.next;
      }
      if (curr == null)
        return head;
      Node newNode = new Node(newdata);
      newNode.next = curr.next;
      curr.next = newNode;
      
      return head;
    }
    
    // #5. Insert a Node at the end of a Linked List
    public static Node insertAtEndNode(Node head, int newdata) {
      Node newNode = new Node(newdata);
      if(head == null) {
        return newNode;
      }
      Node last = head;
      while(last.next != null) {
        last = last.next;
      }
      last.next = newNode;
      return head;
    }
    
    // #6. Search whether key is present in Linked List
    static boolean SearchKey(Node head, int key) {
      Node current = head;
      while (current != null) {
        if (current.data == key) 
          return true;
        current = current.next;
      }
      System.out.println();
      return false;
    }
    
    // #7. Search whether key is present in Linked List using recursion
    static boolean SearchKeyRecursive(Node head, int key) {
      if (head == null)
        return false;

      // If key is present in current node, return true
      if (head.data == key)
        return true;

      // recur for remaining list
      return SearchKeyRecursive(head.next, key);
    }
    
    // #8. Iterative java function to Count the number of nodes in Linked List
    // This method is part of Node.java
    
    // #9. Recursive java function to count the number of nodes
    public static int countNodesRecursive(Node head) {
      if (head == null) {
        return 0;
      }
      return 1 + countNodesRecursive(head.next);
    }

    // 10. Print all nodes of a Linked List
    public static void printList(Node head) {
      Node current = head;
      while (current != null) {
        System.out.println(" " + current.data);
        current = current.next;
      }
      System.out.println();
    }
    
    // #11. Reverse a Linked List

    static Node reversedLinkedList(Node head) {
      
      // Initialize Nodes curr, prev, next
      Node curr = head, prev = null, next1;

      // Traverse Linked List
      while (curr != null) {
        next1 = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next1;
      }
      return prev;
    }

    // #12. Given the head of a list, reverse the list using recursion
    // and return the head of reversed list
    static Node reverseListRecursive(Node head) {

      // If we have reached last node or linked
      // list is empty, return head of linked list
      if (head == null || head.next == null)
          return head;

      // reverse the rest of linked list and put 
      // the first element at the end
      Node rest = reverseListRecursive(head.next);

      // Make the current head as last node of 
      // remaining linked list
      head.next.next = head;

      // Update next of current head to NULL
      head.next = null;

      // Return the reversed linked list
      return rest;
    }

    // #13. Java method to reverse Linked List using Stack

    static Node reverseListUsingStack(Node head) {
      
      // Create a stack to store the nodes
      Stack<Node> stack = new Stack<>();
        
        Node temp = head;

        // Push all nodes except the last node into stack
        while (temp != null) {
          stack.push(temp);
          temp = temp.next;
        }
        
        // Make the last node as new head of the Linked List
        if (!stack.isEmpty()) {
          head = stack.pop();
          temp = head;

          // pop all the nodes and append to the Linked List
          while (!stack.isEmpty()) {
            
            // append the top value of stack to Linked List
            temp.next = stack.pop();

            // move to the next node of Linked List
            temp = temp.next;
          }
          temp.next = null;
        }
        return head;
    }

    // #14. Method to delete the head node and return the new head
    static Node deleteHead(Node head) {

      // check if the list is empty
      if (head == null)
        return null;

      // Move the head pointer to the next node
      head = head.next;

      return head;
    }
    
    // #15. Method to delete Node at a specific position
    static Node deleteNode(Node head, int position) {
      Node curr = head, prev = null;

      if (curr == null)
        return head;

      if (position == 1) {
        head = curr.next;
        return head;
      }

      for (int i = 1; i < position && curr !=null; i++) {
        prev = curr;
        curr = curr.next;
      }

      if (curr != null) {
        prev.next = curr.next;
      }
      else {
        System.out.println("Data not present");
      }
      return head;
    }
    
    // #16. Method to remove last node of Linked List
    static Node deleteLastNode(Node head) {
      Node curr = head;
      if (curr == null)
        return null;

      if (curr.next == null)
        return null;

      while(curr.next.next != null) {
        curr = curr.next;
      }
      curr.next = null;
      return head;
    }

    // 17. Method to get nth Node in a Linked List
    static int getNth(Node head, int index) {
      if (head == null)
        return -1;
      if (index == 1)
        return head.data;
      return getNth(head.next, index - 1);
    }

    // #18. Find nth node from end in a Linked List
    static int getNthFromLast(Node head, int N) {
      Node curr = head;
      int count = Node.getLength(head);

      if (count < N)
        return -1;
      for (int i = 1; i < count - N + 1; i++) {
        curr = curr.next;
      }
      return curr.data;
    }

    // #19. Find Nth node from end in a Linked List using one pass
    static int getNthFromLastUsingOnePass(Node head, int N) {
      Node move_ptr = head, ref_ptr = head;
      
      for (int i = 0; i< N; i++)
        move_ptr = move_ptr.next;
      
      if (move_ptr == null)
        return -1;
      
      while (move_ptr != null) {
        move_ptr = move_ptr.next;
      ref_ptr = ref_ptr.next;
      }
      
      return ref_ptr.data;
    }
    
    // #20. Method to get mmiddle of a Linked List using count method
    static int getMiddle(Node head) {
      int length = Node.getLength(head);
      int mid_index = length / 2;
      Node curr = head;

      while (mid_index > 0) {
        curr = curr.next;
        mid_index--;
      }
      return curr.data;
    }

    // #21. Method to get middle of a Linked List using Hare & Tortoise algorithm
    static int getMiddleUsingHairTortoise(Node head) {
      Node fast_ptr = head, slow_ptr = head;

      while (fast_ptr != null && fast_ptr.next != null) {
        fast_ptr = fast_ptr.next.next;
        slow_ptr = slow_ptr.next;
      }
      return slow_ptr.data;
    }

    // #22. Recursive method to reverse a doubly Linked List
    static DoublyNode reverseDoublyNode(DoublyNode head) {

      if (head == null) return null;
      DoublyNode current = head.prev;
      head.prev = head.next;
      head.next = current;

      if (head.prev == null) {
        return head;
      }
      return reverseDoublyNode(head.prev);
    }

    // #23. Print DoublyLinked List
    static void printListD (DoublyNode head) {
      DoublyNode current = head;
       while (current != null) {
        System.out.print(current.data + " ");
        current = current.next;
       }
    }

    // #24. Two pointer method to reverse Doubly Linked List
    static DoublyNode reverseDoublyNodeusetwopointers(DoublyNode head) {
      DoublyNode prev = null, curr = head;

      if (head == null || head.next ==null) return null;

      while (curr != null) {
        prev = curr.prev;
        curr.prev = curr.next;
        curr.next = prev;

        curr = curr.prev;
      }

      head = prev.prev;

      return head;
    }

    // #25. Rotate a Linked List
    // Given a singly linked list and an integer k, the task is to rotate the linked list to the left by k places.
    static Node rotate(Node head, int k) {
      if (k == 0 || head == null)
        return head;

      for (int i = 0; i < k; ++i) {
        Node curr = head;
        while (curr.next != null) 
          curr = curr.next;

        curr.next = head;
        curr = curr.next;
        head = head.next;
        curr.next = null;
      }
      return head;
    }

    // #26 Circular Linked List - Function to insert node into Linked List
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

    // #27. Function to print the list
    static void printCircularList(Node last) {
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

  // #28. Function to insert a node at the beginning of the circular Linked List
  static Node insertAtBeginning(Node last, int data) {
    Node newNode = new Node(data);

    // If the list is empty, make the new point to
    // itself and set it as last
    if (last == null) {
      newNode.next = newNode;
      return newNode;
    }

    // insert the newNode at the beginning
    newNode.next = last.next;
    last.next = newNode;

    return last;
  }

  // #29. Function to insert a node at the end of a Circular Linked list
  static Node insertAtEnd(Node tail, int data) {
    Node newNode = new Node(data);

    if (tail == null) {
      tail = newNode;
      newNode.next = newNode;
    }
    else {
      // Insert new node after the current tail and
      // update the tail pointer
      newNode.next = tail.next;
      tail.next = newNode;
      tail = newNode;
    }
    return tail;
  }

    // Main
    public static void main(String[] args) {
      Node head = new Node(2);
      head.next = new Node(3);
      head.next.next = new Node(4);

      DoublyNode headD = new DoublyNode(11);
      headD.next = new DoublyNode(12);
      headD.next.prev = headD;
      headD.next.next = new DoublyNode(13);
      headD.next.next.prev = headD.next;
      headD.next.next.next = new DoublyNode(14);
      headD.next.next.next.prev = headD.next.next;

      System.out.println("Original list");
      printList(head);
      System.out.println("Insert Node at front");
      head = insertAtFront(head, 5);
      printList(head);
      System.out.println("Insert Node after Node with data 2");
      head = insertAfterNode(head, 100, 2);
      printList(head);
      System.out.println("Insert Node before Node with data 3");
      head = insertBeforeNode(head, 200, 3);
      printList(head);
      
      // key to search in the Linked List
      int key = 100;
      if (SearchKey(head, key)) 
        System.out.println("Key " + key + " is present");
      else
        System.out.println("Key " + key + "is not present");
      key = 2000;
      if (SearchKeyRecursive(head, key))
        System.out.println("Key " + key + " is present");
      else
        System.out.println("Key " + key + " is not present");

      System.out.println("Count nodes in Linked List " + Node.getLength(head));
      System.out.println("Count nodes in Linked List " + countNodesRecursive(head));
      Node head1 = reversedLinkedList(head);
      printList(head1);
      System.out.println("Recursive method to reverse Linked List");
      // printList(reverseListRecursive(head1));
      System.out.println("Reverse Linked List using Stack");
      Node head2 = reverseListUsingStack(head1);
      printList(head2);
      Node temp = deleteHead(head2);
      printList(temp);
      printList(deleteNode(head, 5));
      printList(deleteLastNode(head));
      System.out.println("Deleted Linked List");
      printList(head);
      // head = null;
      // System.out.println("Linked List before deletion");
      // printList(head);
      System.out.printf("Element at index 3 is %d", getNth(head, 3));
      System.out.println();
      System.out.printf("Element at index 3 is %d", getNthFromLast(head, 3));
      System.out.println();
      System.out.printf("Element at index 3 is %d", getNthFromLastUsingOnePass(head, 3));
      System.out.println();
      System.out.printf("Element at middle node is %d", getMiddle(head));
      System.out.println();
      System.out.printf("Element at middle node is %d", getMiddleUsingHairTortoise(head));
      System.out.println("Original Doubly Linked List");
      printListD(headD);
      System.out.println("Reverse Doubly Linked List");
      DoublyNode revDNode = reverseDoublyNode(headD);
      printListD(revDNode);
      System.out.println("Reverse Doubly Linked List using two pointers");
      DoublyNode revNodeTwoPointer = reverseDoublyNodeusetwopointers(revDNode);
      printListD(revNodeTwoPointer);
      Node llistAfterrotate = rotate(head, 2);
      printList(llistAfterrotate);
      System.out.println("Hi");
      // Node last1 = null;
      // Node last2 = insertInEmptyList(last1, 1);
      // printList(last2);
      Node lastnode = null;

      // Insert a node into the empty list
      lastnode = insertInEmptyList(lastnode, 111);

      // Print the list
      System.out.print("List after insertion: ");
      printCircularList(lastnode);
      
      // Create a circular Linked List
      Node first = new Node(41);
      first.next = new Node(42);
      first.next.next = new Node(43);
      Node last = first.next.next;
      last.next = first;
      System.out.println("Original Circular List");
      printCircularList(last);
      // insert 45 at the beginning of Circular Linked List
      last = insertAtBeginning(last, 45);
      System.out.println("Circular List after inserting 45 at the beginning");
      printCircularList(last);
      // insert 47, 48 at the end of Circular Linked List
      last = insertAtEnd(last, 47);
      last = insertAtEnd(last, 48);
      printCircularList(last);
    }
}