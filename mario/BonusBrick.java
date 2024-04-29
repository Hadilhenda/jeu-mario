package mario;

import java.awt.Canvas;

public class BonusBrick extends Brick implements Bonusable {

	public static final int BONUS_BRICK_COIN = 0;
	public static final int BONUS_BRICK_INVINCIBLE = 1;
	public static final int BONUS_BRICK_SUPER = 2;
	public static final int BONUS_BRICK_ACTIVE = 3;
	public static final int BONUS_BRICK_INACTIVE = 7;
	
	private int m_iBonus;
	private Canvas _canvas;
	
	public BonusBrick(Canvas defaultCanvas, int x, int y, int bonus, boolean in) {
		super(defaultCanvas, x, y, in);
		if (bonus == BONUS_BRICK_COIN || bonus == BONUS_BRICK_INVINCIBLE || bonus == BONUS_BRICK_SUPER) {
			m_iBonus = bonus;
		}
		_spritePosition = BONUS_BRICK_ACTIVE;
		_canvas = defaultCanvas;
	}

	public boolean bonusCoin() {
		return m_iBonus == BONUS_BRICK_COIN;
	}

	public boolean bonusInvincible() {
		return m_iBonus == BONUS_BRICK_INVINCIBLE;
	}

	public boolean bonusSuper() {
		return m_iBonus == BONUS_BRICK_SUPER;
	}

	public boolean isActive() {
		return _spritePosition == BONUS_BRICK_ACTIVE;
	}
	
	protected Canvas getCanvas() {
		return _canvas;
	}
	
	public void setActive(boolean active) {
		_spritePosition = BONUS_BRICK_INACTIVE;
	}
	
}
