package game;


public class ScoreCount extends DefaultCount {
    private static ScoreCount score = new ScoreCount();
    
    private ScoreCount() {}
    
    public static ScoreCount instance() {
	return score;
    } 
}
