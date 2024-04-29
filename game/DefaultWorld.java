package game;


import game.DrawingGameBoard;

import java.util.*;

import base.Drawable;
import base.Obstacle;


public class DefaultWorld implements World
{
    protected Vector obstacleEntities =  new Vector();
    protected DrawingGameBoard drawingGameBoard;
    protected ObstacleChecker obstacleChecker;

    public DefaultWorld(DrawingGameBoard drawingGameBoard, ObstacleChecker obsChk) 
    {
		this.drawingGameBoard = drawingGameBoard;
		obstacleChecker= obsChk;
    }

    @SuppressWarnings("unchecked")
	public  void addObstacle(Obstacle obs)
    {
		obstacleEntities.addElement(obs);
		if(obs instanceof Drawable)
		    drawingGameBoard.addDrawableEntity((Drawable) obs);
		obstacleChecker.addObstacle((Obstacle) obs);
    }
    public  void removeObstacle(Obstacle obs) // Exception pour contrat...
    {
	obstacleEntities.removeElement(obs);
	if(obs instanceof Drawable)
	    drawingGameBoard.removeDrawableEntity((Drawable) obs);
	obstacleChecker.removeObstacle((Obstacle) obs);
    }
}
