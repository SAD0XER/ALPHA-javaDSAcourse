import java.util.*;

public class BinarySearchTree {

    //Created Node class for BST.
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    //Insert function to insert values in BST.
    public static Node insert(Node root, int value) {
        if (root == null) { //Base Case.
            root = new Node(value);
            return root;
        }

        if (root.data > value) { //If value is < root data -> insert it in left subtree. /*If you want to reverse BST then simply just change the '>' sign to '<'.*/
            root.left = insert(root.left, value);
        } else { //If value is > root data -> insert it in right subtree.
            root.right = insert(root.right, value);
        }

        return root;
    }

    //Inorder function to print BST in order. [Utility Function for insert() method.]
    public static void inorder(Node root) {
        if (root == null) { //Base Case.
            return;
        }

        inorder(root.left); //Go To Left Subtree.
        System.out.print(root.data + " "); //Print the Root.
        inorder(root.right); //Go To Right Subtree.
    }

    //Search in BST. O(H) (PS: H is a Height of a Tree from Root to Leaf)
    public static boolean search(Node root, int key) {
        if (root == null) { //Base Case.
            return false;
        }

        if (root.data == key) { //Checking for if Root == Key
            return true;
        } else if (root.data > key) { //If Root is > Key
            return search(root.left, key); //Go To Left Subtree.
        } else {
            return search(root.right, key); //Go To Right Subtree.
        }
    }

    //Delete a Node. (Important Question)
    public static Node delete(Node root, int value) {

        //Search the value first.
        if (root.data < value) { //If Root Data is < Value.
            root.right = delete(root.right, value); //Go To Left Subtree.
        } else if (root.data > value) { //If Root Data is > Value.
            root.left = delete(root.left, value); //Go To Left Subtree.
        } else { //Case: Where root.data == value. #Voila Case.

            //Case 1: Leaf Node (No Child)
            if (root.left == null && root.right == null) {
                return null;
            }

            //Case 2: Single Child.
            if (root.left == null) { //If Left Child is Null
                return root.right; //Return Right Child Node.
            } else if (root.right == null) { //If Right Child is Null
                return root.left; //Return Left Child Node.
            }

            //Case 3: Two Child. (Both Children)*
            Node inorderSuccessor = findInorderSuccessor(root.right); //Step 1: Find Inorder Successor from Right Subtree.
            root.data = inorderSuccessor.data; //Step 2: Replace value with Root Data with Inorder Successors Data.
            root.right = delete(root.right, inorderSuccessor.data); //Step 3: Delete Inorder Successor Node.
        }
        return root;
    }

