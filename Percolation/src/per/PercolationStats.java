package per;

import java.util.Random;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
    {
        if(N<=0||T<=0)
            throw new java.lang.IllegalArgumentException();
        int sumOfFractions=0;
        int[] fractions = new int[T];
        
        for(int i=0;i<T;i++)
        {
            Percolation p = new Percolation(N);
            int count = 0;
            while(!p.percolates())
            {
                Random r = new Random();
                int k = r.nextInt(N) + 1;
                int j = r.nextInt(N) + 1;
                p.open(k, j);
                count++;
            }
            fractions[i]= count/N;
            sumOfFractions = sumOfFractions + count/N;
        }
        this.mean = sumOfFractions /T;
        double variance=0;
        for(int i= 0;i<T;i++)
        {
            variance = variance + (this.mean - fractions[i])*(this.mean - fractions[i]);
        }
        variance = variance /T-1;
        this.stddev = Math.sqrt(variance);
        confidenceLo = this.mean -(1.96*this.stddev/Math.sqrt(T));
        confidenceHi = this.mean +(1.96*this.stddev/Math.sqrt(T));
    }
    public double mean()                      // sample mean of percolation threshold
    {
        return this.mean;
    }
    public double stddev()                    // sample standard deviation of percolation threshold
    {
     return this.stddev;   
    }
    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
        return this.confidenceLo;
    }
    public double confidenceHi()              // high endpoint of 95% confidence interval
    {
        return this.confidenceHi;
    }
    public static void main(String[] args)
    {
        PercolationStats ps  = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println("mean="+ps.mean());
        System.out.println("stddev="+ps.stddev());
        System.out.println("95% confidence interval="+ps.confidenceLo()+","+ps.confidenceHi());
    }
   
 }
