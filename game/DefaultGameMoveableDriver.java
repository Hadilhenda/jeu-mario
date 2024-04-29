package game;

import java.awt.*;

import mario.OpenPipeException;
import mario.WalkFloorException;

import base.DefaultStrategy;
import base.KeyboardStrategy;
import base.Move;
import base.Moveable;
import base.Strategy;

//********************************************************************************************
//MODIFY : NICO DEBUT
//********************************************************************************************

public class DefaultGameMoveableDriver implements GameMoveableDriver
{
    protected ObstacleChecker _obstacleChecker;
    protected Strategy _strategie;
    public DefaultGameMoveableDriver()
    {
	_obstacleChecker=new DefaultObstacleChecker();
	_strategie=new DefaultStrategy();
    }
    public void setStrategie(Strategy strat)
    {
	_strategie=strat;
    }
    public void setObstacleChecker(ObstacleChecker obst)
    {
	_obstacleChecker=obst;
    }
    public Move getMove(Moveable m)  {
	Move tmpMove=_strategie.getMove();
	if (_strategie instanceof KeyboardStrategy)
	    ((KeyboardStrategy)_strategie).lock();
	
	try {
	    _obstacleChecker.moveValidation(m, tmpMove);
	    tmpMove = m.getMove();
	}
	catch(IllegalMoveException evte) {
	    tmpMove = m.getMove();
		tmpMove.setDir(new Point(0,0));
	} catch (WalkFloorException e) {
		e.printStackTrace();
	} catch (OpenPipeException e) {
		e.printStackTrace();
	}
	if (_strategie instanceof KeyboardStrategy) {
	    ((KeyboardStrategy)_strategie).unlock();
	}
	return tmpMove;
    }
}

//********************************************************************************************
//MODIFY : NICO FIN
//********************************************************************************************




