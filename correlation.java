import java.util.Scanner;
import java.util.Arrays;

public class correlation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of data points: ");
        int n = scanner.nextInt();
        
        double[] x = new double[n], y = new double[n];

        System.out.println("Enter the data points for X:");
        for (int i = 0; i < n; i++) x[i] = scanner.nextDouble();

        System.out.println("Enter the data points for Y:");
        for (int i = 0; i < n; i++) y[i] = scanner.nextDouble();

        double meanX = mean(x), meanY = mean(y);
        double numerator = 0, denominatorX = 0, denominatorY = 0;

        for (int i = 0; i < n; i++) {
            double dx = x[i] - meanX, dy = y[i] - meanY;
            numerator += dx * dy;
            denominatorX += dx * dx;
            denominatorY += dy * dy;
        }

        double correlation = numerator / Math.sqrt(denominatorX * denominatorY);
        System.out.printf("Pearson Correlation Coefficient: %.2f\n", correlation);
    }

    private static double mean(double[] data) {
        return Arrays.stream(data).average().orElse(0);
    }
}