    //Utility Function for delete() method.
    public static Node findInorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    //Print in Range.
    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) { //Base Case.
            return;
        }

        if (root.data >= k1 && root.data <= k2) { //If Root lies between k1 & k2.
            printInRange(root.left, k1, k2); //Go To Left.
            System.out.print(root.data + " "); //Print Root Data.
            printInRange(root.right, k1, k2); //Go To Right.
        } else if (root.data < k1) { //If Root Data is < than k1
            printInRange(root.left, k1, k2); //Go To Left.
        } else {
            printInRange(root.right, k1, k2); //Go To Right.
        }
    }

    //Root to Leaf path. (Print)
    public static void printRoot2Leaf(Node root, ArrayList<Integer> path) {
        if (root == null) { //Base Case.
            return;
        }

        path.add(root.data); //Adding Data of Root in ArrayList.
        if (root.left == null && root.right == null) { //If we reach on Leaf Node
            printPath(path); //Print the Path.
        }

        printRoot2Leaf(root.left, path); //Call for Left Subtree.
        printRoot2Leaf(root.right, path); //Call for Right Subtree.
        path.remove(path.size() - 1); //Removing Elements while Backtracking.
    }

    //Utility Function of printRoot2Leaf() method.
    public static void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "->");
        }
        System.out.println("Null");
    }

    //Validate BST: BST must satisfy all the properties of BST.
    public static boolean isValidBST(Node root, Node min, Node max) {
        if (root == null) { //Base Case.
            return true;
        }

        //Checking some Basic Validation Cases.
        if (min != null && root.data <= min.data) { //For Left Subtree: Min = -∞ & Max = Root/Parent
            return false;
        } else if (max != null && root.data >= max.data) { //For Right Subtree: Min = Root/Parent & Max = +∞
            return false;
        }

        return isValidBST(root.left, min, max) && isValidBST(root.right, root, max);
    }

    //Mirror a BST. (Create/Print) O(n)
    public static Node createMirrorBST(Node root) {
        if (root == null) { //Base Case.
            return null;
        }

        //Recursive Calling
        Node leftMirror = createMirrorBST(root.left);
        Node rightMirror = createMirrorBST(root.right);

        //Recursive Work: Swapping Nodes.
        root.left = rightMirror;
        root.right = leftMirror;

        return root;
    }

    //Preorder function to print BST in order. [Utility Function for createMirrorBST() method.]
    public static void preorder(Node root) {
        if (root == null) { //Base Case.
            return;
        }

        System.out.print(root.data + " "); //1st Print the Root.
        preorder(root.left); //2nd Go To Left Subtree.
        preorder(root.right); //3rd Go To Right Subtree.
    }

    //Create Balanced BST of Sorted Array. O(n)
    public static Node createBST(int[] arr, int start, int end) {
        if (start > end) { //Base Case.
            return null;
        }

        //Work: Find mid & Join to Node.
        int middle = (start + end) / 2; //Calculating Mid of array.
        Node root = new Node(arr[middle]); //Creating Node using mid as an index of array.

        root.left = createBST(arr, start, middle - 1); //Recursive call for Left Subtree.
        root.right = createBST(arr, middle + 1, end); //Recursive call for Right Subtree.

        return root;
    }

    /* #Utility Function for createBST() method, Step 1.
    This function will Travers whole given BST in Inorder pattern & it'll create sorted ArrayList.
    Time Complexity: O(n)*/
    public static void getInorder(Node root, ArrayList<Integer> inorder) {
        if (root == null) { //Base Case.
            return;
        }

        getInorder(root.left, inorder); //Go To Left Subtree.
        inorder.add(root.data); //Adding Root Value in a ArrayList.
        getInorder(root.right, inorder); //Go To Right Subtree.
    }

    /* #Utility Function for createBST() method, Step 2.
    This Function Creates Balanced BST of Sorted ArrayList.
    Time Complexity: O(n)*/
    public static Node createBST(ArrayList<Integer> inorder, int start, int end) {
        if (start > end) { //Base Case.
            return null;
        }

        //Work: Find middle & Join to the Node.
        int middle = (start + end) / 2; //Calculating Middle of ArrayList.
        Node root = new Node(inorder.get(middle)); //Creating Node using 'middle' as an index of ArrayList.

        root.left = createBST(inorder, start, middle - 1); //Recursive call for Left Subtree.
        root.right = createBST(inorder, middle + 1, end); //Recursive call for Right Subtree.

        return root;
    }

    //Convert BST to Balanced BST. O(n)
    public static Node balancedBST(Node root) {
        //Step 1: Calculate Inorder Sequence of BST.
        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);

        //Step 2: Convert Sorted Inorder to Balanced BST.
        root = createBST(inorder, 0, inorder.size() - 1);

        return root;
    }

    /*Size of Largest BST in BT [Utility Class for largestBST() method.] (size: count of nodes in Largest BST)
    ALT Q. Alternative Question for this Question is Return the Root Node of Largest BST in BT.
    ANS. Solve problem by creating static Node variable like mazBST & store the Root Node of Largest BST.*/
    static class Info {
        boolean isBST;
        int size;
        int minimum;
        int maximum;

        //Constructor
        public Info(boolean isBST, int size, int minimum, int maximum) {
            this.isBST = isBST;
            this.size = size;
            this.minimum = minimum;
            this.maximum = maximum;
        }
    }

    public static int maxBST = 0;

    public static Info largestBST(Node root) {
        if (root == null) { //Base Case.
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info leftInfo = largestBST(root.left); //Calculating Information from Left Subtree.
        Info rightInfo = largestBST(root.right); //Calculating Information from Right Subtree.

        int size = leftInfo.size + rightInfo.size + 1; //Calculating size for Root.

        int minimum = Math.min(root.data, Math.min(leftInfo.minimum, rightInfo.minimum)); //Comparing Minimum Values from Left and Right Subtrees with Root.
        int maximum = Math.max(root.data, Math.max(leftInfo.maximum, rightInfo.maximum)); //Comparing Maximum Values from Left and Right Subtrees with Root.

        /*For the Root Node: Return False when Data of Root is <= Maximum of Left Subtree OR Data of Root is >= Minimum of Right Subtree.*/
        if (root.data <= leftInfo.maximum || root.data >= rightInfo.minimum) {
            return new Info(false, size, minimum, maximum);
        }

        /*For the Left Subtree & Right Subtree: Is they are a Valid BST?
        * Return True when Both Left & Right Subtree Returns True.*/
        if (leftInfo.isBST && rightInfo.isBST) {
            maxBST = Math.max(maxBST, size); //Before comparing, updating value of maxSizeBST counter.
            return new Info(true, size, minimum, maximum);
        }

        return new Info(false, size, minimum, maximum); //From the all the above conditions if none of any condition gets True then finally Return False.
    }

    //Merge 2 BSTs. Linear Time Complexity: O(n+m)
    public static Node mergeBST(Node root1, Node root2) {
        //Step-1: Get inorder of BST-1.
        ArrayList<Integer> arr1 = new ArrayList<>();
        getInorder(root1, arr1);

        //Step-2: Get inorder of BST-2.
        ArrayList<Integer> arr2 = new ArrayList<>();
        getInorder(root2, arr2);

        //Step-3: Merge Array-1 and Array-2.
        ArrayList<Integer> finalArray = new ArrayList<>();
        int i = 0, j = 0; //Two pointers for tracking.
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                finalArray.add(arr1.get(i));
                i++;
            } else {
                finalArray.add(arr2.get(j));
                j++;
            }
        }

        //These 2 loops will Run when any 1 Array from both of them will completely get Traversed.
        while (i < arr1.size()) { //Loop for remaining Elements in Array-1.
            finalArray.add(arr1.get(i));
            i++;
        }

        while (j < arr2.size()) { //Loop for remaining Elements in Array-2.
            finalArray.add(arr2.get(j));
            j++;
        }

        //Step-4: Create Merged Balanced BST from Sorted ArrayList.
        return createBST(finalArray, 0, finalArray.size() - 1);
    }

    public static void main(String[] args) {
//        int[] values = {8, 5, 3, 1, 4, 6, 10, 11, 14};
//        int[] array = {3, 5, 6, 8, 10, 11, 12};
        /*Node root = null;

        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        inorder(root);
        System.out.println();*/

        /*if (search(root, 11)) {
            System.out.print("Key Found");
        } else {
            System.out.print("Key NOT Found");
        }*/

        /*root = delete(root, 5);

        inorder(root);*/
//        printInRange(root, 5, 12);
//        printRoot2Leaf(root, new ArrayList<>());

        /*if (isValidBST(root, null, null)) {
            System.out.println("BST is Valid.");
        } else {
            System.out.println("BST is NOT a valid.");
        }*/
        /*
        Given BST:
                   8
                  / \
                 6   10
                /     \
               5       11
              /         \
             3           12
         */
        /*Node root = new Node(8);
        root.left = new Node(6);
        root.left.left = new Node(5);
        root.left.left.left = new Node(3);

        root.right = new Node(10);
        root.right.right = new Node(11);
        root.right.right.right = new Node(12);*/

        /*
        Expected BST:
                    8
                  /   \
                 5     11
                / \   /  \
               3   6 10  12
         */

//        root = createMirrorBST(root);
//        preorder(createMirrorBST(root));
        /*Node root = createBST(array, 0, array.length - 1);
        preorder(createBST(array, 0, array.length - 1));*/
//        root = balanced(root);
//        preorder(balancedBST(root));
        /*
         Given Tree:
                    50
                  /    \
                30      60
               /  \    /  \
              5   20  45   70
                          /  \
                         65   80
         */
        /*Node root = new Node(50);
        root.left = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(20);

        root.right = new Node(60);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);*/
        /*
         Expected BST: Size = 5
                    60
                   /   \
                 45     70
                       /  \
                      65   80
         */

//        largestBST(root);
//        System.out.println("Largest BST Size: " + maxBST);
        /*
         Given BST-1:
                   2
                 /   \
                1     4
         */
        Node bst1 = new Node(2);
        bst1.left = new Node(1);
        bst1.right = new Node(4);

        /*
         Given BST-2:
                    9
                  /   \
                 3     12
         */
        Node bst2 = new Node(9);
        bst2.left = new Node(3);
        bst2.right = new Node(12);

        /*
         Merged BST:
                    3
                  /   \
                 1     9
                  \   /  \
                   2 4   12
         */
//        Node root = mergeBST(bst1, bst2);
        preorder(mergeBST(bst1, bst2));
    }
}
