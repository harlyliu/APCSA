import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>Coyote</code> extends Critter.
 * The Coyote drops Stones as he wanders around the grid.
 * He moves in straight lines, but stops every couple steps to drop a stone and change direction.
 */
public class RR extends Critter {
    private int[] directions = {0, 45, 90, 135, 180, 225, 270, 315};
    private int steps;
    private int rest;
    private boolean resting;

    public RR() {
        setColor(null);
        setDirection(Location.NORTH);
        steps = 0;
        rest = 0;
        resting = false;
    }

    public void makeMove(Location loc) {
		ArrayList<Location> spots = new List<Location>();
		Grid<Actor> gr = getGrid();
		Location loc = getLocation();
		for (int dir = 0; dir < 360; dir + 45){
			Location n1 = loc.getAdjacentLocation(dir);
			if (!gr.isValid(n1) || (gr.isValid(n1) && gr.get(n1) != null)){
				continue;
			}
			Location n2 = n1.getAdjacentLocation(dir);
			if (!gr.isValid(n2) || (gr.isValid(n2) && gr.get(n2) != null)){
				continue;
			}
			Location n3 = n2.getAdjacentLocation(dir);
			if (!gr.isValid()){
		}
    }

}


