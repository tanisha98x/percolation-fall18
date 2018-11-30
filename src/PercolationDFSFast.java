
public class PercolationDFSFast extends PercolationDFS {
	/**
	 * Constructor with an int/size parameter that calls super to initialize the state in the parent class
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	 /**
     * Determine if the newly open cell (parameters row and col of updateOnOpen) should be marked as full, if the cell should be marked as full, call dfs(row,col)
     * @param  int row and int col
     */
	@Override
	protected void updateOnOpen(int row, int col) {
		int check=0;
		if( inBounds(row, col) == false) {
			return;
			}
		if(row==0) {
			check=1;
		}
		if (inBounds(row+1,col) && isFull(row+1,col)) {
			check=1;
			
			}
		else if (inBounds(row,col+1) && isFull(row,col+1)) {
			check=1;
			
			}
		else if (inBounds(row-1,col) && isFull(row-1,col)) {
			check=1;
			
			}
		else if (inBounds(row,col-1) && isFull(row,col-1)) {
			check=1;
			
			}	
		if(check==1) {
			dfs(row, col);
		}
	}

}
