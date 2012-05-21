package info.gridworld.world;
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
	private boolean isPaused;
	private final Color color1 = new Color(51, 204, 153, 0);
	private final Color color2 = new Color(255, 102, 255, 0);

	public TRONWorld(){
		super();
		super.setGrid(new BoundedGrid(100, 150)); //both should be even
		racer1 = new Racer(Location.EAST, color1);
		racer2 = new Racer(Location.WEST, color2);
		placeRacers();
		score1 = 0;
		score2 = 0;
		isPaused = false;
	}
	
	//adds racer1 to the middle-left, racer2 to the middle-right
	private void placeRacers(){
		int cols = super.getGrid().getNumCols();
		int rows = super.getGrid().getNumRows();
		Location racer1Location = new Location(rows / 2 - 1, cols / 4);
		racer1.putSelfInGrid(super.getGrid(), racer1Location);
		Location racer2Location = new Location(rows / 2 - 1, cols * 3 / 4);
		racer2.putSelfInGrid(super.getGrid(), racer2Location);
	}
	
	public void reset(){
		racer1 = new Racer(Location.EAST, color1);
		racer2 = new Racer(Location.WEST, color2);
		removeAllActors();
		placeRacers();
	}

	private void removeAllActors() {
		Grid g = super.getGrid();
		ArrayList<Location> occ = g.getOccupiedLocations();
		for (Location l : occ){
			Actor a = (Actor) g.get(l);
			a.removeSelfFromGrid();
		}
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
			WorldFrame f = (WorldFrame) super.getFrame();
			if (isPaused)
				f.run();
			else
				f.stop();
		}
		if (description.equals("F")){
			racer1.Boost(true);
		}
		if (description.equals("L")){
			racer2.Boost(true);
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
		if (racer1.hasLost() && racer2.hasLost()){
			((WorldFrame) super.getFrame()).stop();
			isPaused = true;
        	ImageIcon z = new ImageIcon(super.getFrame().getClass().getResource("TRON.gif"));
    		JOptionPane.showMessageDialog(getFrame(), "Tie!", "Alert!", 2, z);
    		reset();
    		return;
		}
		if (racer1.hasLost()){
			score2++;
			((WorldFrame) super.getFrame()).stop();
			isPaused = true;
        	ImageIcon z = new ImageIcon(super.getFrame().getClass().getResource("TRON.gif"));
    		JOptionPane.showMessageDialog(getFrame(), "Player 2 wins!", "Alert!", 2, z);
    		reset();
    		return;
		}
		if (racer2.hasLost()){
			score1++;
			((WorldFrame) super.getFrame()).stop();
			isPaused = true;
        	ImageIcon z = new ImageIcon(super.getFrame().getClass().getResource("TRON.gif"));
    		JOptionPane.showMessageDialog(getFrame(), "Player 1 wins!", "Alert!", 2, z);
    		reset();
			return;
		}
	}
}