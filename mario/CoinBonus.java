package mario;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

public class CoinBonus extends Bonus {

	private final int ANIMATION_LENGTH = 20;
	private final int SPRITE_POSITION = 5;
	private int _animationTimer;
	
	public CoinBonus(Canvas defaultCanvas, Point pos) {
		super(defaultCanvas, pos);
		_spriteNumber = 1;
		_animationTimer = 0;
	}

	public void draw(Graphics g) {
		animateHandler();
		++_animationTimer;
		g.drawImage(_image.getImage(), (int) getPos().getX() - BONUS_SIZE / 4 - _posEcran, (int) getPos().getY() - BONUS_SIZE / 2 - _animationTimer, (int) getPos().getX() + BONUS_SIZE - BONUS_SIZE / 4 - _posEcran, (int) getPos().getY() + BONUS_SIZE / 2 - _animationTimer, (_spriteIndex + SPRITE_POSITION) * BONUS_SIZE, 0, (_spriteIndex + SPRITE_POSITION + 1) * BONUS_SIZE, BONUS_SIZE, null);
		if (_animationTimer >= ANIMATION_LENGTH) {
			MarioUniverse.getInstance().removeGameEntity(this);
			MarioWorld.getInstance().removeObstacle(this);
		}
	}

	public boolean bonusCoin() {
		return true;
	}
}
