package game;

import java.awt.*;
import game.World;

public abstract class DefaultGameLevel extends Thread implements GameLevel {
    protected Canvas _canvas;
    protected World _world;
    protected Universe _universe;
    protected DrawingGameBoard _drawingGameBoard;
    
    boolean stopGameLoop;

    protected abstract void init();

    public DefaultGameLevel(Canvas canvas) {
    	_canvas = canvas;
    }

    public void start() {
    	
	super.start();
	try {
	    super.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
    
    public void run() {
	stopGameLoop=false;
	while(!stopGameLoop) {
		drawing();
	    _universe.animate();
	    _universe.colision();
	    
	    evolution();
	    
	    try{
		Thread.sleep(40);
	    }
	    catch(Exception e){}
	}
    }

    public void end()
    {
	stopGameLoop = true;
    }
    
    protected void evolution(){}
    protected void collision(){}
    protected void colision_handler(){}
    protected void drawing() {
	_canvas.repaint();
    }
}




