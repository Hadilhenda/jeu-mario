package mario;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import base.Drawable;
import base.ImageDrawable;
import base.Obstacle;

public class Wall extends MarioScroll implements Drawable, Obstacle {

	private final String WALL_IMAGE_OUT = "/images/obstacle.gif";
	private final String WALL_IMAGE_IN = "/images/obstacle_vert.gif";
	private final int WALL_SIZE = 16;
	private final int WALL_DRAW_SIZE = 24;
	private final int WALL_SPRITE_WALL_POSITION = 8;
	private final int WALL_SPRITE_FLOOR_POSITION = 0;
	private final int WALL_SPRITE_PIPE_BASE_POSITION = 9;
	private final int WALL_SPRITE_PIPE_HEAD_POSITION = 11;
	
	protected static ImageDrawable _image = null;
	private Point _position;
	private WallsTypes _isFloor;
	private boolean _open;
	private static boolean _inside;
	
	public Wall(Canvas defaultCanvas, int x, int y, WallsTypes isFloor, boolean in) {
		if (_image == null || _inside != in) {
			if (in) {
				_image = new ImageDrawable(WALL_IMAGE_IN, defaultCanvas, 2);
			} else {
				_image = new ImageDrawable(WALL_IMAGE_OUT, defaultCanvas, 2);
			}
			_inside = in;
		}
		_position = new Point(x, y);
		_isFloor = isFloor;
	}
	
	public void setOpen(boolean open) {
		_open = open;
	}
	
	public boolean isOpen() {
		return _open;
	}
	
	public void draw(Graphics g) {
		int spritePosition = WALL_SPRITE_FLOOR_POSITION;
		int spriteWidth = WALL_DRAW_SIZE;
		int spriteImageWidth = WALL_SIZE;
		switch (_isFloor) {
		case Wall:
			spritePosition = WALL_SPRITE_WALL_POSITION;
			break;
		case PipeBase:
			spritePosition = WALL_SPRITE_PIPE_BASE_POSITION;
			spriteWidth = WALL_DRAW_SIZE * 2;
			spriteImageWidth = WALL_SIZE * 2;
			break;
		case PipeHead:
			spritePosition = WALL_SPRITE_PIPE_HEAD_POSITION;
			spriteWidth = WALL_DRAW_SIZE * 2;
			spriteImageWidth = WALL_SIZE * 2;
			break;
		}
		g.drawImage(_image.getImage(), _position.x - _posEcran, _position.y, _position.x + spriteWidth - _posEcran, _position.y + WALL_DRAW_SIZE, WALL_SIZE * spritePosition, 0, WALL_SIZE * spritePosition + spriteImageWidth, WALL_SIZE, null);

	}

	public Point getPos() {
		return _position;
	}

	public Rectangle getBoundingBox() {
		int spriteWidth = WALL_DRAW_SIZE;
		if (_isFloor == WallsTypes.PipeBase || _isFloor == WallsTypes.PipeHead) {
			spriteWidth = WALL_DRAW_SIZE * 2;
		}
		return (new Rectangle(_position.x, _position.y, spriteWidth, spriteWidth - 1));
	}

}
