package main;

import javafx.scene.canvas.GraphicsContext;
public abstract class GameObject {
    protected int x, y, width, height;
    
    public GameObject(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public abstract void update();
    public abstract void draw(GraphicsContext gc);

    public int getX() { return x; }
    public int getY() { return y; }
}
