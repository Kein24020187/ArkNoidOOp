package main;

import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y, width, height;
    
    public GameObject(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public int getX() { return x; }
    public int getY() { return y; }
}
