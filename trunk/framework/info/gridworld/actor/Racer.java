package info.gridworld.actor;

import java.awt.Color;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Racer extends Bug{
	
	private boolean hasLost, boost;
	private int boostsLeft, boostSteps;
	
	public Racer(int direction, Color c){
		hasLost = false;
		boost = false;
		boostsLeft = 3;
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
    	int direction = super.getDirection();
    	Location current = super.getLocation();
    	Location next1 = current.getAdjacentLocation(direction);
    	Location next2 = next1.getAdjacentLocation(direction);
    	
        if (boost){
        	if (super.getGrid().isValid(next1) == false){
        		return false;
        	}
        	else if (super.getGrid().isValid(next2) == false){
        		return false;
        	}
        	else if (super.getGrid().get(next1) == null && super.getGrid().get(next2) == null)
        		return true;
        	else if (super.getGrid().get(next1) instanceof Racer){
        		Racer racer2 = (Racer) super.getGrid().get(next1);
        		racer2.setLost(true);
        		return false;
        	}
        	else if (super.getGrid().get(next2) instanceof Racer && super.getGrid().get(next1) == null){
        		Racer racer2 = (Racer) super.getGrid().get(next2);
        		racer2.setLost(true);
        		return false;
        	}
        	else
        		return false;
        }
        else{
        	if (super.getGrid().isValid(next1) == false){
        		return false;
        	}
        	else if (super.getGrid().get(next1) instanceof Racer){
        		Racer racer2 = (Racer) super.getGrid().get(next1);
        		racer2.setLost(true);
        		return false;
        	}
        	else{
        		return super.canMove();
        	}
        }
    }

    public void act()
    {
    	if (boost){
    		if (canMove()){
    			move();
    			if (canMove())
    				move();
    			else{
    				hasLost = true;
    				return;
    			}
    		}
    		else{
    			hasLost = true;
    			return;
    		}
    		boostSteps += 2;
    		if (boostSteps >= 10){
    			boost = false;
    			boostSteps = 0;
    		}
    	}
    	else{
    		if (canMove())
    			move();
    		else
    			hasLost = true;
    	}
    }
    
    public boolean hasLost(){
    	return hasLost;
    }
    
    public void boost(){
    	if (boostsLeft == 0)
    		return;
    	boost = true;
    	boostsLeft--;
    	boostSteps = 0;
    }
    
    public int getBoostsLeft(){
    	return boostsLeft;
    }
    
    public boolean isBoosting(){
    	return boost;
    }
}
