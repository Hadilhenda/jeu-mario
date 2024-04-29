package mario;

import game.*;

import java.awt.*;

import base.Colideable;
import base.Drawable;
import base.ImageDrawable;
import base.Move;
import base.Strategy;

public class Mario extends GameMoveable implements Drawable, GameEntity, Colideable {
	
	private final String MARIO_IMAGE = "/images/mario.gif";
	private final int MARIO_SIZE = 32;
	private final int MARIO_DRAW_SIZE = 48;
	
	private final int MARIO_IMAGE_WAIT = 0;
	private final int MARIO_IMAGE_WAIT_NUMBER = 1;
	private final int MARIO_IMAGE_WALK = 1;
	private final int MARIO_IMAGE_WALK_NUMBER = 3;
	private final int MARIO_IMAGE_JUMP = 4;
	private final int MARIO_IMAGE_JUMP_NUMBER = 1;
	private final int MARIO_IMAGE_DEAD = 6;
	private final int MARIO_IMAGE_DEAD_NUMBER = 1;
	private final int MARIO_IMAGE_SUPER = 1;
	private final int MARIO_IMAGE_VULNERABLE = 0;
	private final int MARIO_IMAGE_INVULNERABLE = 2;
	private final int MARIO_IMAGE_SUPER_ANIMATION = 5;
	private final int MARIO_MOVE_RIGHT = 0;
	private final int MARIO_MOVE_LEFT = 4;

	private final int MARIO_UNVULNERABLE_TIME = 100;
	private final int MARIO_SUPER_TIMER = 20;
	
	private Screen _screen;
	private int _screenPosition;
	private boolean _scrollingActivate;
	private Strategy _moveStrategy;
	
	protected static ImageDrawable _image = null;
	protected int _spriteNumber = 0;
	protected boolean _animate = false;
	protected int _vulnerableTimer = 0;
	protected boolean _isSuper = false;
	protected int _deadTimer = 0;
	protected int _superTimer = 0; 
	protected boolean _minimize; 
		
	public void setUnvulnerable(boolean unvulnerable) {
		if (unvulnerable) {
			_vulnerableTimer = MARIO_UNVULNERABLE_TIME;
		}
		else {
			_vulnerableTimer = 0;
		}
	}

	public boolean isVulnerable() {
		return (_vulnerableTimer <= 0);
	}

	public boolean isDeadUnvulnerable() {
		return _deadTimer != 0;
	}
	
	public void setDead() {
		LifeCount life = LifeCount.instance();
		life.setCount(life.getCount() - 1);
		_deadTimer = MarioDeadStrategie.DEAD_ANIMATION_TIME;
		_moveStrategy = ((MarioGameMoveableDriver) getDriver()).getStrategy();
		((MarioGameMoveableDriver) getDriver()).setStrategy(new MarioDeadStrategie());
	}
	
	public void setSuper(boolean isSuper) {
		if (!isSuper) {
			_superTimer = MARIO_SUPER_TIMER;
			_deadTimer = MARIO_SUPER_TIMER;
			_minimize = true;
		}
		_isSuper = isSuper;
	}
	
	public boolean isSuper() {
		return _isSuper;
	}
	
	public Mario(Canvas defaultCanvas) {
		if (_image == null)
			_image = new ImageDrawable(MARIO_IMAGE, defaultCanvas, 4);
		_screenPosition = 0;
		_scrollingActivate = true;
	}

	public void desactivateScrolling(){
		_scrollingActivate = false;
	}
	
	public void draw(Graphics g) {
		int spriteType = MARIO_IMAGE_WAIT;
		int iLine = (isVulnerable() ? MARIO_IMAGE_VULNERABLE : MARIO_IMAGE_INVULNERABLE) + (isSuper() ? MARIO_IMAGE_SUPER : 0);
		_animate = (MARIO_IMAGE_WAIT_NUMBER > 1);
		if (isDeadUnvulnerable()) {
			if (!_minimize) {
				spriteType = MARIO_IMAGE_DEAD;
				_animate = (MARIO_IMAGE_DEAD_NUMBER > 1);
			}
		}
		else {
			if (getMove().getDir().getX() == Move.MOVE_RIGHT) {
				spriteType = MARIO_IMAGE_WALK;
				iLine += MARIO_MOVE_RIGHT;
				_animate = (MARIO_IMAGE_WALK_NUMBER > 1);
			}
			else if (getMove().getDir().getX() == Move.MOVE_LEFT) {
				spriteType = MARIO_IMAGE_WALK;
				iLine += MARIO_MOVE_LEFT;
				_animate = (MARIO_IMAGE_WALK_NUMBER > 1);
			}
			if (getMove().getDir().getY() == Move.MOVE_UP) {
				spriteType = MARIO_IMAGE_JUMP;
				_animate = (MARIO_IMAGE_JUMP_NUMBER > 1);
			}
		}
		int posX = (int) getPos().getX();
		int posY = (int) getPos().getY();
		if (posX < _screenPosition) {
			setPos(new Point(_screenPosition, posY));
		}
		spriteType += _spriteNumber;
		g.drawImage(_image.getImage(), posX - _screenPosition, posY, posX + MARIO_DRAW_SIZE - _screenPosition, posY + MARIO_DRAW_SIZE, spriteType * MARIO_SIZE, iLine * MARIO_SIZE, (spriteType + 1) * MARIO_SIZE, (iLine + 1) * MARIO_SIZE, null);
	}

	public void animateHandler() {
		if (getPos().y >= DefaultGame.SCREEN_HEIGHT) {
			setDead();
		}
		if (_superTimer > 0) {
			--_superTimer;
		}
		if (_deadTimer > 0) {
			--_deadTimer;
			if (!isDeadUnvulnerable()) {
				if (_minimize) {
					_minimize = false;
				}
				else {
					((MarioGameMoveableDriver) getDriver()).setStrategy(_moveStrategy);
					DefaultGame.getInstance().restart();
				}
			}
		}
		if (_animate) {
			++_spriteNumber;
			_spriteNumber %= 3;
			if (!isVulnerable())
				--_vulnerableTimer;
		}
		else {
			_spriteNumber = 0;
		}
		if (_superTimer > 0) {
			--_superTimer;
			_spriteNumber = MARIO_IMAGE_SUPER_ANIMATION;
		}
	}
	
	public Rectangle getBoundingBox() {
		if (!isSuper()) {
			return (new Rectangle(MARIO_DRAW_SIZE / 2 - MARIO_SIZE / 4, MARIO_DRAW_SIZE / 2, MARIO_DRAW_SIZE / 2 - MARIO_SIZE / 4, MARIO_DRAW_SIZE / 2));
		}
		return (new Rectangle(MARIO_DRAW_SIZE / 4, 0, MARIO_DRAW_SIZE - MARIO_DRAW_SIZE / 2 - MARIO_DRAW_SIZE / 8, MARIO_DRAW_SIZE));
	}	
	
	public void animate(){
		super.animate();
		
		if(_scrollingActivate){
			_screen.actualiser(getMove());
			_screenPosition = _screen.getPosEcran();
		}
	}
	
	public void setScreen(Screen scr){
		_screen = scr;
	}
}
