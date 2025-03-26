import info.gridworld.actor.Bug;
import info.gridworld. actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;

public class Kaboom extends Actor{
	private int lifetime;
	private final int THRESHOLD = 3;
	
	public Kaboom(){
		setColor(null);
		lifetime = THRESHOLD;
	}
	public void act(){
		lifetime--;
		if (lifetime == 0){
			removeSelfFromGrid();
		}
	}
}
