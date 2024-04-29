package game;

import java.util.*;

public interface ColisionRules
{
    public abstract void setUniverse(Universe universe);
    public abstract void colisionProcessing(Vector colideables);
}
