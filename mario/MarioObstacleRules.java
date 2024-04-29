package mario;

import game.IllegalMoveException;
import game.ObstacleRules;

import java.util.*;

import base.Moveable;
import base.Obstacle;

public class MarioObstacleRules implements ObstacleRules {
	public void moveValidationProcessing(Vector obs, Moveable m) throws IllegalMoveException, WalkFloorException, OpenPipeException {
		WalkFloorException walkException = null;
		for (int i = 0; i < obs.size(); i++) {
			Obstacle obstacle = (Obstacle) obs.elementAt(i);
			try {
				obstacleProcessing(m, obstacle);
			}
			catch (IllegalMoveException exc) {
				throw new IllegalMoveException();
			}
			catch (WalkFloorException exc) {
				if (walkException == null) {
					walkException = new WalkFloorException();
				}
			}
		}
		if (walkException != null) {
			throw walkException;
		}
	}

	public void obstacle(Mario m, Wall w) throws IllegalMoveException, WalkFloorException, OpenPipeException {
		if (m.getPos().y + m.getBoundingBox().height + m.getBoundingBox().y <= w.getBoundingBox().y) {
			(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(false);
			if (w.isOpen()) {
				throw new OpenPipeException(w);
			}
			throw new WalkFloorException();
		}
		else {	
			throw new IllegalMoveException();
		}
	}
	
	public void obstacle(Mario m, Brick b) throws IllegalMoveException, WalkFloorException {
		if (m.getPos().y + m.getBoundingBox().height + m.getBoundingBox().y <= b.getBoundingBox().y ) {
			(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(false);
			throw new WalkFloorException();
		}
		else {
			throw new IllegalMoveException();
		}
	}
		
	public void obstacle(MarioGoombass m, Wall w) throws IllegalMoveException, WalkFloorException {
		if (m.getPos().y + m.getBoundingBox().height < w.getBoundingBox().y) {
			throw new WalkFloorException();
		}
		else {	
			throw new IllegalMoveException();
		}
	}
	
	public void obstacle(MarioGoombass m, Brick b) throws IllegalMoveException, WalkFloorException {
		if (m.getPos().y + m.getBoundingBox().height < b.getBoundingBox().y ) {
			throw new WalkFloorException();
		}
		else {	
			throw new IllegalMoveException();
		}
	}
	
	public void obstacleProcessing(Moveable e1, Obstacle e2) throws WalkFloorException, IllegalMoveException, OpenPipeException {		
		if (e1 instanceof Mario) {		
			if (e2 instanceof Wall) {
				obstacle((Mario) e1, (Wall) e2);
			}
			else if (e2 instanceof Brick) {
				obstacle((Mario) e1, (Brick) e2);
			}
		}
		else {
			if (e2 instanceof Wall) {
				obstacle((MarioGoombass) e1, (Wall) e2);
			}
			else if (e2 instanceof Brick) {
				obstacle((MarioGoombass) e1, (Brick) e2);
			}
		}
	}
	
}
