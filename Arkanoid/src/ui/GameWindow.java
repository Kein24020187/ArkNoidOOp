package ui;

import main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private GameManager game;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameWindow() {
        game = new GameManager();
        timer = new Timer(8, this); // 60 FPS
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        switch(game.getCurrentState()){
            case Playing:
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(20, 10, 40),    // tím đậm ở trên
                        0, getHeight(), new Color(0, 0, 10) // đen ở dưới
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                game.getBall().draw(g);
                game.getPaddle().draw(g);
                for (Brick b : game.getBricks()) b.draw(g);

                g.setColor(Color.ORANGE);
                g.drawString("Score: " + game.getScoreBoard().getScore(), 10, 20);

                break;
            case GameOver:
                g.setColor(Color.RED);
                g.drawString("Game Over!",game.windowWith/2-50,game.windowHeight/2);

                break;
            case Menu:
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("ARKANOID", game.windowWith / 2 - 70, game.windowHeight / 2 - 30);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                g.drawString("Press SPACE to Start", game.windowWith / 2 - 80, game.windowHeight / 2);
                break;
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle continuous paddle movement
        if (leftPressed) game.getPaddle().move(-10);
        if (rightPressed) game.getPaddle().move(10);

        game.update(); // Ball, collisions, etc.
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) leftPressed = true;
        if (key == KeyEvent.VK_RIGHT) rightPressed = true;

        if(game.getCurrentState() == GameManager.GameState.Menu&&key == KeyEvent.VK_SPACE){
            game.startGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) leftPressed = false;
        if (key == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
