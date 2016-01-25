package per;

public class Percolation {
	 private WeightedQuickUnionUF qf;
	 private int N;
	 public Percolation(int N)               // create N-by-N grid, with all sites blocked
	  {	
		 this.N = N;
		  qf = new WeightedQuickUnionUF(N*N+2);
		 for(int i=1;i<=N;i++)
		 {
			 qf.union(0,i);
			 qf.union(N*N+1,i);
		 }
		 
	  }
	   public void open(int i, int j)          // open site (row i, column j) if it is not open already
	   {
		   if(i>1)
			   qf.union((i-1)*N+j,(i-2)*N + j);
		   if(j>1)
			   qf.union((i-1)*N+j,(i-1)*N + j -1);
		   if(i<N)
			   qf.union((i-1)*N+j,(i)*N + j);
		   if(j<N)
			   qf.union((i-1)*N+j,(i-1)*N + j+1);
	   }
	   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	   {
		   
	   }
	   /*public boolean isFull(int i, int j)     // is site (row i, column j) full?
	   public boolean percolates()             // does the system percolate?

	   public static void main(String[] args)  // test client (optional)*/

}
