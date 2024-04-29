package mario;

import java.util.Observable;

public abstract class MarioScroll implements MarioScrollable {

	private Observable _observable;
	protected int _posEcran;
	
	public void setObservable(Observable obs) {
		_observable = obs;
		_observable.addObserver(this);
	}

	public void update(Observable obs, Object arg1) {
		if(obs instanceof Screen){
			Screen scr = (Screen) obs;
			_posEcran = scr.getPosEcran();
		}
	}

}
