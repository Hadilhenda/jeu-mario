package mario;

import game.DefaultGame;
import game.DefaultGameLevel;

import java.util.ArrayList;

public class Main
{
    public static void main (String [] args)
    {
		ArrayList<DefaultGameLevel> levels = new ArrayList<DefaultGameLevel>();
		DefaultGame g = DefaultGame.getInstance();
		levels.add(new MarioLevel1(g.getCanvas()));
		levels.add(new MarioLevel2(g.getCanvas()));
		g.setLevels(levels);
		g.setCurrentLevel(1);
		g.start();
    }
}