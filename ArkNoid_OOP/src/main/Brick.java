package main;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
public abstract class Brick extends GameObject implements Renderable {
    protected int health;

    public Brick(int x, int y, int width, int height, int health) {
        super(x, y, width, height);
        this.health = health;
    }

    public void hit() { health--; }
    public boolean isDestroyed() { return health <= 0; }
}
