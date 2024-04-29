package base;

import java.awt.Point;

public class StraightLineStrategy implements Strategy {

    Point _goal, _pos;
    
    public StraightLineStrategy(Point pos, Point goal) {
	_goal = goal;
	_pos = pos;

    }
    public Move getMove() {
	double dist = _pos.distance(_goal);
	Move move = new DefaultMove(
				    new Point( 
					      (int)Math.rint((_goal.getX()-_pos.getX())/dist),
					      (int)Math.rint((_goal.getY()-_pos.getY())/dist) ), 
					      8 );
	return move;
    }
}
