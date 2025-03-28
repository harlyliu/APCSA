import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class CoyoteRunner
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(10,10);
        ActorWorld world = new ActorWorld(mygrid);
        world.add(new Location(2, 2), new Coyote());
        world.add(new Location(7, 7), new Coyote());
        world.add(new Location(4, 5), new Stone());
        world.add(new Location(8, 3), new Stone());
        world.show();
    }
}
