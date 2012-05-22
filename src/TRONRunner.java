import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Racer;
import info.gridworld.grid.Location;
import info.gridworld.world.AePlayWave;
import info.gridworld.world.TRONWorld;
import info.gridworld.world.World;

/**
 * @author 12mpolevy, 12jmooney
 * The TRON program!
 */

//the runner class; makes a world
public class TRONRunner{
		
	//main method, creates the GUI
	public static void main(String[] args){
		TRONWorld w = new TRONWorld();
		w.show();
    	ImageIcon z = new ImageIcon(w.getFrame().getClass().getResource("TRON.gif"));
		JOptionPane.showMessageDialog(w.getFrame(), "Directions:\n\nWhen a racer runs into a colored block, that racer loses the match.\nThe objective of the game is to crash the other racer either by outlasting them or entrapping them.\nIf both players crash at the same time, it is a tie.\n\nPlayer 1 controls: A - left; W - up; D - left; S - down. \n\nPlayer 2 uses the arrow pad.\n\nSPACEBAR pauses and starts the game.", "Welcome to TRON!", 2, z);
		//new AePlayWave("Aerodynamic.wav").start();
	}
}