package base;

import java.awt.*;

public interface Moveable extends ShapedObject {
    public Point getPos();
    public Move  getMove();
    public void  setMove(Move m);
    /*
     * Translate the position of the moveable according to
     * its current move.
     */
    public void  animate();
}
