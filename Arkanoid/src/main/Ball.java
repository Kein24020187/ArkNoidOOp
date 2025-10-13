package main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject implements Renderable {
    private int dx = 3, dy = -3;
    int windowWith,windowHeight;
    public Ball(int x, int y, int size,int windowWith,int windowHeight) {
        super(x, y, size, size);
        this.windowWith = windowWith;
        this.windowHeight = windowHeight;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        if(y+height <= 0) bounceY(); //hit top then reverse y
        else if(x+width >= windowWith&&y+height<windowHeight||x<=0&&y+height<windowHeight) {bounceX();} //hit right of left then reverse x
        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    public void bounceX() { dx = -dx; }
    public void bounceY() { dy = -dy; }
}
