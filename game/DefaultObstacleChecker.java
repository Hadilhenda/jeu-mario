package game;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.Iterator;
import java.util.Vector;

import mario.OpenPipeException;
import mario.WalkFloorException;
import base.IntersectTools;
import base.Move;
import base.Moveable;
import base.Obstacle;

public class DefaultObstacleChecker implements ObstacleChecker
{
    protected Vector _listObstacles; 
    protected ObstacleRules _obstacleRules;

    public DefaultObstacleChecker()
	{
	    _listObstacles=new Vector();
	    _obstacleRules=new DefaultObstacleRules();
	}

    @SuppressWarnings("unchecked")
	public void addObstacle(Obstacle p)
	{
	    _listObstacles.add(p);
	}
    public void removeObstacle(Obstacle p)
	{
	    _listObstacles.remove(p);
	}
    public void setObstacleRules(ObstacleRules obstacleRules)
	{
	    _obstacleRules=obstacleRules;
	}

    @SuppressWarnings("unchecked")
	public void moveValidation(Moveable m,Move mov)throws IllegalMoveException, WalkFloorException, OpenPipeException
	{

	    Shape intersectShape=IntersectTools.getIntersectShape(m,mov);

	    //Compute the intersection with Obstacles
	    Iterator iterator=_listObstacles.iterator();
	    Vector result=new Vector();
	    Area myArea,tmpArea;
	    myArea=new Area(intersectShape);
	    Rectangle tmpInte;
	    tmpInte=(intersectShape.getBounds());
	    while (iterator.hasNext())
		{
		    Obstacle tmpObstacle=(Obstacle)iterator.next();
		    Rectangle tmpB=tmpObstacle.getBoundingBox();
		    if (tmpInte.intersects(tmpB))
		    {
			tmpArea=new Area(tmpB);
			tmpArea.intersect(myArea);
			if (!tmpArea.isEmpty())
			    {
				result.add(tmpObstacle);
			    }
		    }
		}
	    if (!result.isEmpty())
		_obstacleRules.moveValidationProcessing(result,m);
	}
}










