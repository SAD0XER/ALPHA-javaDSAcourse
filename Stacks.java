import java.util.*;

public class Stacks {

    //Implementation of Stack using ArrayList.
    static class StackAL {
        static ArrayList<Integer> list = new ArrayList<>();

        //isEmpty(): To know List is Empty or Not.
        public static boolean isEmpty() {
            return list.size() == 0;
        }

        //Push(): To Add elements in Stack.
        public static void push(int data) {
            list.add(data);
        }

        //Pop(): To Remove element from stack.
        public static int pop() {
            //Corner case for Empty stack.
            if (isEmpty()) return -1;

            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }

        //Peek(): To see which elements on the top.
        public static int peek() {
            //Corner case for Empty stack.
            if (isEmpty()) return -1;
            return list.get(list.size() - 1);
        }
    }

    //Implementation of Stack using Linked List.
    static class Node {
        int data;
        Node next;

        //Constructor.
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class StackLL {
        static Node head = null;

        //isEmpty(): To know Linked List is Empty or Not.
        public static boolean isEmpty() {
            return head == null;
        }

        //Push(): To Add elements in Linked List.
        public static void push(int data) {
            Node newNode = new Node(data);

            if (isEmpty()) {
                head = newNode;
                return;
            }

            newNode.next = head;
            head = newNode;
        }

        //Pop(): To Remove element from Linked List.
        public static int pop() {
            if (isEmpty()) return -1;

            int top = head.data;
            head = head.next;
            return top;
        }

        //Peek(): To see which elements on the top.
        public static int peek() {
            if (isEmpty()) return -1;

            return head.data;
        }
    }

    //Question 1: Push at the Bottom of the Stack. O(n)
    public static void pushAtBottom(Stack<Integer> stack, int data) {
        //Base Case: If Stack is Empty then Add/Push data directly.
        if (stack.isEmpty()) {
            stack.push(data);
            return;
        }
        //Work
        int top = stack.pop(); //Storing Top most element.
        pushAtBottom(stack, data); //Recursive Calling for next element.
        stack.push(top); //Adding those removed element again.
    }

    //Question 2: Reverse a String using Stack.
    public static String reverseString(String str) {
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        while (idx < str.length()) {
            stack.push(str.charAt(idx));
            idx++;
        }

        StringBuilder result = new StringBuilder("");
        while (!stack.isEmpty()) {
            char curr = stack.pop();
            result.append(curr);
        }

        return result.toString();
    }

    //Question 3: Reverse a Stack. O(n)
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return; //If Stack is Empty.

        int top = stack.pop(); //1st. popping all elements 1 by 1.
        reverseStack(stack); //2nd. Recursive Calling.
        pushAtBottom(stack, top); //3rd. Pushing elements at Bottom.
    }

    //Question 4: Stock Span Problem. O(n)
    public static void stocksSpan(int[] stocks, int[] span) {
        Stack<Integer> stack = new Stack<>();
        span[0] = 1;
        stack.push(0);

        for (int i = 1; i < stocks.length; i++) {
            int currPrice = stocks[i];
            while (!stack.isEmpty() && currPrice > stocks[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                span[i] = i + 1;
            } else {
                int prevHigh = stack.peek();
                span[i] = i - prevHigh;
            }

            stack.push(i);
        }
    }

    public static void printStack(Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    //Valid Parentheses Code. O(n)
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // Opening condition
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                // Closing condition
                if (stack.isEmpty()) return false;

                if ((stack.peek() == '(' && ch == ')') /*()*/
                        || (stack.peek() == '[' && ch == ']') /*[]*/
                        || (stack.peek() == '{' && ch == '}') /*{}*/) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] para_coder) {
//        StackLL stack = new StackLL();
        /*Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        reverseStack(stack);
        printStack(stack);*/

//        pushAtBottom(stack, 4);

        /*while (!stack.isEmpty()) {
            System.out.println(stack.peek()); //Printing the TOP element.
            stack.pop(); //After printing, Removing TOP element.
        }*/

        /*String str = "SAD";
        String result = reverseString(str);
        System.out.println("Result = " + result);*/

        /*int[] stocks = {100, 80, 60, 70, 60, 85, 100};
        int[] span = new int[stocks.length];
        stocksSpan(stocks, span);

        for(int i = 0; i < span.length; i++) {
            System.out.println(span[i] + " ");
        }*/

        /*Next Greater Element: This question has 4 forms.
        1. Next Greater at Right Side (Backward Loop and <=)
        2. Next Greater at Left Side (Forward Loop and <=)
        3. Next Smallest at Right Side (Backward Loop and >=)
        4. Next Smallest at Left Side (Forward Loop and >=)*/
        /*int[] arr = {98, 76, 54, 32, 10}, nextGreater = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
//        int[] nextGreater = new int[arr.length];

        //Backward loop.
        for (int i = arr.length - 1; i >= 0; i--) {
            //1. While loop
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            //if-else checking.
            if (stack.isEmpty()) {
                nextGreater[i] = -1;
            } else {
                nextGreater[i] = arr[stack.peek()];
            }

            //pushing in stack.
            stack.push(i);
        }

        for (int i = 0; i < nextGreater.length; i++) {
            System.out.print(nextGreater[i] + " ");
        }*/

        //Valid Parentheses. O(n)
        String str = "}{"; //true
        System.out.println(isValid(str));
    }
}