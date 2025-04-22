// JavaScript Program to insert the node at the beginning of 
// Linked List

class Node {
    constructor(newData)
    {
        // initialize the node's data
        this.data = newData;

        // Set the next pointer to null
        this.next = null;
    }
}

// Functio to insert a new node at the beginning of the 
// list
function insertAtFront(head, newData)
{
    // Create a new node with the given data
    const newNode = new Node(newData);

    // Make the next of the new node point to the current 
    // head
    newNode.next = head;

    // Return the new nodeas the new head of the list
    return newNode;
}

// Function to print the contents of the linked list
function printList(head)
{
    // Start from the head of the list
    let curr = head;
    let result = "";

    // Traverse the List
    while (curr != null) {
        // Append the current node's data to result
        result += " " + curr.data;

        // Move to the next node
        curr = curr.next;
    }
    // Print the result
    console.log(result);
}

// Driver code to test the function
function main()
{
    // Create the linked list 2->3->4->5
    let head = new Node(2);
    head.next = new Node(3);
    head.next.next = new Node(4);
    head.next.next.next = new Node(5);

    // Print the original List
    console.log("Original Linked List:");
    printList(head);

    // Insert a new node at the front of the List
    console.log("After inserting Nodes at the front:");
    const data = 1;
    head = insertAtFront(head, data);

    // Print the updated List
    printList(head);
}

main(); // Execute the driver code