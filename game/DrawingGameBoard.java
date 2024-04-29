package game;

import java.awt.*;

import base.Drawable;

public interface DrawingGameBoard {
    public void addDrawableEntity(Drawable e);
    public void removeDrawableEntity(Drawable e);
    public void paint(Graphics g);
}

