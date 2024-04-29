package mario;

import java.awt.Point;

import base.DefaultMove;
import base.Move;
import base.Strategy;

public class MarioDeadStrategie implements Strategy {
	public static final int DEAD_ANIMATION_TIME = 12;
	
    private Move _currentMove = new DefaultMove(new Point(0,-1),10);
    private int _animationTimer = DEAD_ANIMATION_TIME;
    
    public Move getMove() {
    	--_animationTimer;
    	return _currentMove;
    }
}
