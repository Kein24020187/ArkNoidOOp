package main;

import java.awt.Color;
import java.awt.Graphics;

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
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
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
