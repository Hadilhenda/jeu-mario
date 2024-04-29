package base;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

public class ImageDrawable implements Drawable {
	protected Image _image;
	protected Canvas _canvas;
	
	public ImageDrawable(String filename, Canvas canvas, int id) {
		_canvas = canvas;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		_image = toolkit.createImage(new JarRessource().getFile(filename));
		MediaTracker tracker = new MediaTracker(canvas);
		tracker.addImage(_image, id);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}
	}

	public Image getImage() {
		return _image;
	}

	public void draw(Graphics g) {
		g.drawImage(_image, 0, 0, _canvas);
	}
}
