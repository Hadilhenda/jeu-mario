package mario;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

public class StarBonus extends Bonus {

	private final int SPRITE_POSITION = 2;
	
	public StarBonus(Canvas defaultCanvas, Point pos) {
		super(defaultCanvas, pos);
		_spriteNumber = 4;
	}

	public void draw(Graphics g) {
		animateHandler();
		g.drawImage(_image.getImage(), (int) getPos().getX() - BONUS_SIZE / 4 - _posEcran, (int) getPos().getY() - BONUS_SIZE / 2, (int) getPos().getX() + BONUS_SIZE - BONUS_SIZE / 4 - _posEcran, (int) getPos().getY() + BONUS_SIZE / 2, (_spriteIndex + SPRITE_POSITION) * BONUS_SIZE, 0, (_spriteIndex + SPRITE_POSITION + 1) * BONUS_SIZE, BONUS_SIZE, null);
	}

	public boolean bonusInvincible() {
		return true;
	}
}
