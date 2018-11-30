import java.util.Arrays;

public class PercolationUF implements IPercolate {
	protected boolean[][] myGrid;
	protected int myCount;
	protected IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	private final int sizee;

	PercolationUF(int size, IUnionFind finder) {
		myGrid=new boolean [size][size];
		finder.initialize(size*size+2);
		myFinder=finder;
		VTOP=size*size;
		VBOTTOM=size*size+1;
		sizee=size;
		
		
	}
	
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		if (myGrid[row][col]==true) {
			return;
		}
		myCount+=1;
		if(row==0) {
			myFinder.union((row)*sizee+col, VTOP);
			
		}
		if(row==myGrid.length-1) {
			myFinder.union((row)*sizee+col, VBOTTOM);
		}
		
		
			myGrid[row][col]= true;
			if (inBounds(row+1,col) && isOpen(row+1,col)) {
				myFinder.union((row+1)*sizee+col, row*sizee+col);
			}
			if (inBounds(row,col+1) && isOpen(row,col+1)) {
				myFinder.union((row)*sizee+col+1, row*sizee+col);
			}
			if (inBounds(row-1,col) && isOpen(row-1,col)) {
				myFinder.union((row-1)*sizee+col, row*sizee+col);
			}
			if (inBounds(row,col-1) && isOpen(row,col-1)) {
				myFinder.union((row)*sizee+col-1, row*sizee+col);
			}
		
		
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(row*sizee+col, VTOP);
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myCount;
	}
	
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

	
}
