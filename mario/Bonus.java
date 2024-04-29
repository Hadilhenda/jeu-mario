package mario;

import game.GameEntity;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;

import base.Colideable;
import base.Drawable;
import base.ImageDrawable;
import base.Obstacle;

public abstract class Bonus extends MarioScroll implements Drawable, Bonusable, Colideable, Obstacle, GameEntity {

	private final String BONUS_IMAGE = "/images/bonus.gif";
	protected final int BONUS_SIZE = 32;
	protected final int BONUS_HEIGHT_INSET = 4;
	private final int REFRESH_RATE = 4;
	
	protected static ImageDrawable _image = null;
	protected Point _position;
	protected int _spriteIndex = 0;
	protected int _spriteNumber = 1;
	protected int _refresh = 4;
	
	protected Bonus(Canvas defaultCanvas, Point pos) {
		if (_image == null)
			_image = new ImageDrawable(BONUS_IMAGE, defaultCanvas, 5);
		_position = pos;
	}

	public boolean bonusCoin() {
		return false;
	}

	public boolean bonusSuper() {
		return false;
	}

	public boolean bonusInvincible() {
		return false;
	}
	
	public Point getPos() {
		return _position;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) _position.getX() + BONUS_SIZE / 4, (int) _position.getY(), BONUS_SIZE - BONUS_SIZE / 2, BONUS_SIZE / 2));
	}

	public void animateHandler() {
		_refresh++;
		_refresh = _refresh % REFRESH_RATE;
		if (_refresh == 0) {
			_spriteIndex++;
			_spriteIndex = _spriteIndex % _spriteNumber;
		}
	}

}
