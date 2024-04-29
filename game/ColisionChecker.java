package game;

import base.Colideable;

public interface ColisionChecker
{
    public abstract void addColideable(Colideable p);
    public abstract void removeColideable(Colideable p);
    public abstract void setColisionRules(ColisionRules colisionRules);
    public abstract void computeAllColisions();
}
