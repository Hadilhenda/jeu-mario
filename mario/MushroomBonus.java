package mario;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

public class MushroomBonus extends Bonus {
	
	public MushroomBonus(Canvas defaultCanvas, Point pos) {
		super(defaultCanvas, pos);
	}

	public void draw(Graphics g) {
		animateHandler();
		g.drawImage(_image.getImage(), (int) getPos().getX() - _posEcran, (int) getPos().getY() - BONUS_SIZE / 2, (int) getPos().getX() + BONUS_SIZE   - _posEcran, (int) getPos().getY() + BONUS_SIZE / 2, _spriteIndex * BONUS_SIZE, 0, (_spriteIndex + 1) * BONUS_SIZE, BONUS_SIZE, null);
	}

	public boolean bonusSuper() {
		return true;
	}

}
