package mario;

import game.DefaultDrawingGameBoard;

import java.awt.*;

import base.Background;


public class MarioDrawingGameBoard extends DefaultDrawingGameBoard {
	
	private final String MARIO_BACKGROUND_IMAGE = "/images/fond.jpg";
	private final String MARIO_BACKGROUND_IMAGE_INSIDE = "/images/fond_inside.jpg";

    public MarioDrawingGameBoard(Canvas canvas, boolean inside) { 
    	super(canvas);
    	if (inside) {
    		background = new Background(MARIO_BACKGROUND_IMAGE_INSIDE, _canvas);
    	} else {
    		background = new Background(MARIO_BACKGROUND_IMAGE, _canvas);
    	}
    } 
}








