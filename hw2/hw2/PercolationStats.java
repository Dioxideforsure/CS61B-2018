package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private final double[] results;
    private final int T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        this.T = T;
        results = new double[T];
        for (int i = 0; i < T; i++){
            Percolation pT = pf.make(N);
            while (!pT.percolates()){
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                while (pT.isOpen(x, y)){
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                }
                pT.open(x, y);
            }
            results[i] = (double) pT.numberOfOpenSites() / (N * N);
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(results);
    }  // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(results);
    } // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }// low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }// high endpoint of 95% confidence interval


}



