package base;

import java.awt.*;
import java.util.*;

public class DrawableComposite implements Drawable {
    protected Vector<Drawable> _elements = new Vector<Drawable>();
    public void add(Drawable e) {
	_elements.addElement(e);
    }
    public void remove(Drawable e) {
    	_elements.removeElement(e);
    }
    public void draw(Graphics g) {
	Iterator it = _elements.iterator();
	for(;it.hasNext();) {
		try {
			((Drawable) (it.next())).draw(g);
		}
		catch (Exception e) {
			it = _elements.iterator();
		}
	}
    }
}
