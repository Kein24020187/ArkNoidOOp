package main;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import static main.GameManager.windowWith;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y) {
        super(x, y, windowWith/13, windowWith/25, 3);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(health == 3){
        gc.setFill(Color.DARKGRAY);} else if (health == 2) {
            gc.setFill(Color.BLUE);
        }
        else{
            gc.setFill(Color.ORANGE);
        }
        gc.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        // static
    }
}
