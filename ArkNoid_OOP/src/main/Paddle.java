package main;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
public class Paddle extends GameObject implements Renderable {
    int windowWith;
    public Paddle(int x, int y, int width, int height,int windowWith) {
        super(x, y, width, height);
        this.windowWith = windowWith;
    }

    @Override
    public void update() {
        // Controlled by keyboard input (handled in GameWindow)
    }

    @Override
    public void draw(GraphicsContext gc) {
    gc.setFill(Color.color(0.2, 0.5, 0.8)); // giống new Color(float, float, float)
    gc.fillRoundRect(x, y, width, height, 10, 10);
    }
    public void move(int dx) {
        if(dx>0&&x+width<windowWith) x += dx;
        else if(dx<0&&x>0) x+=dx;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
