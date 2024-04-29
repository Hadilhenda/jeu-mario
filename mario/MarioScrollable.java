package mario;

import java.util.Observable;
import java.util.Observer;

public interface MarioScrollable extends Observer {
	public void update(Observable obs, Object arg1);
	public void setObservable(Observable obs);
}
