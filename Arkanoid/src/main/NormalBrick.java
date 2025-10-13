package main;

import java.awt.Color;
import java.awt.Graphics;

public class NormalBrick extends Brick {
    public NormalBrick(int x, int y) {
        super(x, y, 60, 20, 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() { }
}
