package base;

import java.awt.Point;


public class DefaultStrategy implements Strategy
{
    public Move getMove()
    {
	return (new DefaultMove(new Point(0,0),0));
    }
}

