package mario;


import game.DefaultWorld;
import game.DrawingGameBoard;
import game.ObstacleChecker;

import java.util.*;

import base.Colideable;
import base.Drawable;
import base.Obstacle;


public class MarioWorld extends DefaultWorld
{
    protected Vector<Obstacle> obstacleEntities =  new Vector<Obstacle>();
    private static MarioWorld _instance;
    
    public static MarioWorld createInstance(DrawingGameBoard drawingGameBoard, ObstacleChecker obsChk) {
    	_instance = new MarioWorld(drawingGameBoard, obsChk);
    	return _instance;
    }
    
    public static MarioWorld getInstance() {
    	return _instance;
    }
    
    public void setDrawingGameBord(DrawingGameBoard drawingGameBoard) {
    	_instance.setDrawingGameBord(drawingGameBoard);
    }

    public void setObstacleChecker(ObstacleChecker obsChk) {
    	_instance.setObstacleChecker(obsChk);
    }
    
    private MarioWorld(DrawingGameBoard drawingGameBoard, ObstacleChecker obsChk) 
    { 
    	super(drawingGameBoard, obsChk);
    }


    public  void removeObstacle(Obstacle obs) // Exception pour contrat...
    {
    	try {
			obstacleEntities.removeElement(obs);
			if(obs instanceof Drawable) {
				if (!(obs instanceof Colideable)) {
					drawingGameBoard.removeDrawableEntity((Drawable) obs);
				}
			}
			obstacleChecker.removeObstacle((Obstacle) obs);
    	}
    	catch (Exception e) {}
    }
}
