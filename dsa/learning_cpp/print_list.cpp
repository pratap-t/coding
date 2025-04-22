#include <bits/stdc++.h>
using namespace std;

class Node{
public:
    int data;
    Node *next;

    Node(int new_data) {
        this->data = new_data;
        this->next = nullptr;
    }
};

void printList(Node *head) {
    while(head != nullptr){
        cout << head->data << " ";
        head = head->next;
    }
}

Node *insertAtFront(Node *head, int new_data) {
    Node *new_node = new Node(new_data);
    new_node->next = head;
    return new_node;
}

int main() {
    Node *head = new Node(100);
    head->next = new Node(200);
    head->next->next = new Node(200);
    head->next->next->next = new Node(500);
    
    printList(head);

    // After inserting new node
    head = insertAtFront(head, 4000);
    printList(head);
}