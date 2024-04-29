package base;

import java.awt.*;

public interface Move extends Cloneable
{
    public Point getDir();
    public int getSpeed();
    public void setDir(Point p);
    public void setSpeed(int i);
    public Object clone();
    
    public final int MOVE_UP = -1;
    public final int MOVE_DOWN = 1;
    public final int MOVE_LEFT = -1;
    public final int MOVE_RIGHT = 1;
}
