package mario;

import game.GameEntity;
import game.GameMoveable;
import base.ImageDrawable;

import java.awt.*;
import java.util.Observable;

import base.Colideable;
import base.Drawable;

public class MarioGoombass extends GameMoveable implements Drawable, GameEntity, Colideable, MarioScrollable {
	
	private final int GOOMBASS_SIZE = 16;
	private final int GOOMBASS_IMAGE_WALK_NUMBER = 3;
	private final int GOOMBASS_IMAGE_DEAD = 2;
	private final int GOOMBASS_DEAD_ANIMATION_TIME = 10;
	
	private int posEcran;
	private Observable observable;
	protected static ImageDrawable _image = null;
	protected int _spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean animate = true;
	private int _refresh = 0;
	private int _deadTimer = 0;
	private boolean _isDead = false;

	public MarioGoombass(Canvas defaultCanvas) {
		if (_image == null)
			_image = new ImageDrawable("/images/ennemies.gif", defaultCanvas, 5);
	}

	public void setDead() {
		_isDead = true;
		_deadTimer = GOOMBASS_DEAD_ANIMATION_TIME;
	}
	
	public boolean isDead() {
		return _isDead;
	}
	
	public void draw(Graphics g) {
		animate = true;
		int x = (int) getPos().getX();
		int y = (int) getPos().getY();
		if (_isDead) {
			if (_deadTimer > GOOMBASS_DEAD_ANIMATION_TIME / 2) {
				y -= (GOOMBASS_DEAD_ANIMATION_TIME - _deadTimer) * GOOMBASS_SIZE / 8;
			}
			else {
				y -= GOOMBASS_DEAD_ANIMATION_TIME / 2 * GOOMBASS_SIZE / 8;
				y += (GOOMBASS_DEAD_ANIMATION_TIME / 2 - _deadTimer) * GOOMBASS_SIZE / 8;
			}
			
		}
		g.drawImage(_image.getImage(), x - posEcran - GOOMBASS_SIZE / 2, y, x + GOOMBASS_SIZE + GOOMBASS_SIZE / 2 - posEcran, y + GOOMBASS_SIZE, _spriteNumber * GOOMBASS_SIZE * 2, GOOMBASS_SIZE, (_spriteNumber + 1) * GOOMBASS_SIZE * 2, GOOMBASS_SIZE * 2, null);
	}

	public void animateHandler() {
		if (animate) {
			_refresh = ++_refresh % 4;
			if (_refresh == 0) {
				_spriteNumber++;
				_spriteNumber = _spriteNumber % (GOOMBASS_IMAGE_WALK_NUMBER - 1);
			}
		}
		if (_isDead) {
			_spriteNumber = GOOMBASS_IMAGE_DEAD;
			--_deadTimer;
			if (_deadTimer <= 0) {
				MarioUniverse.getInstance().removeGameEntity(this);
			}
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, GOOMBASS_SIZE, GOOMBASS_SIZE - 1));
	}
	
	public void setObservable(Observable obs){
		observable=obs;
		observable.addObserver(this);
	}

	public void update(Observable obs, Object arg1) {
		if(obs instanceof Screen){
			Screen scr = (Screen) obs;
			posEcran = scr.getPosEcran();
		}
	}
}