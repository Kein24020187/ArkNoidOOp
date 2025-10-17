package main;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Brick extends GameObject implements Renderable {
    protected int health;
    protected String type;
    public Brick(int x, int y, int width, int height, int health) {
        super(x, y, width, height);
        this.health = health;
    }

    public Brick(int x, int y, int width, int height,String type){
        super(x, y, width, height);
        this.type = type;
    }
    public void hit() { health--; }
    public boolean isDestroyed() { 
        if(this.type == "immortal") return true;
        return health <= 0; 
    
    }
}
