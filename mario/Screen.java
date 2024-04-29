package mario;
import java.util.*;
import base.*;

public class Screen extends Observable {
	private final int SCREEN_HALF_WIDTH = 300;
	
	private static int _posEcran;
	private static Mario _mario;
	
	private static Screen _instance;
	
	public static Screen createInstance(Mario m) {
		_mario = m;
		_posEcran = 0;
		_instance = new Screen();
		return _instance;
	}
	
	public static Screen getInstance() {
		if (_instance == null) {
			_instance = new Screen();
		}
		return _instance;
	}
	
	public void actualiser(Move m){
		if(_mario.getPos().x > _posEcran + SCREEN_HALF_WIDTH) {
			_posEcran +=(int)_mario.getMove().getDir().getX() * _mario.getMove().getSpeed(); 
			setChanged();
			notifyObservers();
		}
		
	}
	
	public int getPosEcran(){
		return _posEcran;
	}
}
