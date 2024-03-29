public class BacktrackingArray {
    public static void changeArr(int arr[], int i, int val) {
        //base case
        if (i == arr.length) {
            printArr(arr);
            return;
        }

        //Recursive
        arr[i] = val;
        changeArr(arr, i + 1, val + 1); //function call
        arr[i] = arr[i] - 2; //backtracking step
    }

    public static void findSubset(String str, String ans, int i) {
        //base case
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("null");
            } else {
                System.out.println(ans);
            }
            return;
        }

        //recursion
        findSubset(str, ans + str.charAt(i), i + 1); //Choice is YES
        findSubset(str, ans, i + 1); // Choice is NO
    }

    public static void findPermutation(String str, String ans) {
        //base case
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        //recursion
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            //Remove 1 char from str: "abcde" => "ab" + "de" = "abde"
            String NewStr = str.substring(0, i) + str.substring(i + 1);
            findPermutation(NewStr, ans + curr);
        }
    }

    public static boolean nQueens(char[][] board, int row) {
        //base case
        if (row == board.length) {
//            printBoard(board);
//            count++;
            return true;
        }

        //recursion for col
        for (int j = 0; j < board.length; j++) {
            if (isSafe(board, row, j)) {
                board[row][j] = 'Q';
                if (nQueens(board, row + 1)) {
                    return true;
                }
//                board[row][j] = 'x'; //backtracking step
            }
        }
        return false;
    }

//    static int count = 0;

    public static boolean isSafe(char[][] board, int row, int col) {
        //up side
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        //up left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //up right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        System.out.println("--------Safe Chess Board--------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean ratMaze(int[][] maze, int[][] solution) {
        if (!solveMazeUtil(maze, 0, 0, solution)) {
            System.out.print("Solution doesn't exist");
            return false;
        }
        return true;
    }

    public static boolean solveMazeUtil(int[][] maze, int x, int y, int[][] solution) {
        //base case
        if (x == maze.length - 1 && y == maze.length - 1 && maze[x][y] == 1) {
            solution[x][y] = 1;
            return true;
        }
        //Recursion: Check if maze[x][y] is valid
        if (isSafe(maze, x, y)) {
            if (solution[x][y] == 1) {
                return false;
            }
            solution[x][y] = 1;
            if (solveMazeUtil(maze, x + 1, y, solution)) {
                return true;
            }
            if (solveMazeUtil(maze, x, y + 1, solution)) {
                return true;
            }
            solution[x][y] = 0;
            return false;
        }
        return false;
    }

    public static boolean isSafe(int[][] maze, int x, int y) {
        // if (x, y outside maze) return false
        return (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] == 1);
    }

    final static char[][] L = {
            {}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    public static void letterCombinations(String D) {
        int len = D.length();
        if (len == 0) {
            System.out.println("");
            return;
        }
        bfs(0, len, new StringBuilder(), D);
    }

    public static void bfs(int pos, int len, StringBuilder sb, String D) {
        if (pos == len) {
            System.out.println(sb.toString());
        } else {
            char[] letters = L[Character.getNumericValue(D.charAt(pos))];
            for (int i = 0; i < letters.length; i++)
                bfs(pos + 1, len, new StringBuilder(sb).append(letters[i]), D);
        }
    }

    static int N = 8;

    public static boolean isSafe(int x, int y, int[][] sol) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    public static boolean solveKnightTour() {
        int[][] solution = new int[8][8];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                solution[x][y] = -1;
            }
        }

        int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

        //As the Knight starts from cell(0,0)
        solution[0][0] = 0;

        if (!solveKTUtil(0, 0, 1, solution, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } else {
            printSolution(solution);
        }
        return true;
    }

    public static boolean solveKTUtil(int x, int y, int movei, int[][] solution, int[] xMove, int[] yMove) {
        int k, next_x, next_y;

        if (movei == N * N) return true;

        for (k = 0; k < 8; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(next_x, next_y, solution)) {
                solution[next_x][next_y] = movei;
                if (solveKTUtil(next_x, next_y, movei + 1, solution, xMove, yMove)) {
                    return true;
                } else {
                    solution[next_x][next_y] = -1; // backtracking
                }
            }
        }
        return false;
    }

    public static void printSolution(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] para_coder) {
//        int arr[] = new int[5];
//        changeArr(arr, 0, 1);
//        printArr(arr);
//        String str = "abc";
//        findSubset(str, "", 0);
//        findPermutation(str, "");
        /*int n = 5;
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 'x';
            }
        }
        //Print 1 solution.
        if (nQueens(board, 0)) {
            System.out.println("Solution is possible.");
            printBoard(board);
        } else {
            System.out.println("Solution is NOT possible.");
        }*/
//        System.out.println("Total ways to solve N-Queen problem is " + count); //count ways

        /*Question 1: Rat in maze, N*N maze, where the cells with value 0 represent the maze’s blocked locations while value 1 is the open/available path that the rat can take to reach its destination. Destination is (N-1, N-1). Your task is to find all the possible paths that the rat can take to reach from source to destination in the maze. Possible directions are Up, Down, Left, Right.*/
        /*int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        int N = maze.length;
        int[][] solution = new int[N][N];

        if (ratMaze(maze, solution)) {
            System.out.println("Solution exists.");
            printSolution(maze);
        }*/

        /*Question 2: Keypad Combination, Given a string containing digits from 2-9 inclusive, print all possible letter combinations that the number could represent. You can print the answer in any order. A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.*/
//        letterCombinations("23");

        /*Question 3: Knight’s Tour, Given a N*N board with the Knight placed on the first block of an empty board. Moving according to the rules of chess, knights must visit each square exactly once. Print the order of each cell in which they are visited.*/
        solveKnightTour();
    }
}
