import java.util.*;

public class Astar {

    // Directions for movement: Up, Down, Left, Right
    private static final int[] rowDirections = {-1, 1, 0, 0};
    private static final int[] colDirections = {0, 0, -1, 1};

    static class Node {
        int row, col, cost, heuristic;
        Node parent;

        Node(int row, int col, int cost, int heuristic, Node parent) {
            this.row = row;
            this.col = col;
            this.cost = cost; // g(n): Cost from start to current node
            this.heuristic = heuristic; // h(n): Estimated cost to goal
            this.parent = parent;
        }

        int getTotalCost() {
            return cost + heuristic; // f(n) = g(n) + h(n)
        }
    }

    // Manhattan distance heuristic
    public static int calculateHeuristic(int row, int col, int goalRow, int goalCol) {
        return Math.abs(row - goalRow) + Math.abs(col - goalCol);
    }

    // Check if a position is within the grid and not an obstacle
    public static boolean isValidMove(int row, int col, int rows, int cols, int[][] grid) {
        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] != 1;
    }

    // A* algorithm to find the shortest path
    public static void aStarSearch(int[][] grid, int startRow, int startCol, int goalRow, int goalCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(Node::getTotalCost));
        Set<String> visited = new HashSet<>();

        // Start node
        openList.add(new Node(startRow, startCol, 0, calculateHeuristic(startRow, startCol, goalRow, goalCol), null));
        visited.add(startRow + "," + startCol);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            int currentRow = currentNode.row;
            int currentCol = currentNode.col;

            // If we reach the goal, print the path
            if (currentRow == goalRow && currentCol == goalCol) {
                printPath(currentNode);
                return;
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + rowDirections[i];
                int newCol = currentCol + colDirections[i];

                if (isValidMove(newRow, newCol, rows, cols, grid) && !visited.contains(newRow + "," + newCol)) {
                    int newCost = currentNode.cost + 1;
                    int newHeuristic = calculateHeuristic(newRow, newCol, goalRow, goalCol);
                    openList.add(new Node(newRow, newCol, newCost, newHeuristic, currentNode));
                    visited.add(newRow + "," + newCol);
                }
            }
        }

        System.out.println("No path found.");
    }

    // Print the path from start to goal
    public static void printPath(Node node) {
        if (node == null) return;
        printPath(node.parent);
        System.out.println("Move to: (" + node.row + ", " + node.col + ")");
    }

    // Main method
    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0}
        };

        // Start and goal positions
        int startRow = 0, startCol = 0; // Starting point
        int goalRow = 4, goalCol = 4;   // Goal point

        aStarSearch(grid, startRow, startCol, goalRow, goalCol);
    }
}