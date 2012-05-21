import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Racer;
import info.gridworld.grid.Location;
import info.gridworld.world.TRONWorld;
import info.gridworld.world.World;

/**
 * @author 12mpolevy, 12jmooney
 * The TRON program!
 */

//the runner class; makes a world
public class TRONRunner {
	
	//directions: When a racer runs into a colored block, that racer loses the match. The objective of the game is to crash the other racer either by outlasting them or entrapping them. If both players crash at the same time, it is a tie. Player 1 controls: "A" - left; "W" - up; "D" - left; "S" - down. Player 2 uses the arrow pad.
	
	//main method, creates the GUI
	public static void main(String[] args){
		TRONWorld w = new TRONWorld();
		w.show();
	}
}