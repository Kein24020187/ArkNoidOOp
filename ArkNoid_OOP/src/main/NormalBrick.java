package main;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
public class NormalBrick extends Brick {
    public NormalBrick(int x, int y) {
        super(x, y, GameManager.windowWith/13, GameManager.windowWith/25, 1);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.ORANGE);
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void update() { }
}
