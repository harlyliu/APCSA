import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Coyote extends Critter {
    private int steps;
    private int sleepTime;
    private int direction;

    public Coyote() {
        setColor(null);
        direction = (int) (Math.random() * 8) * 45;
        steps = 0;
        sleepTime = 0;
    }

    public void act() {
        if (sleepTime > 0) {
            sleepTime--;
            return;
        }
        if (steps >= 5 || !canMove()) {
            steps = 0;
			Grid<Actor> grid = getGrid();
			if (grid == null) return;
			List<Location> validLocations = grid.getEmptyAdjacentLocations(getLocation());
			if (!validLocations.isEmpty()) {
				new Stone().putSelfInGrid(grid, validLocations.get(0));
			}
			direction = (int) (Math.random() * 8) * 45;
            sleepTime = 5;
            return;
        }
        move();
        steps++;
    }

    public boolean canMove() {
        Grid<Actor> grid = getGrid();
        if (grid == null) return false;
        Location nextLoc = getLocation().getAdjacentLocation(direction);
        if (!grid.isValid(nextLoc)) return false;
        Actor neighbor = grid.get(nextLoc);
        if (neighbor instanceof Boulder) {
            neighbor.removeSelfFromGrid();
            new Kaboom().putSelfInGrid(grid, nextLoc);
            removeSelfFromGrid();
            return false;
        }
        return (neighbor == null);
    }

    public void move() {
        Grid<Actor> grid = getGrid();
        if (grid == null) return;
        Location nextLoc = getLocation().getAdjacentLocation(direction);
        if (grid.isValid(nextLoc) && grid.get(nextLoc) == null) {
            moveTo(nextLoc);
        }
    }

}
