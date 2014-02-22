public class PercolationStats {
    private Percolation grid;
    private int times;
    private double[] num;
    private int size;
    
    public PercolationStats(int N, int T) {
        // perform T independent computational experiments on an N-by-N grid
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        size = N;
        grid = new Percolation(size);
        times = T;
        num = new double[T];
        int a = 1;
        int b = 1;
        for (int i = 0; i < times; i++) {
            while (!grid.percolates()) {
                while (grid.isOpen(a, b)) {
                    a = StdRandom.uniform(1, this.size+1);
                    b = StdRandom.uniform(1, this.size+1);
                }
                grid.open(a, b);
                num[i]++;
            }
            num[i] /= (size * size);
            grid = new Percolation(size);
        }
    }
 
    public double mean() {
     // sample mean of percolation threshold
        return StdStats.mean(num);
    }
 
    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(num);
    }
    
    public double confidenceLo() {
        // returns lower bound of the 95% confidence interval
        return mean() - (1.96 * stddev() / Math.sqrt(times));
    }
 
    public double confidenceHi() {
        // returns upper bound of the 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(N, T);
        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = " + p.confidenceLo() + ", " + p.confidenceHi());
    }

}
