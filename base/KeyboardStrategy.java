package base;

import java.awt.*;
import java.awt.event.*;


public class KeyboardStrategy extends KeyAdapter implements Strategy
{
    Move _currentMove=new DefaultMove(new Point(0,0),8);
    boolean lock=false;
    public Move getMove()
    {
	return (_currentMove);
    }
    public void keyPressed(KeyEvent event)
    {              
	if (!lock)
	    {
		int keycode = event.getKeyCode();
		switch (keycode) 
		    {
		    case KeyEvent.VK_RIGHT:
			_currentMove.setDir(new Point(1,0));
			break;
		    case KeyEvent.VK_LEFT:
			_currentMove.setDir(new Point(-1,0));
			break;
		    case KeyEvent.VK_UP:
			_currentMove.setDir(new Point(0,-1));
			break;
		    case KeyEvent.VK_DOWN:
			_currentMove.setDir(new Point(0,1));
			break;
		    }
	    }
    }
    public void lock()
    {
	lock=true;
    }
    public void unlock()
    {
	lock=false;
    }

}

