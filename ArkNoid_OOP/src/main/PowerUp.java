package main;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
public class PowerUp extends GameObject implements Renderable {
    public String kind;

    public PowerUp(int x, int y, int width, int height, String kind) {
        super(x, y, width, height);
        this.kind = kind;
    }

    @Override
    public void update() {
        // simple drop behavior
        y += 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(x, y, width, height);
    }
}
