import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.*;

public class Coyote extends Critter {
    private int steps;
    private int sleepTime;
    private int direction;
    private static final int MAX_STEPS = 5;
    private static final int WAIT_TIME = 5;

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
        if (steps >= MAX_STEPS || !canMove()) {
            pickNewDirection();
            sleepTime = WAIT_TIME;
            return;
        }
        move();
        steps++;
    }

    private boolean canMove() {
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

    private void move() {
        Grid<Actor> grid = getGrid();
        if (grid == null) return;
        Location nextLoc = getLocation().getAdjacentLocation(direction);
        if (grid.isValid(nextLoc) && grid.get(nextLoc) == null) {
            moveTo(nextLoc);
        }
    }

    private void pickNewDirection() {
        steps = 0;
        Grid<Actor> grid = getGrid();
        if (grid == null) return;
        List<Location> validLocations = grid.getEmptyAdjacentLocations(getLocation());
        if (!validLocations.isEmpty()) {
            new Stone().putSelfInGrid(grid, validLocations.get(0));
        }
        direction = (int) (Math.random() * 8) * 45;
    }
}
