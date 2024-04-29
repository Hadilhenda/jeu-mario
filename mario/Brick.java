package mario;

import game.GameEntity;

import java.awt.*;

import base.Colideable;
import base.Drawable;
import base.Obstacle;
import base.ImageDrawable;

public class Brick extends MarioScroll implements Drawable, Obstacle, GameEntity, Colideable  {

	private final String BRICK_IMAGE_OUT = "/images/obstacle.gif";
	private final String BRICK_IMAGE_IN = "/images/obstacle_vert.gif";
	private final int BRICK_BREAK_ANIMATION_TIME = 100;
	protected final int BRICK_SIZE = 16;
	protected final int BRICK_DRAW_SIZE = 24;
	protected final int BRICK_HEIGHT_INSET = 4;
	
	protected static ImageDrawable _image = null;
	protected Point _position;
	protected int _spritePosition = 1;
	protected boolean _isBreak;
	private static boolean _inside;
	
	private int _breakAnimationIndex = 0; 
	
	public Brick(Canvas defaultCanvas, int x, int y, boolean in) {
		if (_image == null || _inside != in) {
			if (in) {
				_image = new ImageDrawable(BRICK_IMAGE_IN, defaultCanvas, 2);
			} else {
				_image = new ImageDrawable(BRICK_IMAGE_OUT, defaultCanvas, 2);
			}
			_inside = in;
		}
		_position = new Point(x, y);
		_posEcran=0;
	}

	public void draw(Graphics g) {
		if (_isBreak) {
			_breakAnimationIndex += 10;
			g.drawImage(_image.getImage(), _position.x - _breakAnimationIndex - _posEcran, _position.y - _breakAnimationIndex, _position.x - _breakAnimationIndex + BRICK_SIZE / 2 - _posEcran, _position.y - _breakAnimationIndex + BRICK_SIZE / 2, BRICK_SIZE * _spritePosition, BRICK_SIZE / 2, BRICK_SIZE * (_spritePosition + 1) - BRICK_SIZE / 2, BRICK_SIZE, null);
			g.drawImage(_image.getImage(), _position.x + _breakAnimationIndex - _posEcran, _position.y - _breakAnimationIndex, _position.x + _breakAnimationIndex + BRICK_SIZE / 2 - _posEcran, _position.y - _breakAnimationIndex + BRICK_SIZE / 2, BRICK_SIZE * _spritePosition + BRICK_SIZE / 2, BRICK_SIZE / 2, BRICK_SIZE * (_spritePosition + 1), BRICK_SIZE, null);
			g.drawImage(_image.getImage(), _position.x - _breakAnimationIndex - _posEcran, _position.y + _breakAnimationIndex, _position.x - _breakAnimationIndex + BRICK_SIZE / 2 - _posEcran, _position.y + _breakAnimationIndex + BRICK_SIZE / 2, BRICK_SIZE * _spritePosition, 0, BRICK_SIZE * (_spritePosition + 1) - BRICK_SIZE / 2, BRICK_SIZE / 2, null);
			g.drawImage(_image.getImage(), _position.x + _breakAnimationIndex - _posEcran, _position.y + _breakAnimationIndex, _position.x + _breakAnimationIndex + BRICK_SIZE / 2 - _posEcran, _position.y + _breakAnimationIndex + BRICK_SIZE / 2, BRICK_SIZE * _spritePosition + BRICK_SIZE / 2, 0, BRICK_SIZE * (_spritePosition + 1) + BRICK_SIZE / 2, BRICK_SIZE / 2, null);
			if (_breakAnimationIndex == BRICK_BREAK_ANIMATION_TIME) {
				MarioWorld.getInstance().removeObstacle(this);
				MarioUniverse.getInstance().removeGameEntity(this);
			}
		}
		else {
			g.drawImage(_image.getImage(), _position.x - _posEcran, _position.y, _position.x + BRICK_DRAW_SIZE - _posEcran, _position.y + BRICK_DRAW_SIZE, BRICK_SIZE * _spritePosition, 0, BRICK_SIZE * (_spritePosition + 1), BRICK_SIZE, null);
		}
	}

	public void setBreak(boolean isBreak) {
		_isBreak = isBreak;
	}
	
	public Point getPos() {
		return _position;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(_position.x , _position.y, BRICK_DRAW_SIZE, BRICK_DRAW_SIZE - 1));
	}
		
}
