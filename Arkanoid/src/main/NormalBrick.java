package main;

import java.awt.Color;
import java.awt.Graphics;

import static main.GameManager.windowWith;

public class NormalBrick extends Brick {
    public NormalBrick(int x, int y) {
        super(x, y, windowWith/13, windowWith/25, 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() { }
}
