package base;

import java.awt.*;

public class DefaultMove implements Move
{
    Point _direction;
    int _speed;
    public DefaultMove(Point direction, int speed)
    {
	_direction=direction;
	_speed=speed;
    }
    public Point getDir()
    {
	return _direction;
    }
    public int getSpeed()
    {
	return _speed;
    }
    public void setDir(Point direction)
    {
	_direction=direction;
    }
    public void setSpeed(int speed)
    {
	_speed=speed;
    }
    public Object clone()
    {
	return new DefaultMove(_direction, _speed);
    }
}
