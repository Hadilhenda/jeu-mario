package mario;

import game.GameEntity;

import java.awt.*;

import base.Colideable;
import base.Drawable;
import base.ImageDrawable;

public class GoldCoin extends MarioScroll implements Drawable, GameEntity, Colideable {

	private final String GOLD_COIN_IMAGE = "/images/gold.gif";
	private final int GOLD_COIN_SIZE = 32;
	private final int GOLD_COIN_SPRITE_NUMBER = 4;
	
	protected static ImageDrawable _image = null;
	protected Point _position;
	protected int _spriteNumber = 0;
	private boolean _refresh = true;

	public GoldCoin(Canvas defaultCanvas, Point pos) {
		if (_image == null)
			_image = new ImageDrawable(GOLD_COIN_IMAGE, defaultCanvas, 2);
		_position = pos;
	}

	public Point getPos() {
		return _position;
	}

	public void draw(Graphics g) {
		animateHandler();
		g.drawImage(_image.getImage(), (int) getPos().getX() - GOLD_COIN_SIZE / 4 - _posEcran, (int) getPos().getY() - GOLD_COIN_SIZE / 4, (int) getPos().getX() + GOLD_COIN_SIZE - GOLD_COIN_SIZE / 4 - _posEcran, (int) getPos().getY() + GOLD_COIN_SIZE - GOLD_COIN_SIZE / 4, _spriteNumber * GOLD_COIN_SIZE, 0, (_spriteNumber + 1) * GOLD_COIN_SIZE, GOLD_COIN_SIZE, null);
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) _position.getX() + 4, (int) _position.getY() + 4, GOLD_COIN_SIZE - 8, GOLD_COIN_SIZE - 8));
	}

	public void animateHandler() {
		_refresh = !_refresh;
		if (_refresh) {
			_spriteNumber++;
			_spriteNumber = _spriteNumber % GOLD_COIN_SPRITE_NUMBER;
		}
	}

}
