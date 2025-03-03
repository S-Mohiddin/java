import java.util.*;

public class binning {
    public static void main(String[] args) {
        double[] data = {5, 10, 11, 13, 15, 35, 50, 55, 72, 92, 204, 215};
        int numBins = 3;

        // Find min, max, and bin width
        double min = Arrays.stream(data).min().getAsDouble();
        double max = Arrays.stream(data).max().getAsDouble();
        double binWidth = (max - min) / numBins;

        // Initialize bins
        List<List<Double>> bins = new ArrayList<>(numBins);
        for (int i = 0; i < numBins; i++) bins.add(new ArrayList<>());

        // Assign data points to bins
        for (double value : data) {
            int binIndex = Math.min((int) ((value - min) / binWidth), numBins - 1);
            bins.get(binIndex).add(value);
        }

        // Output the bins
        bins.forEach(bin -> System.out.println(bin));
    }
}
