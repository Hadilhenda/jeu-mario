package mario;

import base.Colideable;
import base.Drawable;
import game.ColisionChecker;
import game.DefaultUniverse;
import game.DrawingGameBoard;
import game.GameEntity;
import game.World;

public class MarioUniverse extends DefaultUniverse {
	
	private static MarioUniverse _instance;
	
	public static MarioUniverse createInstance(World world, DrawingGameBoard drawingGameBoard, ColisionChecker colisionChecker) {
		_instance = new MarioUniverse(world, drawingGameBoard, colisionChecker);
		return _instance;
	}
	
	public static MarioUniverse getInstance() {
		return _instance;
	}

	private MarioUniverse(World world, DrawingGameBoard drawingGameBoard, ColisionChecker colisionChecker) {
		super(world, drawingGameBoard, colisionChecker);
	}

	public void removeGameEntity(GameEntity gameEntity) {
		try {
			gameEntities.remove(gameEntity);
			if( gameEntity instanceof Drawable) {
				drawingGameBoard.removeDrawableEntity((Drawable) gameEntity);
			}
			if(gameEntity instanceof Colideable) {
			    _colisionChecker.removeColideable((Colideable) gameEntity);
			}
		}
		catch (Exception e) {}
	}
}
