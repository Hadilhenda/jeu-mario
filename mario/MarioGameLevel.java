package mario;

import game.ColisionChecker;
import game.ColisionRules;
import game.DefaultCanvas;
import game.DefaultGameLevel;
import game.GameEntity;
import game.LifeCount;
import game.ObstacleChecker;
import game.ScoreCount;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import base.Obstacle;

public class MarioGameLevel extends DefaultGameLevel implements Observer {

	private final Point MARIO_START_POSITION = new Point(32, 232);
	
	private final int MAP_SIZE_HEIGHT = 22;
	private final int MAP_RESOLUTION = 24;
	
	private final int MAP_ELEMENT_WALL = 1;
	private final int MAP_ELEMENT_FLOOR = 2;
	private final int MAP_ELEMENT_COIN = 3;
	private final int MAP_ELEMENT_BRICK = 4;
	private final int MAP_ELEMENT_MUSHROOM = 5;
	private final int MAP_ELEMENT_BONUS_BRICK_SUPER = 6;
	private final int MAP_ELEMENT_BONUS_BRICK_INVINCIBLE = 7;
	private final int MAP_ELEMENT_PIPE_BASE = 8;
	private final int MAP_ELEMENT_PIPE_HEAD = 9;
	private final int MAP_ELEMENT_GOOMBASS = 10;
	private final int MAP_ELEMENT_BONUS_BRICK_COIN = 11;
	private final int MAP_ELEMENT_PIPE_OPEN = 12;
	
	protected int[][] _levelMap;
	protected boolean _inside;
	protected boolean _scrollable;
	protected int _width; 
	
	protected void init() {
		ScoreCount count = ScoreCount.instance();
		count.addObserver(this);
		_drawingGameBoard = new MarioDrawingGameBoard(_canvas, _inside);
		
		ColisionChecker colisionChecker = new MarioColisionChecker();
		ObstacleChecker obstacleChecker = new MarioObstacleChecker();
		ColisionRules colisionRules = new MarioColisionRules();

		obstacleChecker.setObstacleRules(new MarioObstacleRules());

		colisionChecker.setColisionRules(colisionRules);
		((DefaultCanvas)_canvas).setDrawingGameBoard(_drawingGameBoard);
		_world = MarioWorld.createInstance(_drawingGameBoard, obstacleChecker);
		
		_universe = MarioUniverse.createInstance(_world, _drawingGameBoard, colisionChecker);

		colisionRules.setUniverse(_universe);

		int[][] tab = _levelMap;
		
		Mario monMario = new Mario(_canvas);
		Screen scr = Screen.createInstance(monMario);
		if (_scrollable) {
			monMario.setScreen(scr);
		} else {
			monMario.setScreen(null);
		}
				
		Object o = null;
		for (int i=0; i < _width; i++) {
			for (int j=0; j < MAP_SIZE_HEIGHT; j++) {
				boolean entity = false;
				boolean obstacle = false;
				if (tab[i][j] == MAP_ELEMENT_WALL) {
					o = new Wall(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, WallsTypes.Wall, _inside);
					obstacle = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_COIN) {
					o = new GoldCoin(_canvas,new Point(i * MAP_RESOLUTION, j * MAP_RESOLUTION));
					entity = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_FLOOR) {
					o = new Wall(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, WallsTypes.Floor, _inside);
					obstacle = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_PIPE_BASE) {
					o = new Wall(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, WallsTypes.PipeBase, _inside);
					obstacle = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_PIPE_HEAD) {
					o = new Wall(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, WallsTypes.PipeHead, _inside);
					obstacle = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_PIPE_OPEN) {
					o = new Wall(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, WallsTypes.PipeHead, _inside);
					((Wall) o).setOpen(true);
					obstacle = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_BRICK) {
					o = new Brick(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, _inside);
					obstacle = true;
					entity = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_MUSHROOM) {
					o = new MushroomBonus(_canvas, new Point(i * MAP_RESOLUTION, j * MAP_RESOLUTION));
					obstacle = true;
					entity = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_BONUS_BRICK_SUPER) {
					o = new BonusBrick(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, BonusBrick.BONUS_BRICK_SUPER, _inside);
					entity = true;
					obstacle = true;
				} 
				else if (tab[i][j] == MAP_ELEMENT_BONUS_BRICK_COIN) {
					o = new BonusBrick(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, BonusBrick.BONUS_BRICK_COIN, _inside);
					obstacle = true;
					entity = true;
				} 
				else if (tab[i][j] == MAP_ELEMENT_BONUS_BRICK_INVINCIBLE) {
					o = new BonusBrick(_canvas, i * MAP_RESOLUTION, j * MAP_RESOLUTION, BonusBrick.BONUS_BRICK_INVINCIBLE, _inside);
					obstacle = true;
					entity = true;
				}
				else if (tab[i][j] == MAP_ELEMENT_GOOMBASS) {
					o = new MarioGoombass(_canvas);
					MarioGameMoveableDriver goombassDriver = new MarioGameMoveableDriver();
					MarioLinearStrategie goombassStrategie = new MarioLinearStrategie();
					goombassDriver.setStrategie(goombassStrategie);
					goombassDriver.setObstacleChecker(obstacleChecker);
					((MarioGoombass) o).setPos(new Point(i * MAP_RESOLUTION, j * MAP_RESOLUTION));
					((MarioGoombass) o).setDriver(goombassDriver);
					entity = true;
					((MarioColisionRules)colisionRules).addGoomBass((MarioGoombass) o);
				}
				if (obstacle) {
					_world.addObstacle((Obstacle) o);
				}
				if (entity) {
					_universe.addGameEntity((GameEntity) o);
				} 
				((MarioScrollable) o).setObservable(scr);
			}
		}
		
		MarioGameMoveableDriver pacDriv = new MarioGameMoveableDriver();
		MarioKeyboardStrategie keyStr = new MarioKeyboardStrategie();
		pacDriv.setStrategie(keyStr);
		pacDriv.setObstacleChecker(obstacleChecker);
		_canvas.addKeyListener(keyStr);
		monMario.setDriver(pacDriv);
		monMario.setPos(MARIO_START_POSITION);
		_universe.addGameEntity(monMario);
	}

	public MarioGameLevel(Canvas canvas) {
		super(canvas);
	}
	
	public void update(Observable obs, Object arg1) {
		if (obs instanceof ScoreCount) {
			ScoreCount count = (ScoreCount) obs;
			if (count.getCount() % 100 == 0) {
				LifeCount life = LifeCount.instance();
				life.setCount(life.getCount() + 1);
			}
		}
	}

	public boolean isInside() {
		return _inside;
	}
	
	public void setInside(boolean inside) {
		_inside = inside;
	}
	
	public int[][] getLevelMap() {
		return _levelMap;
	}
	
	public void setLevelMap(int[][] levelMap) {
		_levelMap = levelMap;
	}
	
	public boolean isScrollable() {
		return _scrollable;
	}
	
	public void setScrollable(boolean scrollable) {
		_scrollable = scrollable;
	}

	public int getWidth() {
		return _width;
	}
	
	public void setWidth(int width) {
		_width = width;
	}

}








