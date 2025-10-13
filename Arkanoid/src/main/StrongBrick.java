package main;

import java.awt.Color;
import java.awt.Graphics;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y) {
        super(x, y, 60, 20, 3);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        // static
    }
}
