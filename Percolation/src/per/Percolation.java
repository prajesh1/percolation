package per;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	 private WeightedQuickUnionUF topqf;
	 private WeightedQuickUnionUF originalqf;
	 private boolean[][] isOpen;
	 private int N;
	 public Percolation(int N)               // create N-by-N grid, with all sites blocked
	  {	
	     if(N<1)
	         throw new java.lang.IllegalArgumentException();
		 this.N = N;
		  topqf = new WeightedQuickUnionUF(N*N+2);
		  originalqf = new WeightedQuickUnionUF(N*N+2);
		 
		 
		 isOpen = new boolean[N][N];
		 
	  }
	   public void open(int i, int j)          // open site (row i, column j) if it is not open already
	   {
	       if(i<1||j<1||i>N||j>N)
               throw new java.lang.IndexOutOfBoundsException(); 
	       if(i==1)// topmost row
	       {
	           topqf.union(0, j);
	           originalqf.union(N*N+1, (i-1)*N+j);
	       }
	       if(i==N)//bottom most row
	       {
	           originalqf.union(N*N+1, (i-1)*N+j); //topqf not connected to avoid backlash
	       }
	       this.isOpen[i-1][j-1]=true;
		   if(i>1&&this.isOpen[i-2][j-1]) //top
			   unionBoth((i-1)*N+j,(i-2)*N + j);
		   if(j>1&&this.isOpen[i-1][j-2])//left
			   unionBoth((i-1)*N+j,(i-1)*N + j -1);
		   if(i<N&&this.isOpen[i][j-1]) //bottom
			   unionBoth((i-1)*N+j,(i)*N + j);
		   if(j<N&&this.isOpen[i-1][j])//right
			   unionBoth((i-1)*N+j,(i-1)*N + j+1);
	   }
	   
	   public void unionBoth(int i,int j)
	   {
		   topqf.union(i, j);
		   originalqf.union(i, j);
	   }
	   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	   {
	       if(i<1||j<1||i>N||j>N)
	           throw new java.lang.IndexOutOfBoundsException();
		   return this.isOpen[i-1][j-1];
	   }
	   public boolean isFull(int i, int j)     // is site (row i, column j) full?
	   {
	       if(i<1||j<1||i>N||j>N)
               throw new java.lang.IndexOutOfBoundsException();
	       return topqf.connected(0, (i-1)*N+j); //if it is connected to top
	   }
	   public boolean percolates()             // does the system percolate?
	   {
	       return originalqf.connected(0, N*N+1);
	   }

	   //public static void main(String[] args)  // test client (optional)*/

}
