package mario;

import java.awt.*;

import base.DefaultMove;
import base.Move;
import base.Strategy;

public class MarioLinearStrategie implements Strategy
{
    private Move _currentMove = new DefaultMove(new Point(-1, 3),2);
    
    public Move getMove() {
    	Move current = _currentMove;
    	_currentMove = new DefaultMove(new Point(current.getDir().x,1),2);
    	return current;
    }
    
    public void setMove(Move move) {
    	_currentMove = move;
    }
}
