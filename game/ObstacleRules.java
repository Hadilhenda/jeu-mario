package game;


import java.util.Vector;

import mario.OpenPipeException;
import mario.WalkFloorException;
import base.Moveable;

public interface ObstacleRules
{
    public abstract void moveValidationProcessing(Vector obs, Moveable m) throws IllegalMoveException, WalkFloorException, OpenPipeException ;
}


