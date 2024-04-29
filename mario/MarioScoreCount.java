package mario;

import game.DefaultCount;
import game.LifeCount;

public class MarioScoreCount extends DefaultCount {

	static MarioScoreCount _instance;
	
	private MarioScoreCount() {
		super();
	}
	
	public static MarioScoreCount getInstance() {
		if (_instance == null) {
			_instance = new MarioScoreCount();
		}
		return _instance;
	}

	public void setCount(int c) {
		if (c % 100 == 0) {
			LifeCount life = LifeCount.instance();
			life.setCount(life.getCount() + 1);
		}
	}
}
