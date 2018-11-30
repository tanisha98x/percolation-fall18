import java.util.Arrays;

public class PercolationUF implements IPercolate {
	protected boolean[][] myGrid;
	protected int myCount;
	protected IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	private final int sizee;
	 /**
     *  Constructor that constructs and initializes the NxN grid stored in the instance
     *  variable myGrid (where N is given by the size parameter). 
     *  The constructor initializes the IUnionFind object of size NxN + 2 by calling finder.
     *  initializes appropriately and then stores this object in the appropriate instance variable.  
     */
	PercolationUF(int size, IUnionFind finder) {
		myGrid=new boolean [size][size];
		finder.initialize(size*size+2);
		myFinder=finder;
		VTOP=size*size;
		VBOTTOM=size*size+1;
		sizee=size;	
	}
	
	/**
	 * if top row connects to VTOP and if bottom row connects to VBOTTOM
	 * checks if neighbors are open and if they are, connects them to the parameter celll
     * @param  int row and int column of cell
     * @throws IndexOutOfBoundsException if the cell is out of bounds
     */
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
	
	
	/**
     * Checks if a cell is open
     * @param  int row and int column of cell
     * @throws IndexOutOfBoundsException if the cell is out of bounds
     * @return true if the cell is open
     */
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	
	 /**
     * Checks if a cell is full
     * @param  int row and int column of cell
     * @throws IndexOutOfBoundsException if the cell is out of bounds
     * @return checks whether the cell is connected to VTOP
     */
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(row*sizee+col, VTOP);
	}

	
	 /**
     * Checks if the system percolates
     * @return true if VTOP is connected to VBOTTOM and false if not
     */
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	
	 /**
     * @return instance variable myCount that stores the number of open sites
     */
	@Override
	public int numberOfOpenSites() {
		return myCount;
	}
	
	
	 /**
     * Checks whether in bounds
     * @param int rows and columns
     * @return true if in bounds and false if not
     */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

	
}
