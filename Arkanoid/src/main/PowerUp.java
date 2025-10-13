package main;

import java.awt.Color;
import java.awt.Graphics;

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
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, width, height);
    }
}
