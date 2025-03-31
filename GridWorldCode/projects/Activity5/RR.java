import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;


public class RR extends Critter {
    public RR() {
        setColor(null);
        setDirection(Location.NORTH);
    }

    public void makeMove(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) return;

        ArrayList<Location> spots = new ArrayList<>();
        Location currentLoc = getLocation();

        for (int dir = 0; dir < 360; dir += 45) {
            Location n1 = currentLoc.getAdjacentLocation(dir);
            if (!gr.isValid(n1) || (gr.get(n1) != null && !(gr.get(n1) instanceof Boulder) && !(gr.get(n1) instanceof Coyote)))
                continue;

            spots.add(n1);
            Location n2 = n1.getAdjacentLocation(dir);
            if (!gr.isValid(n2) || (gr.get(n2) != null && !(gr.get(n2) instanceof Boulder) && !(gr.get(n2) instanceof Coyote)))
                continue;

            spots.add(n2);
            Location n3 = n2.getAdjacentLocation(dir);
            if (!gr.isValid(n3) || (gr.get(n3) != null && !(gr.get(n3) instanceof Boulder) && !(gr.get(n3) instanceof Coyote)))
                continue;

            spots.add(n3);
        }

        if (spots.isEmpty()) return;

        Location next = spots.get((int) (Math.random() * spots.size()));

        if (gr.get(next) instanceof Boulder) {
            gr.get(next).removeSelfFromGrid();
            new Kaboom().putSelfInGrid(gr, next);
            removeSelfFromGrid();
        } 
        else if (gr.get(next) instanceof Coyote) {
            gr.get(next).removeSelfFromGrid();
            moveTo(next);

            ArrayList<Location> validLocations = gr.getEmptyAdjacentLocations(getLocation());
            if (!validLocations.isEmpty()) {
                new SickCoyote().putSelfInGrid(gr, validLocations.get((int) (Math.random() * validLocations.size())));
            }
        } 
        else {
            moveTo(next);
        }
    }
}
