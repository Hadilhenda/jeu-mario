package mario;

import java.awt.*;
import java.awt.event.*;

import base.DefaultMove;
import base.Move;
import base.Strategy;


public class MarioKeyboardStrategie extends KeyAdapter implements Strategy
{
	private final int KEY_LEFT = 0;
	private final int KEY_RIGHT = 1;
	private final int KEY_UP = 2;
	private final int KEY_DOWN = 3;
	private final int JUMP_MAX = 13;
	
    private Move _currentMove = new DefaultMove(new Point(0,0),8);
    private boolean _lock = false;
    private boolean _keysPressed[] = {false, false, false, false};
    private int _jumpHeight = 0;
    private boolean _jumpLock = false; 
    private boolean _stopJumping = false;
    
    public void setStopJumping(boolean stopJumping) {
    	if (!stopJumping) {
    		_jumpHeight = 0;
    		_jumpLock = false;
    	}
    	_stopJumping = stopJumping;
    }
    
    public Move getMove()
    {
    	computeDirection();
    	return _currentMove;
    }
    
    public void keyReleased(KeyEvent event) {
    	if (!_lock) {
			computeKeysPressed(event.getKeyCode(), false);
	    }
	}
	
    public void keyPressed(KeyEvent event) {
		if (!_lock) {
			computeKeysPressed(event.getKeyCode(), true);
	    }
	}
	
    private void computeKeysPressed(int keycode, boolean pressed) {
		switch (keycode) 
	    {
		case KeyEvent.VK_RIGHT:
			_keysPressed[KEY_RIGHT] = pressed;
			break;
		case KeyEvent.VK_LEFT:
			_keysPressed[KEY_LEFT] = pressed;
			break;
		case KeyEvent.VK_UP:
			_keysPressed[KEY_UP] = pressed;
			break;
		case KeyEvent.VK_DOWN:
			_keysPressed[KEY_DOWN] = pressed;
			break;
	    }
	}
	
	private void computeDirection() {
		int x = 0;
		int y = 0;
		if (_keysPressed[KEY_LEFT]) {
			--x;
		}
		if (_keysPressed[KEY_RIGHT]) {
			++x;
		}
		if (_keysPressed[KEY_DOWN]) {
			++y;
		}
		if (_keysPressed[KEY_UP]) {
			if (_currentMove.getDir().y != 1) {
				if (!_jumpLock && !_stopJumping) {
					++_jumpHeight;
					y -= 2;
				}
			}
		}
		if (_jumpHeight > JUMP_MAX) {
			_keysPressed[KEY_UP] = false;
			_jumpLock = true;
			_stopJumping = true;
		}
		else if (_jumpHeight < 0) {
			_jumpLock = false;
			_lock = false;
		}
		if (_jumpLock) {
			--_jumpHeight;
		}
		_currentMove.setDir(new Point(x, y + 1));
	}
    
	public void lock()
    {
    	_lock = true;
    }
    
	public void unlock()
    {
		_lock = false;
    }

}

