package game;

import java.awt.*;

import base.Drawable;
import base.Background;
import base.DrawableComposite;

public class DefaultDrawingGameBoard implements DrawingGameBoard {
	private Image _buffer;
	private Graphics _bufferGraphics;
	protected Canvas _canvas;
	protected Background background;
	protected DrawableComposite foreground = new DrawableComposite();

	public DefaultDrawingGameBoard(Canvas canvas) {
		this._canvas = canvas;
		_buffer = _canvas.createImage(_canvas.getWidth(), _canvas.getHeight());
		_bufferGraphics = _buffer.getGraphics();
		background = new Background("/images/fond.jpg", _canvas);
	}

	public void setBackground(String filename) {
		background = new Background(filename, _canvas);
	}

	public void addDrawableEntity(Drawable e) {
		foreground.add(e);
	}

	public void removeDrawableEntity(Drawable e) {
		foreground.remove(e);
	}

	public void paint(Graphics g) {
		background.draw(_bufferGraphics);
		foreground.draw(_bufferGraphics);
		g.drawImage(_buffer, 0, 0, _canvas.getWidth(), _canvas.getHeight(),
				_canvas);
	}
}
