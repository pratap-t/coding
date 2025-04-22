// Iterative C++ program to find length
// or count of nodes in a linked list

#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node *next;

    Node(int new_data) {
      data = new_data;
      next = nullptr;
    }
};

int countNodes(Node *head) {
  if (head == NULL) {
    return 0;
  }

  return 1 + countNodes(head->next);
}

int main() {
  Node *head = new Node(100);
  head->next = new Node(200);
  head->next->next = new Node(300);
  head->next->next->next = new Node(400);
  head->next->next->next->next = new Node(500);

  cout << "Count of nodes is " << countNodes(head);
  cout << "\n";

  return 0;
}
