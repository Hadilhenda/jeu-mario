package mario;

import game.*;
import base.*;

import java.util.*;
import java.lang.reflect.*;
import java.awt.Point;
import base.Colideable;

public class MarioColisionRules implements ColisionRules {

	protected Vector _ennemies = new Vector();
	protected boolean managePacmanDeath;

	private boolean isColision(Mario m, Brick b) {
		int directionY = m.getDriver().getMove(m).getDir().y;
		if (directionY == Move.MOVE_UP) {
			if (m.getPos().y - m.getBoundingBox().height + m.getBoundingBox().y <= b.getBoundingBox().y + b.getBoundingBox().height && m.getPos().y + m.getBoundingBox().height + m.getBoundingBox().y > b.getBoundingBox().y) {
				if (m.getPos().x + m.getBoundingBox().width / 2 < b.getBoundingBox().x + b.getBoundingBox().width) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setUniverse(Universe universe) {}

	@SuppressWarnings("unchecked")
	public void addGoomBass(MarioGoombass g) {
		_ennemies.addElement(g);
	}

	public void colisionProcessing(Vector colideables) {
		managePacmanDeath = true;
		for (int i = 0; i < colideables.size(); i++) {
			Vector tmp = (Vector) colideables.elementAt(i);
			try {
				colision((Colideable) tmp.elementAt(0), (Colideable) tmp
						.elementAt(1));
			} catch (Exception e) {
			}
		}
	}

	public void colision(MarioGoombass g, Mario m) {
		if (m.isDeadUnvulnerable()) {
			return;
		}
		if (!g.isDead()) {
			if (!m.isVulnerable()) {
				g.setDead();
			}
			else {
				if ((m.getPos().y < g.getPos().y) && m.getMove().getDir().y == Move.MOVE_DOWN){
					g.setDead();
				}
				else {
					if (m.isSuper()) {
						m.setSuper(false);
					}
					else {
						if (!m.isDeadUnvulnerable()) {
							m.setDead();
						}
					}
				}
			}
		}
	}

	public void colision(MarioGoombass g, GoldCoin c) {
	}

	public void colision(Mario m, GoldCoin c) {
		ScoreCount tmp = ScoreCount.instance();
		tmp.setCount(tmp.getCount() + 1);
		MarioUniverse.getInstance().removeGameEntity(c);
	}

	public void colision(Mario m, Brick b) {
		(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(false);
		if (isColision(m, b)) {
			(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(true);
			if (m.isSuper()) {
				b.setBreak(true);
			}
		}
	}

	public void colision(Mario m, BonusBrick b) {
		(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(false);
		if (b.isActive()) {
			if (isColision(m, b)) {
				(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(true);
				MarioWorld world = MarioWorld.getInstance();
				if (b.bonusSuper()) {
					MushroomBonus mushroom = new MushroomBonus(b.getCanvas(), new Point(b.getPos().x - 4, b.getPos().y - b.getBoundingBox().height + 8));
					mushroom.setObservable(Screen.getInstance());
					mushroom.update(Screen.getInstance(), null);
					world.addObstacle(mushroom);
					MarioUniverse.getInstance().addGameEntity(mushroom);
				}
				else if (b.bonusInvincible()) {
					StarBonus star = new StarBonus(b.getCanvas(), new Point(b.getPos().x + 4, b.getPos().y - b.getBoundingBox().height + 8));
					star.setObservable(Screen.getInstance());
					star.update(Screen.getInstance(), null);
					world.addObstacle(star);
					MarioUniverse.getInstance().addGameEntity(star);
				}
				else if (b.bonusCoin()) {
					CoinBonus coin = new CoinBonus(b.getCanvas(), new Point(b.getPos().x + 4, b.getPos().y - b.getBoundingBox().height + 8));
					coin.setObservable(Screen.getInstance());
					coin.update(Screen.getInstance(), null);
					world.addObstacle(coin);
					MarioUniverse.getInstance().addGameEntity(coin);
					ScoreCount score = ScoreCount.instance();
					score.setCount(score.getCount() + 1);
				}
				b.setActive(false);
			}
		} else {
			(( MarioKeyboardStrategie) ((MarioGameMoveableDriver) m.getDriver()).getStrategy()).setStopJumping(true);
		}
	}
	
	public void colision(Mario m, MushroomBonus b) {
		if (b.bonusSuper()) {
			m.setSuper(true);
		}
		MarioUniverse.getInstance().removeGameEntity(b);
		MarioWorld.getInstance().removeObstacle(b);
		MarioUniverse.getInstance().removeGameEntity(b);
	}
	
	public void colision(Mario m, StarBonus b) {
		m.setUnvulnerable(true);
		MarioUniverse.getInstance().removeGameEntity(b);

		MarioWorld.getInstance().removeObstacle(b);
		MarioUniverse.getInstance().removeGameEntity(b);
	}
	
	public void colision(Colideable e1, Colideable e2) throws Exception {
		Object[] param = new Object[2];
		Class[] paramClass = new Class[2];
		Class<? extends MarioColisionRules> receiverClass = this.getClass();
		param[0] = e1;
		paramClass[0] = e1.getClass();
		param[1] = e2;
		paramClass[1] = e2.getClass();
		Method m = null;
		try {
			m = receiverClass.getMethod("colision", paramClass);
			m.invoke(this, param);
		} catch (Exception e) {
			Class tmpclass = paramClass[0];
			Object tmpobject = param[0];
			paramClass[0] = paramClass[1];
			paramClass[1] = tmpclass;
			param[0] = paramClass[1];
			param[1] = tmpobject;
			m = receiverClass.getMethod("colision", paramClass);
			m.invoke(this, param);
		}
	}
}
