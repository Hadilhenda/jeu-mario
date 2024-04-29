package game;

import mario.OpenPipeException;
import mario.WalkFloorException;
import base.Move;
import base.Moveable;
import base.Obstacle;


public interface ObstacleChecker
{
    public abstract void addObstacle(Obstacle p);
    public abstract void removeObstacle(Obstacle p);
    public abstract void setObstacleRules(ObstacleRules obstacleRules);

    public abstract void moveValidation(Moveable m, Move mov)throws IllegalMoveException, WalkFloorException, OpenPipeException ;
}
