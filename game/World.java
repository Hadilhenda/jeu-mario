package game;
import base.Obstacle;

public interface World
{
    public abstract void addObstacle(Obstacle obs);
    public abstract void removeObstacle(Obstacle obs);
}
