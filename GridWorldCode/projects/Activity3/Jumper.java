/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */

import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
//package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Bug</code> is an actor that can move and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug
{
	private int dist = -1;
	private int jumped;
    /**
     * Constructs a blue bug.
     */
    public Jumper()
    {
        setColor(Color.BLUE);
    }
    
    public Jumper(int distIn){
		setColor(Color.BLUE);
		dist = distIn;
	}

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
		Grid<Actor> gr = getGrid();
		if (isSurrounded()){
			if (gr.isValid(getLocation().getAdjacentLocation(getDirection()))){
				move();
			}
			else turn();
		}
		else if (dist != -1 && canJump() && jumped < dist){
			jump();
			jumped+= 2;
		}
        else if (dist == -1 && canJump()){
            jump();
            jumped+=2;
		}
        else{
			jumped = 0;
            turn();
		}
    }
	
	public boolean isSurrounded(){
		Grid<Actor> gr = getGrid();
		boolean ans = true;
		for (int i = 0; i < 8; i++){
			setDirection(getDirection() + Location.HALF_RIGHT);
			Location loc = getLocation();
			Location fakeNext = loc.getAdjacentLocation(getDirection());
			Location next = fakeNext.getAdjacentLocation(getDirection());
			if (gr.isValid(next) && canJump()){
				ans = false;
			}
		}
		return ans;
	}
    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location fakeNext = loc.getAdjacentLocation(getDirection());
        Location next = fakeNext.getAdjacentLocation(getDirection());
        if (gr.isValid(next) && canJump())
            moveTo(next);
        else
            removeSelfFromGrid();
        Blossom blossom = new Blossom();
        blossom.putSelfInGrid(gr, loc);
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a blossom.
     * @return true if this bug can move.
     */
    public boolean canJump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location fakeNext = loc.getAdjacentLocation(getDirection());
        Location next = fakeNext.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) && !(neighbor instanceof Blossom);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
