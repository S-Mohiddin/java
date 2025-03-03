import java.util.*;

public class DFS {

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
        Stack<State> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(new State(0, 0)); // Start with both jugs empty

        while (!stack.isEmpty()) {
            State current = stack.pop();
            int jug1 = current.jug1;
            int jug2 = current.jug2;

        
            System.out.println("Current state: (" + jug1 + ", " + jug2 + ")");

        
            if (jug1 == target || jug2 == target) {
                System.out.println("Reached target: (" + jug1 + ", " + jug2 + ")");
                return;
            }

            4
            String stateKey = jug1 + "-" + jug2;
            if (visited.contains(stateKey)) continue;
            visited.add(stateKey);

            
            stack.push(new State(cap1, jug2)); // Fill jug 1
            stack.push(new State(jug1, cap2)); // Fill jug 2
            stack.push(new State(0, jug2)); // Empty jug 1
            stack.push(new State(jug1, 0)); // Empty jug 2
            stack.push(new State(Math.max(jug1 - (cap2 - jug2), 0), Math.min(cap2, jug2 + jug1))); // Pour jug 1 into jug 2
            stack.push(new State(Math.min(cap1, jug1 + jug2), Math.max(jug2 - (cap1 - jug1), 0))); // Pour jug 2 into jug 1
        }

        System.out.println("No solution found.");
    }
}