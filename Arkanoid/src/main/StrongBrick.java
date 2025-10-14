package main;

import java.awt.Color;
import java.awt.Graphics;

import static main.GameManager.windowWith;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y) {
        super(x, y, windowWith/13, windowWith/25, 3);
    }

    @Override
    public void draw(Graphics g) {
        if(health == 3){
        g.setColor(Color.DARK_GRAY);} else if (health == 2) {
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.ORANGE);
        }
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        // static
    }
}
