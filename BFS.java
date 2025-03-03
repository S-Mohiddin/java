import java.util.*;

public class BFS {

    static class State {
        int jug1, jug2;

        State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of jug 1: ");
        int capacity1 = scanner.nextInt();

        System.out.print("Enter the capacity of jug 2: ");
        int capacity2 = scanner.nextInt();

        System.out.print("Enter the target amount of water: ");
        int target = scanner.nextInt();

        solveWaterJugProblem(capacity1, capacity2, target);
    }

    private static void solveWaterJugProblem(int cap1, int cap2, int target) {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new State(0, 0)); // Start with both jugs empty

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int jug1 = current.jug1;
            int jug2 = current.jug2;

            // Print the current state
            System.out.println("Current state: (" + jug1 + ", " + jug2 + ")");

            // Check if we've reached the target
            if (jug1 == target || jug2 == target) {
                System.out.println("Reached target: (" + jug1 + ", " + jug2 + ")");
                return;
            }

            // Mark the state as visited
            String stateKey = jug1 + "-" + jug2;
            if (visited.contains(stateKey)) continue;
            visited.add(stateKey);

            // Possible next states
            queue.offer(new State(cap1, jug2)); // Fill jug 1
            queue.offer(new State(jug1, cap2)); // Fill jug 2
            queue.offer(new State(0, jug2));    // Empty jug 1
            queue.offer(new State(jug1, 0));    // Empty jug 2
            queue.offer(new State(Math.max(jug1 - (cap2 - jug2), 0), Math.min(cap2, jug2 + jug1))); // Pour jug 1 into jug 2
            queue.offer(new State(Math.min(cap1, jug1 + jug2), Math.max(jug2 - (cap1 - jug1), 0))); // Pour jug 2 into jug 1
        }

        System.out.println("No solution found.");
    }
}