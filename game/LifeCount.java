package game;


public class LifeCount extends DefaultCount {
    private static LifeCount lifeCount = new LifeCount();
    
    private LifeCount() {}
    
    public static LifeCount instance () {
	return lifeCount;
    }
} 


