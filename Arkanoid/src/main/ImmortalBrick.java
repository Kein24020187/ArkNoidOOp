package main;
import static main.GameManager.windowWith;
import java.awt.Color;
import java.awt.Graphics;
public class ImmortalBrick extends Brick {
    public ImmortalBrick(int x, int y) {
        super(x, y, windowWith/13, windowWith/25, 10000);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        // static
    }
}
