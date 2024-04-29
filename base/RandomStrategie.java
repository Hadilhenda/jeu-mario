package base;

import java.util.Random;
import java.awt.Point;

public class RandomStrategie implements Strategy {
    Move _currentMove=new DefaultMove(new Point(0,0),8);
    static Random random = new Random();
    public Move getMove() {
	int i = random.nextInt(5);

	switch (i) {
	case 0:
	    _currentMove.setDir(new Point(1,0));
	    break;
	case 1:
	    _currentMove.setDir(new Point(-1,0));
	    break;
	case 2:
	    _currentMove.setDir(new Point(0,-1));
	    break;
	case 3:
	    _currentMove.setDir(new Point(0,1));
	    break;
	}
	return _currentMove;
    }
}
