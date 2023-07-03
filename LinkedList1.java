public class LinkedList1 {
    //Created single node in LL.
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

//    Methods of Linked List:

    //Adding Node at first place.
    public void addFirst(int data) {
        //Step 1: Create New Node.
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        //Step 2: newNode next = head (Linking Process)
        newNode.next = head; //link

        //Step 3: head = newNode.
        head = newNode;
    }

    public static void main(String[] para_coder) {
        LinkedList1 ll = new LinkedList1();
        ll.addFirst(1);
        ll.addFirst(2);
    }
}
