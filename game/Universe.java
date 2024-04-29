package game;

public interface Universe {
    public void addGameEntity(GameEntity gameEntity);
    public void removeGameEntity(GameEntity gameEntity);
    /*
     * Move all the element of the universe
     */
    public void animate();
    /*
     * Treat all collision in the universe
     */
    public void colision();
}
