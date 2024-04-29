package game;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

import base.*;

public class DefaultColisionChecker implements  ColisionChecker
{
    protected Vector _listColideable; 
    protected Vector _listMoveable;
    protected Vector _listMoveableTmp;
    protected ColisionRules _colisionRules;
    
    public DefaultColisionChecker()
    {
	_listColideable=new Vector();
	_listMoveable=new Vector();
	//  _colisonRules=new DefaultColisionRules();
    }
    @SuppressWarnings("unchecked")
	public void addColideable(Colideable p)
    {

	if (p instanceof Moveable)
	    _listMoveable.add(p);
	else
	    _listColideable.add(p);
    }

    public void removeColideable(Colideable p)
    {
	if (p instanceof Moveable)
	    _listMoveable.remove(p);
	else
	    _listColideable.remove(p);
    }

    public void setColisionRules(ColisionRules colisionRules)
    {
	_colisionRules=colisionRules;
    }

    @SuppressWarnings("unchecked")
	public void computeAllColisions()
    {
	Vector result=new Vector();
	_listMoveableTmp=new Vector(_listMoveable);
	Iterator iterator=_listMoveable.iterator();

	for (;iterator.hasNext();)
	    {
		Colideable tmp=(Colideable)iterator.next();
		computeOneColision(tmp,result);
		_listMoveableTmp.remove(tmp);
	    }


	_colisionRules.colisionProcessing(result);
    }


    @SuppressWarnings("unchecked")
	public void computeOneColision(Colideable colideable, Vector result)
    {
	Area colideableArea,targetArea;
	Rectangle boundingBoxTarget,boundingBoxColideable;

	//Compute the intersection with Obstacles
	Shape intersectShape;
	if (colideable instanceof Moveable)
	    intersectShape=IntersectTools.getIntersectShape((Moveable)colideable,
							    new DefaultMove( ((Moveable)colideable).getMove().getDir(),
									     -1 * ((Moveable)colideable).getMove().getSpeed() ) );
	else
	    intersectShape=colideable.getBoundingBox();

	colideableArea=new Area(intersectShape);
	boundingBoxColideable=intersectShape.getBounds();

	Iterator iterator=_listColideable.iterator();
	while (iterator.hasNext())
	    {
		Colideable targetColideable=(Colideable)iterator.next();
		if (targetColideable!=colideable)
		    {
			Shape targetShape;
			targetShape=targetColideable.getBoundingBox();

			boundingBoxTarget=targetShape.getBounds();

			if (boundingBoxColideable.intersects(boundingBoxTarget))
			    {
				targetArea=new Area(targetShape);
				targetArea.intersect(colideableArea);
				if (!targetArea.isEmpty())
				    {
					Vector res=new Vector();
					res.add(colideable);
					res.add(targetColideable);
					result.add(res);
				    }
			    }
		    }
	    }

	iterator=_listMoveableTmp.iterator();
	while (iterator.hasNext())
	    {
		Colideable targetColideable=(Colideable)iterator.next();
		if (targetColideable!=colideable)
		    {
			Shape targetShape;
			targetShape=IntersectTools.getIntersectShape(
						      (Moveable)targetColideable,
						      new DefaultMove( ((Moveable)targetColideable).getMove().getDir(),
								       - ((Moveable)targetColideable).getMove().getSpeed() 
								       ));
			boundingBoxTarget=targetShape.getBounds();

			if (boundingBoxColideable.intersects(boundingBoxTarget))
			    {
				targetArea=new Area(targetShape);
				targetArea.intersect(colideableArea);
				if (!targetArea.isEmpty())
				    {
					Vector res=new Vector();
					res.add(colideable);
					res.add(targetColideable);
					result.add(res);
				    }
			    }
		    }
	    }

    }
}









