import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Racer;
import info.gridworld.world.World;

/**
 * @author 12mpolevy, 12jmooney
 * The TRON program!
 */

//the runner class; makes a world
public class TRONRunner {
	
	//main method, creates the GUI
	public static void main(String[] args){
		TRONWorld w = new TRONWorld();
		Racer x = new Racer();
		Racer y = new Racer();
		x.setColor(Color.green);
		y.setColor(Color.red);
		w.add(y);
        w.add(x);
		w.show();
	}
}