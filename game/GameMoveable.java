package game;

import java.awt.*;

import mario.MarioGameMoveableDriver;


import base.DefaultMove;
import base.Move;
import base.Moveable;

public abstract class GameMoveable implements Moveable {
    GameMoveableDriver _moveDriver = new MarioGameMoveableDriver();
    Point _position = new Point(50,50);
    Move _direction = new DefaultMove(new Point(0,0),0);
    
    public void setPos(Point p) {
	_position = (Point) p.clone();
    }
    public Point getPos() {
	return _position;
    }
    public void setMove(Move m)	{
	_direction = (Move) m.clone();
    }
    public Move getMove() {
	return (Move)_direction.clone();
    }
    public void setDriver(GameMoveableDriver drv) {
	_moveDriver = drv;
    }
    public GameMoveableDriver getDriver() {
	return _moveDriver;
    }
    public void animate() {
	Move m = _moveDriver.getMove(this);
	_direction.setDir(m.getDir());
	_direction.setSpeed(m.getSpeed());
	_position.translate((int)_direction.getDir().getX() * _direction.getSpeed(),
			    (int)_direction.getDir().getY() * _direction.getSpeed());
	animateHandler();
    }
    public abstract void animateHandler();
}
