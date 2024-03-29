import java.util.*;

public class Queues {
    static class QueueArr {
        static int[] arr;
        static int size;
        static int rear;
        static int front;

        //Constructor
        QueueArr(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        //isEmpty Method: To check queue is Empty or NOT.
        public static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        //isFull Method: To check Circular Queue is Full or NOT.
        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        //Add (Enque) Method. O(1)
        public static void add(int data) {
            if (isFull()) {
                System.out.println("Queue is Full.");
                return;
            }
            //Case: while Adding First element.
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        //Remove (Dequeue) Method. O(1)
        public static int remove() {
            if (isEmpty()) {
                System.out.print("Queue is Empty.");
                return -1;
            }
            int result = arr[front];

            //Case: when Removing Last element.
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }

        //Peek O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.print("Queue is Empty.");
                return -1;
            }
            return arr[front];
        }
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class QueueLL {
        static Node head = null;
        static Node tail = null;

        //isEmpty Method: To check queue is Empty or NOT.
        public static boolean isEmpty() {
            return head == null && tail == null;
        }

        //Add (Enque) Method. O(1)
        public static void add(int data) {
            Node newNode = new Node(data);
            //Case: When there is NO any Node in LL.
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }

        //Remove (Dequeue) Method. O(1)
        public static int remove() {
            if (isEmpty()) {
                System.out.print("Queue is Empty.");
                return -1;
            }
            int front = head.data;
            //Case: When there is single element in LL.
            if (tail == head) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return front;
        }

        //Peek O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.print("Queue is Empty.");
                return -1;
            }
            return head.data;
        }
    }

    static Stack<Integer> s1 = new Stack<>();
    static Stack<Integer> s2 = new Stack<>();

    //isEmpty
    public static boolean isEmpty() {
        return s1.isEmpty();
    }

    //Add: O(n)
    public static void add(int data) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        s1.push(data);

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    //Remove: O(1)
    public static int remove() {
        if (isEmpty()) {
            System.out.println("Queue is Empty.");
            return -1;
        }

        return s1.pop();
    }

    //Peek: O(1)
    public static int peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty.");
            return -1;
        }
        return s1.peek();
    }

    static class Stacks {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        //isEmpty
        public static boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        //Push: O(1)
        public static void push(int data) {
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        //Pop: O(n)
        public static int pop() {
            if (isEmpty()) {
                System.out.println("Stack is Empty.");
                return -1;
            }

            int top = -1;

            if (!q1.isEmpty()) { //Case 1
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else { //Case 2
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }
            }
            return top;
        }

        //Peek: O(n)
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty.");
                return -1;
            }

            int top = -1;

            if (!q1.isEmpty()) { //Case 1
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else { //Case 2
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }
            }
            return top;
        }
    }

    //First non-repeating letter in a stream of a character using queues. O(n)
    public static void printNonRepeating(String str) {
        int[] freq = new int[26]; //'a' - 'z'
        Queue<Character> q = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            q.add(ch);
            freq[ch - 'a']++;

            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }

            if (q.isEmpty()) {
                System.out.println(-1 + " ");
            } else {
                System.out.println(q.peek() + " ");
            }
        }
        System.out.println();
    }

    //Interleave 2 Halves of a Queue (even length). TC: O(n) & SC: O(n)
    public static void interLeave(Queue<Integer> q) {
        Queue<Integer> firstHalf = new LinkedList<>();
        int size = q.size();

        for (int i = 0; i < size / 2; i++) {
            firstHalf.add(q.remove()); //Adding element in 2nd queue.
        }

        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.remove()); //Adding element from 2nd queue to main queue.
            q.add(q.remove()); //Adding element from main queue to itself again.
        }
    }

    //Queue Reversal. O(n)
    public static void reverse(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();

        while (!q.isEmpty()) {
            s.push(q.remove());
        }

        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    //Implement Stack using Deque.
    static class DequeStack {
        Deque<Integer> deque = new LinkedList<>();

        //push: O(1)
        public void push(int data) {
            deque.addLast(data); //adding data from last side.
        }

        //pop: O(1)
        public int pop() {
            return deque.removeLast(); //removing data from last side.
        }

        //peek: O(1)
        public int peek() {
            return deque.getLast(); //getting data from first side.
        }
    }

    //Implement Queue using Deque.
    static class DequeQueue {
        Deque<Integer> deque = new LinkedList<>();

        //Add: O(1)
        public void add(int data) {
            deque.addLast(data); //adding data from last side.
        }

        //Remove: O(1)
        public int remove() {
            return deque.removeFirst(); //removing data from last side.
        }

        //Peek: O(1)
        public int peek() {
            return deque.getFirst(); //getting data from first side.
        }
    }

    public static void main(String[] para_coder) {
//        QueueLL q = new QueueLL(); //Here we've defined size of the array.

        /*FYI: Queue is an Interface so, we cannot create object of it. So, we need any other class to implement it.
         * There are two classes in Java who can implement Queue interfaces. One is Linked List and Second is ArrayDeque.*/

//        Queue<Character> q = new LinkedList<>();
//        Queue<Character> q = new ArrayDeque<>();
//        Queues q = new Queues();
        /*Stacks s = new Stacks();
        s.push(1);
        s.push(2);
        s.push(3);

        while (!s.isEmpty()) {
            System.out.println(s.peek());
            s.pop();
        }*/

        //First non-repeating letter in a stream of a character using queues. O(n)
//        String str = "aabccxb";
//        printNonRepeating(str);

        //Interleave 2 Halves of a Queue (even length). TC: O(n) & SC: O(n)
        //Queue Reversal. O(n)

        /*Deque<Integer> deque = new LinkedList<>(); //Remember: Deque is an Interface, which is implemented by other classes like Liked List.
        deque.addFirst(1); //1
        deque.addFirst(2); //2, 1
        deque.addFirst(3); //3, 2, 1
        deque.addLast(3); //3, 2, 1, 3
        deque.addLast(4); //3, 2, 1, 3, 4
        System.out.println(deque);
        deque.removeLast(); //3, 2, 1, 3
        System.out.println(deque);
        deque.removeFirst(); //2, 1, 3
        System.out.println(deque);
        System.out.println("First Element: " + deque.getFirst()); //2
        System.out.println("Last Element: " + deque.getLast()); //3*/

//        interLeave(q);
//        reverse(q);

        /*while (!q.isEmpty()) {
            System.out.print(q.remove() + " ");
        }*/

        //Implement Stack using Deque.
        /*DequeStack s = new DequeStack();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println("Peek: " + s.peek());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());*/

        //Implement Queue using Deque.
        DequeQueue q = new DequeQueue();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println("Peek: " + q.peek());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
