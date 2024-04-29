package base;

import java.awt.*;

public class IntersectTools 
{
 
   public static Shape getIntersectShape(Moveable moveable, Move mov)
    {
	//Compute the intersection shape using the moveable properties
	int dX=(int)mov.getDir().getX();
	int dY=(int)mov.getDir().getY();
	int v=mov.getSpeed(); 

	//We need the bounding box of the moveable
	int x1=(int)moveable.getPos().getX()+(int)moveable.getBoundingBox().getX();
	int y1=(int)moveable.getPos().getY()+(int)moveable.getBoundingBox().getY();
	int x2=x1+(int)moveable.getBoundingBox().getWidth();
	int y2=y1+(int)moveable.getBoundingBox().getHeight();

	//We build the intersection polygon now
	//As usual we need to consider the 8 direction to build different kind of shape
	Shape intersectShape;

	//The first quarter of the plane
	if ((dX>0) && (dY>0))
	    {
		intersectShape=new Polygon();
		((Polygon)intersectShape).addPoint(x1,y1);
		((Polygon)intersectShape).addPoint(x1,y2);
		((Polygon)intersectShape).addPoint(x2,y2);
		((Polygon)intersectShape).addPoint(x2+dX*v,y2+dY*v);
		((Polygon)intersectShape).addPoint(x2+dX*v,y1+dY*v);
		((Polygon)intersectShape).addPoint(x1+dX*v,y1+dY*v);
	    }
	//The second quarter of the plane
	else if ((dX<0) && (dY>0))
	    {
		intersectShape=new Polygon();
		((Polygon)intersectShape).addPoint(x1,y2);
		((Polygon)intersectShape).addPoint(x2,y2);
		((Polygon)intersectShape).addPoint(x2,y1);
		((Polygon)intersectShape).addPoint(x2+dX*v,y1+dY*v);
		((Polygon)intersectShape).addPoint(x1+dX*v,y1+dY*v);
		((Polygon)intersectShape).addPoint(x1+dX*v,y2+dY*v);
	    }
	//The third quarter of the plane
	else if ((dX<0) && (dY<0))
	    {
		intersectShape=new Polygon();
		((Polygon)intersectShape).addPoint(x2,y2);
		((Polygon)intersectShape).addPoint(x2,y1);
		((Polygon)intersectShape).addPoint(x1,y1);
		((Polygon)intersectShape).addPoint(x1+dX*v,y1+dY*v);
		((Polygon)intersectShape).addPoint(x1+dX*v,y2+dY*v);
		((Polygon)intersectShape).addPoint(x2+dX*v,y2+dY*v);
	    }
	//The fourth quarter of the plane
	else if ((dX>0) && (dY<0))
	    {
		intersectShape=new Polygon();
		((Polygon)intersectShape).addPoint(x2,y1);
		((Polygon)intersectShape).addPoint(x1,y1);
		((Polygon)intersectShape).addPoint(x1,y2);
		((Polygon)intersectShape).addPoint(x1+dX*v,y2+dY*v);
		((Polygon)intersectShape).addPoint(x2+dX*v,y2+dY*v);
		((Polygon)intersectShape).addPoint(x2+dX*v,y1+dY*v);
	    }
	//And now the axis
	else if ((dX==0) && (dY>0))
	    {
		intersectShape=new Rectangle(x1,y1,x2-x1,y2-y1+dY*v);
	    }
	else if ((dX==0) && (dY<0))
	    {
		intersectShape=new Rectangle(x1,y1+dY*v,x2-x1,y2-y1+dY*v);
	    }
	else if ((dX>0) && (dY==0))
	    {
		intersectShape=new Rectangle(x1,y1,x2-x1+dX*v,y2-y1);
	    }
	else  if ((dX<0) && (dY==0))
	    {
		intersectShape=new Rectangle(x1+dX*v,y1,x2-x1+dX*v,y2-y1);
	    }
	else
	    {
		intersectShape=new Rectangle(x1,y1,x2-x1,y2-y1);
	    }
	return intersectShape;

    }


}

