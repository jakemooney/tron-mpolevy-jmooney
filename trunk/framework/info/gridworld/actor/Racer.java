package info.gridworld.actor;

import java.awt.Color;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Racer extends Bug{
	private boolean hasLost;
	
	public Racer(){
		hasLost = false;
	}

	public void move(){
		  Grid<Actor> gr = getGrid();
	        if (gr == null)
	            return;
	        Location loc = getLocation();
	        Location next = loc.getAdjacentLocation(getDirection());
	        if (gr.isValid(next))
	            moveTo(next);
	        else{
	        	this.setColor(Color.white);
	        	hasLost = true;
	           //throw new IllegalArgumentException("Lost");
	        }
	        Block1 b = new Block1();
	        b.setColor(this.getColor());
	        b.putSelfInGrid(gr, loc);
	}
	
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null);
        // ok to move into empty location
        // not ok to move onto any other actor or wall
    }
    
    public void act()
    {
    	//this.setColor(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
    	if (canMove()){
            move();
        }
        else{
        	hasLost = true;
        }
    }
    public boolean hasLost(){
    	return hasLost;
    }
}
