import info.gridworld.actor.Bug;
import info.gridworld. actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;

public class Stone extends Rock{
	private int lifetime;
	private final int THRESHOLD = 3;
	
	public Stone(){
		setColor(null);
		lifetime = (int)(200*(Math.random()))+1;
	}
	
	public Stone(int lifeIn){
		setColor(null);
		lifetime = lifeIn;
		
	}
	public void act(){
		Grid<Actor> gr = getGrid();
		if (gr == null)
            return;
		Location loc = getLocation();
		if (lifetime <= THRESHOLD){
			setColor(Color.GREEN);
		}
		lifetime--;
		if (lifetime == 0){
			removeSelfFromGrid();
			Boulder bould = new Boulder();
			bould.putSelfInGrid(gr,loc);
		}
	}
}
