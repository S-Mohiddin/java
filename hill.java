import java.util.Scanner;

public class hill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial x: ");
        int x = scanner.nextInt();
        System.out.print("Enter initial y: ");
        int y = scanner.nextInt();


        System.out.print("Enter goal x: ");
        int goalX = scanner.nextInt();
        System.out.print("Enter goal y: ");
        int goalY = scanner.nextInt();   

        while (true) {
            int cost = Math.abs(x - goalX) + Math.abs(y - goalY);
            System.out.println("Current state: (" + x + ", " + y + ") Cost: " + cost);

            if (x == goalX && y == goalY) {
                System.out.println("Goal state reached!");
                break;
            }

            if (x < goalX) x++;
            else if (x > goalX) x--;
            if (y < goalY) y++;
            else if (y > goalY) y--;
        }
    }
}