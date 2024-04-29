package game;

import java.util.*;


import base.Moveable;
public class DefaultObstacleRules implements ObstacleRules
{
    public void moveValidationProcessing(Vector obs, Moveable m) throws IllegalMoveException
	{
    	throw new IllegalMoveException();
	}
}
