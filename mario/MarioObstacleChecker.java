package mario;

import game.DefaultObstacleChecker;
import game.IllegalMoveException;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Iterator;
import java.util.Vector;

import base.IntersectTools;
import base.Move;
import base.Moveable;
import base.Obstacle;

public class MarioObstacleChecker extends DefaultObstacleChecker
{

    public void moveValidation(Moveable m, Move mov)throws IllegalMoveException, WalkFloorException, OpenPipeException
	{
    	Shape intersectShape = IntersectTools.getIntersectShape(m, mov);
    	
	    //Compute the intersection with Obstacles
	    Iterator iterator=_listObstacles.iterator();
	    Vector<Obstacle> result=new Vector<Obstacle>();
	    Rectangle tmpInte;
	    
	    iterator=_listObstacles.iterator();
	    tmpInte=(intersectShape.getBounds());
	    while (iterator.hasNext())
		{
		    Obstacle tmpObstacle=(Obstacle)iterator.next();
		    Rectangle tmpB=tmpObstacle.getBoundingBox();
		    if (tmpInte.intersects(tmpB))
		    {
				result.add(tmpObstacle);
		    }
		}

	    if (!result.isEmpty()) {
	    	_obstacleRules.moveValidationProcessing(result, m);
	    }
	}
}










