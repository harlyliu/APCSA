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

//package info.gridworld.actor;

import java.awt.Color;

/**
 * A <code>Flower</code> is an actor that darkens over time. Some actors drop
 * flowers as they move. <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */

public class Blossom extends Flower
{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static final double DARKENING_FACTOR = 0.05;
    private int lifeLeft;

    // lose 5% of color value in each step

    /**
     * Constructs a pink flower.
     */
    public Blossom()
    {
        setColor(DEFAULT_COLOR);
        lifeLeft = 10;
        //lifeLeft = (int)(Math.random()*100);
    }
    
    public Blossom(int life)
    {
        setColor(DEFAULT_COLOR);
        lifeLeft = life;
    }
    
    
	
    /**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
		if (lifeLeft == 0){
			removeSelfFromGrid();
			return;
		}
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        lifeLeft--;
        setColor(new Color(red, green, blue));
    }
}

