package base;

import java.awt.*;


public class Background extends ImageDrawable {

    public Background(String filename, Canvas canvas) {
    	super(filename, canvas, 0);
    }

    public void draw(Graphics g) {
	g.drawImage(_image, 0, 0,
		    _canvas.getWidth(), _canvas.getHeight(),
		    _canvas);
    }
}
