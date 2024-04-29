package game;


public interface GameRules
{
    public abstract void setObstacleRules(ObstacleRules obstacleRules );
    public abstract void setColisionRules(ColisionRules colisionRules);
}
