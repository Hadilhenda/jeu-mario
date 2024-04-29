package mario;

import game.DefaultGame;
import game.DefaultGameMoveableDriver;
import game.IllegalMoveException;

import java.awt.Point;

import base.Move;
import base.Moveable;
import base.Strategy;

public class MarioGameMoveableDriver extends DefaultGameMoveableDriver
{   
    public Move getMove(Moveable m)  {
		Move tmpMove = _strategie.getMove();
		if (_strategie instanceof MarioKeyboardStrategie)
		    ((MarioKeyboardStrategie)_strategie).lock();
		
		try {
		    _obstacleChecker.moveValidation(m,tmpMove);
		}
		catch(IllegalMoveException evte) {
		    tmpMove = m.getMove();
		    if (_strategie instanceof MarioKeyboardStrategie) {
		    	tmpMove.setDir(new Point(0, 1));
		    }
		    try {
		    	_obstacleChecker.moveValidation(m,tmpMove);
		    }
		    catch(IllegalMoveException evt) {
		    	if (_strategie instanceof MarioLinearStrategie) {
		    		tmpMove.setDir(new Point(tmpMove.getDir().x * -1 ,0));
		    		((MarioLinearStrategie) _strategie).setMove(tmpMove);
		    	}
		    	else {
		    		tmpMove.setDir(new Point(0,0));
		    	}
		    } catch (WalkFloorException e) {
		    	tmpMove.setDir(new Point(tmpMove.getDir().x,0));
			} catch (OpenPipeException e) {
				if (tmpMove.getDir().getY() > 1) {
					tmpMove.setDir(new Point(tmpMove.getDir().x, 0));
				} else {
					tmpMove.setDir(new Point(tmpMove.getDir().x, 0));
				}
			}
	    } catch (WalkFloorException e) {
	    	tmpMove.setDir(new Point(tmpMove.getDir().x, 0));
		} catch (OpenPipeException e) {
			if (tmpMove.getDir().getY() > 1) {
				tmpMove.setDir(new Point(tmpMove.getDir().x, 0));
				DefaultGame game = DefaultGame.getInstance();
				game.setCurrentLevel(2);
				game.restart();
			} else {
				tmpMove.setDir(new Point(tmpMove.getDir().x, 0));
			}
		}
	    
		if (_strategie instanceof MarioKeyboardStrategie) {
		    ((MarioKeyboardStrategie)_strategie).unlock();
		}
	
		return tmpMove;
    }

    public Strategy getStrategy() {
    	return _strategie;
    }

    public void setStrategy(Strategy strategy) {
    	_strategie = strategy;
    }
}





