package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class DefaultGame implements Game, Observer {
	
	public static final int SCREEN_WIDTH = 624;
	public static final int SCREEN_HEIGHT = 525;
	
	private static DefaultGame _instance;
	
    private Frame f;
    private DefaultCanvas defaultCanvas = null;
    private DefaultGameLevel gl; 
    protected boolean stopGame;
    protected Count score;
    protected Count life;
    protected Label lifeText;
    protected Label score_text;
    protected Label information;
    protected Label informationValue;
    protected Label lifeValue;
    protected Label scoreValue;
    protected Label currentLevel;
    protected Label currentLevelValue;
    protected ArrayList gameLevels;
    protected Iterator itLevel;
    protected int levelNumber;

    public static DefaultGame getInstance() {
    	if (_instance == null) {
    		_instance = new DefaultGame();
    	}
    	return _instance;
    }
    
    private DefaultGame() {
		score = ScoreCount.instance();
		life = LifeCount.instance();
		stopGame = false;
		lifeText = new Label("Vies:");
		score_text = new Label("Pièces :");
		information = new Label("Etat:");
		informationValue = new Label("jouee");
		currentLevel = new Label("Niveau:");
		((ScoreCount)score).setCount(0);
		((LifeCount)life).setCount(3);
		((ScoreCount)score).addObserver(this);
		((LifeCount)life).addObserver(this);
		create();
		
    }  
    
    public DefaultGameLevel getCurrentLevel() {
    	return (DefaultGameLevel) gameLevels.get(levelNumber);
    }
    
    public void restart() {
		try {
			gl = (DefaultGameLevel) gameLevels.get(levelNumber - 1);
			gl.init();
			gl.start();
			gl.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void start() {
	try {
	    while(!stopGame) {
			gl = (DefaultGameLevel) gameLevels.get(levelNumber - 1);
			currentLevelValue.setText(Integer.toString(levelNumber));
			gl.init();
			gl.start();
			gl.join();
	    }
	}
	catch(InterruptedException e) {}
		f.dispose();
    }
    
    @SuppressWarnings("deprecation")
	public void create() {
	Container c = new Container();
	GridBagLayout gbl = new GridBagLayout();
	lifeValue = new Label(Integer.toString(life.getCount()));
	scoreValue = new Label(Integer.toString(score.getCount()));
	currentLevelValue = new Label("");
	defaultCanvas = new DefaultCanvas();
	Label separation = new Label("   ");
	
	MenuBar menuBar = new MenuBar();
	Menu file = new Menu("fichier");
	MenuItem save = new MenuItem("sauvegarder");
	MenuItem restore = new MenuItem("restaurer");
	MenuItem quit = new MenuItem("quitter");
	Menu game = new Menu("jeu");
	MenuItem suspend = new MenuItem("suspendre");
	MenuItem resume = new MenuItem("reprendre");
	
	if (f != null)
	    f.hide();
	f = new Frame("Default Game");
	
	defaultCanvas.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
	c.setLayout(gbl);
	c.add(lifeText);
	c.add(lifeValue);
	c.add(separation);
	c.add(score_text);
	c.add(scoreValue);
	c.add(separation);
	c.add(currentLevel);
	c.add(currentLevelValue);
	c.add(separation);
	c.add(information);
	c.add(informationValue);
	
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    save();
		}
	    });
	restore.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    restore();
		}
	    });
	quit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    System.exit(0);
		}
	    });
	suspend.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    suspend();
		}
	    });
	resume.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    resume();
		}
	    });
	
	file.add(save);
	file.add(restore);
	file.add(quit);
	game.add(suspend);
	game.add(resume);
	menuBar.add(file);
	menuBar.add(game);
	
	f.setMenuBar(menuBar);
	f.add(defaultCanvas);
	f.add(c, BorderLayout.NORTH);
	f.pack();
	f.show();
	defaultCanvas.setFocusable(true);
    }
    
    public void update(Observable o, Object arg) {
	if (o instanceof ScoreCount) {
	    ScoreCount tmp_score = (ScoreCount)o;
	    scoreValue.setText(Integer.toString(tmp_score.getCount()));
	}
	else if (o instanceof LifeCount) {
	    LifeCount tmp_life = (LifeCount)o;
	    int lifes = tmp_life.getCount();
	    lifeValue.setText(Integer.toString(lifes));
	    if (lifes == 0) {
		informationValue.setText("Perdu !!!  :-(");
		System.out.println("PERDU !");
		gl.end();
		stopGame = true;
	    }
	}
    }
    
    public void restore(){
    }
    
    public void save(){
    }
    
    @SuppressWarnings("deprecation")
	public void suspend() {
	gl.suspend();
    }
    
    @SuppressWarnings("deprecation")
	public void resume(){
	gl.resume();
    }

    public void setLevels(ArrayList levels)
    {
		gameLevels = levels;
		itLevel = levels.iterator();
		levelNumber = 0;
    }

    public ArrayList getLevels() {
    	return gameLevels;
    }
    
    public void setCurrentLevel(DefaultGameLevel level) {
    	levelNumber = gameLevels.indexOf(level);
    }
    
    public void setCurrentLevel(int levelIndex) {
    	levelNumber = levelIndex;
    }
    
    public Canvas getCanvas()
    {
	return defaultCanvas;
    }
}





