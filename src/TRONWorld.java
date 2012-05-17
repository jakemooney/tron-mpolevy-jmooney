import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Racer;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.gui.GUIController;
import info.gridworld.gui.WorldFrame;

public class TRONWorld extends ActorWorld{

	private int score1, score2;
	private Racer racer1, racer2;

	public TRONWorld(){
		super();
		super.setGrid(new BoundedGrid(101, 150)); //the first dimension should be odd, the second should be even
		racer1 = new Racer();
		racer1.setColor(Color.green);
		racer2 = new Racer();
		racer2.setColor(Color.red);
		placeRacers();
		score1 = 0;
		score2 = 0;
	}
	
	//adds racer1 to the middle-left, racer2 to the middle-right
	private void placeRacers(){
		int cols = super.getGrid().getNumCols();
		int rows = super.getGrid().getNumRows();
		Location racer1Location = new Location(rows / 2 - 1, cols / 4);
		super.add(racer1Location, racer1);
		Location racer2Location = new Location(rows / 2 - 1, cols * 3 / 4);
		super.add(racer2Location, racer2);
	}

	public boolean keyPressed(String description, Location loc){
		if (description.equals("W") && racer1.getDirection()!= Location.SOUTH){
			racer1.setDirection(Location.NORTH);
		}
		if (description.equals("D")&& racer1.getDirection()!= Location.WEST){
			racer1.setDirection(Location.EAST);
		}
		if (description.equals("S")&& racer1.getDirection()!= Location.NORTH){
			racer1.setDirection(Location.SOUTH);
		}
		if (description.equals("A")&& racer1.getDirection()!= Location.EAST){
			racer1.setDirection(Location.WEST);
		}
		if (description.equals("UP")&& racer2.getDirection()!= Location.SOUTH){
			racer2.setDirection(Location.NORTH);
		}
		if (description.equals("RIGHT")&& racer2.getDirection()!= Location.WEST){
			racer2.setDirection(Location.EAST);
		}
		if (description.equals("DOWN")&& racer2.getDirection()!= Location.NORTH){
			racer2.setDirection(Location.SOUTH);
		}
		if (description.equals("LEFT")&& racer2.getDirection()!= Location.EAST){
			racer2.setDirection(Location.WEST);
		}
		if (description.equals("SPACE")){
			placeRacers();
			((WorldFrame) super.getFrame()).run();
		}
		return true;
	}
	
	public void show(){
		super.show();
		super.getFrame().setSize(975, 760);
	}
	
	public Racer getRacer1(){
		return racer1;
	}
	
	public Racer getRacer2(){
		return racer2;
	}
	
	public void step(){
		super.step();
		if (racer1.hasLost()){
			score2++;
			((WorldFrame) super.getFrame()).stop();
        	ImageIcon z = new ImageIcon(super.getFrame().getClass().getResource("GridWorld.png"));
    		JOptionPane.showMessageDialog(getFrame(), "Player 2 wins. Press spacebar to continue.", "Loser!", 2, z);
    		return;
		}
		if (racer2.hasLost()){
			score1++;
			((WorldFrame) super.getFrame()).stop();
        	ImageIcon z = new ImageIcon(super.getFrame().getClass().getResource("GridWorld.png"));
    		JOptionPane.showMessageDialog(getFrame(), "Player 1 wins. Press spacebar to continue.", "Loser!", 2, z);
			return;
		}
	}
}