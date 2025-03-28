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
public class Coyote extends Critter {
    private int[] directions = {0, 45, 90, 135, 180, 225, 270, 315};
    private int steps;
    private int rest;
    private boolean resting;

    public Coyote() {
        setColor(null);
        setDirection(directions[(int) (8 * Math.random())]);
        steps = 0;
        rest = 0;
        resting = false;
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc) {
		if (resting) {
            if (rest < 5) {
                rest++;
                return;
            } else {
                resting = false;
                rest = 0;
                setDirection(directions[(int) (8 * Math.random())]);
            }
        }
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location next = getLocation().getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            steps = 0;
            resting = true;
            rest = 0;
            return;
           }
        boolean hitboulder = false;
        for (Actor ac: getActors()){
			if (ac.getLocation().equals(next)) hitboulder = true;
		}
        if (gr.isValid(next) && hitboulder) {
            Kaboom kab = new Kaboom();
            kab.putSelfInGrid(gr, next);
            removeSelfFromGrid();
            return;
        } else if (gr.isValid(next) && gr.get(next) == null && steps < 5) {
            steps++;
            move();
        } else {
            steps = 0;
            resting = true;
            rest = 0;
            ArrayList<Location> spots = gr.getValidAdjacentLocations(getLocation());
            if (!spots.isEmpty()) {
                Stone st = new Stone();
                st.putSelfInGrid(gr, spots.get((int) (spots.size() * Math.random())));
            }
        }
    }

    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        }
    }
}

