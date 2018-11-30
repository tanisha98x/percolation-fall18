import java.util.*;
public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void dfs(int row, int col) {
		Queue<Integer> qp = new LinkedList<>();
		if (!inBounds(row, col)) {
			return;
		}
		myGrid[row][col]= FULL;
		int size=myGrid.length;
		qp.add(row*size+col);
		while(qp.size()!=0) {
		 Integer num=qp.remove();
		 int r= num/size;
		 int c= num%size;
		 if (inBounds(r+1,c) && isOpen(r+1,c) && !isFull(r+1,c)) {
			 myGrid[r+1][c]= FULL;
			 qp.add((r+1)*size+c);
		 }
		 if (inBounds(r-1,c) && isOpen(r-1,c) && !isFull(r-1,c)) {
			 myGrid[r-1][c]= FULL;
			 qp.add((r-1)*size+c);
		 }
		 if (inBounds(r,c+1) && isOpen(r,c+1) && !isFull(r,c+1)) {
			 myGrid[r][c+1]= FULL;
			 qp.add((r)*size+c+1);
		 }
		 if (inBounds(r,c-1) && isOpen(r,c-1) && !isFull(r,c-1)) {
			 myGrid[r][c-1]= FULL;
			 qp.add((r)*size+c-1);
		 }
		 
			
		}
	}

}
