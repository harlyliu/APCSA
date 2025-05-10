/**
 *	A SinglyLinkedList of Coordinate objects representing
 *	a snake on a two-dimensional grid.
 *
 *	@author	
 *	@since	
 */
public class Snake extends SinglyLinkedList<Coordinate> {
	
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(Coordinate location) {
		super();
		int r = location.getRow();
		int c = location.getCol();
		add(location);
		add(new Coordinate(r, c+1));
		add(new Coordinate(r, c+2));
		add(new Coordinate(r, c+3));
		add(new Coordinate(r, c+4));
	}
	
	
}
