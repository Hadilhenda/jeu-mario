package game;

import java.util.*;

import base.Colideable;
import base.Drawable;
import base.Moveable;

public class DefaultUniverse implements Universe {
	protected Vector gameEntities = new Vector();
    protected DrawingGameBoard drawingGameBoard;
    protected ColisionChecker _colisionChecker;

    public DefaultUniverse(World w, DrawingGameBoard dGB, ColisionChecker col) {
    	this.drawingGameBoard = dGB;
        _colisionChecker=col;
    }
    
    @SuppressWarnings("unchecked")
	public void addGameEntity(GameEntity gameEntity) {
    	gameEntities.add(gameEntity);
	if(gameEntity instanceof Drawable)
	    drawingGameBoard.addDrawableEntity((Drawable) gameEntity);
	if(gameEntity instanceof Colideable) {
		_colisionChecker.addColideable((Colideable) gameEntity);
	}
    }
    
    public void removeGameEntity(GameEntity gameEntity) {
			gameEntities.remove(gameEntity);
			if( gameEntity instanceof Drawable)
			    drawingGameBoard.removeDrawableEntity((Drawable) gameEntity);
			if(gameEntity instanceof Colideable)
				_colisionChecker.removeColideable((Colideable) gameEntity);
    }

    public void animate()  {
    	try {
			Iterator iterator = gameEntities.iterator();
			for (;iterator.hasNext();) {
			    GameEntity tmp = (GameEntity)iterator.next();
			    if (tmp instanceof Moveable)
				((Moveable)tmp).animate();
			}
    	}
    	catch (Exception e) {}
    }

    public void colision() {
    	_colisionChecker.computeAllColisions();
    }

}
