class Node {
	int data;
	Node next;
	
	Node(int new_data) {
		data = new_data;
		next = null;
	}

	static int getLength(Node head) {
        int count = 0;
        Node curr = head;
        while(curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }
}