/* *****************************************************************************
 *  Name:              Kaveh Hashemi
 *  Coursera User ID:  123456
 *  Last modified:     27/1/2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int t;
    private double conf95 = 1.96;
    private double[] percentages = new double[0];
    private double sampleMean;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation b = new Percolation(n);
            while (!b.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                b.open(row, col);
            }
            double percentage = ((double) b.numberOfOpenSites() / (double) (n * n));
            percentages = push(percentage, percentages);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        sampleMean = StdStats.mean(percentages);
        return sampleMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double standardDeviation = StdStats.stddev(percentages);
        return standardDeviation;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double CLO = sampleMean - (conf95 * stddev() / Math.sqrt(t));
        return CLO;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double CHI = sampleMean + (conf95 * stddev() / Math.sqrt(t));
        return CHI;
    }

    // test client (see below)
    public static void main(String[] args) {
        int nValue = Integer.parseInt(args[0]);
        int tValue = Integer.parseInt(args[1]);
        PercolationStats a = new PercolationStats(nValue, tValue);
        System.out.println("mean = " + a.mean());
        System.out.println("stddev = " + a.stddev());
        System.out.println(
                "95% confidence interval = [" + a.confidenceLo() + ", " + a.confidenceHi() + "]");
    }

    private double[] push(double percentage, double[] percentagesArray) {
        double[] temp = new double[percentagesArray.length + 1];
        if (percentagesArray.length == 0) {
            temp[0] = percentage;
        } else {
            for (int i = 0; i < percentagesArray.length; i++) {
                temp[i] = percentagesArray[i];
            }
            temp[percentagesArray.length] = percentage;
        }
        return temp;
    }
}
