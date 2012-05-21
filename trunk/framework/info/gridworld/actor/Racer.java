package info.gridworld.actor;

import java.awt.Color;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Racer extends Bug{
	private boolean hasLost, boost;
	private int boostcount, boostlimit;
	
	public Racer(int direction, Color c){
		hasLost = false;
		boost = false;
		boostcount = 0;
		boostlimit = 0;
		super.setDirection(direction);
		super.setColor(c);
	}
	
	public void setLost(boolean b){
		hasLost = b;
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
    	if (canMove()){
            move();
            if (boost && boostcount<10){
            	if (canMove()){
                    move();
            	 }
                else{
                	hasLost = true;
                }
            	boostcount++;
            }
            if (boostcount == 10){
            	boost = false;
            	boostcount = 0;
            }
        }
        else{
        	hasLost = true;
        }
    }
    public boolean hasLost(){
    	return hasLost;
    }
    public void Boost(boolean boost){
    	this.boost = boost;
    	if (boost = true){
    		boostlimit++;
    	}
    	if (boostlimit >2){
    		boost = false;
    	}
    }
    public void resetBoostLimit(){
    	boostlimit = 0;
    }
}
