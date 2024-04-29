package game;

import java.util.Observable;

public class DefaultCount extends Observable implements Count {
    protected int count;
    
    public int getCount() {
	return count;
    }
    
    public void setCount (int c) {
	count = c;
	setChanged();
	notifyObservers();
    }
}
