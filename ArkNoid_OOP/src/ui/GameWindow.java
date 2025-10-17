package ui;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameManager;
import main.Brick;

public class GameWindow extends Pane {

    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager game;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameWindow(int width, int height) {
        setPrefSize(width, height);
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);

        game = new GameManager();

        // Key events
        setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case LEFT -> leftPressed = true;
                case RIGHT -> rightPressed = true;
                case SPACE -> {
                    if (game.getCurrentState() != GameManager.GameState.Playing) {
                        game.reset();
                        game.startGame();
                    }
                }
                default -> {}
            }
        });

        setOnKeyReleased((KeyEvent e) -> {
            switch (e.getCode()) {
                case LEFT -> leftPressed = false;
                case RIGHT -> rightPressed = false;
                default -> {}
            }
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            private long last = 0;
            @Override
            public void handle(long now) {
                // ~60 FPS throttle
                if (last == 0 || now - last > 8_000_000) {
                    update();
                    render();
                    last = now;
                }
            }
        };
        timer.start();

        // request focus so keys work
        setFocusTraversable(true);
        requestFocus();
    }

    private void update() {
        if (leftPressed) game.getPaddle().move(-10);
        if (rightPressed) game.getPaddle().move(10);
        game.update();
    }

    private void render() {
        // clear
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        switch (game.getCurrentState()) {
            case Playing -> {
                game.getBall().draw(gc);
                game.getPaddle().draw(gc);
                for (Brick b : game.getBricks()) b.draw(gc);

                gc.setFill(Color.YELLOW);
                gc.setFont(Font.font("Arial", 12));
                gc.fillText("Score: " + game.getScoreBoard().getScore(), 10, 20);
            }
            case GameOver -> {
                gc.setFill(Color.RED);
                gc.setFont(Font.font("Arial", 24));
                gc.fillText("Game Over!", GameManager.windowWith/2-50, GameManager.windowHeight/2);
            }
            case Menu -> {
                gc.setFill(Color.WHITE);
                gc.setFont(Font.font("Arial", 24));
                gc.fillText("ARKANOID", GameManager.windowWith / 2 - 70, GameManager.windowHeight / 2 - 30);
                gc.setFont(Font.font("Arial", 16));
                gc.fillText("Press SPACE to Start", GameManager.windowWith / 2 - 80, GameManager.windowHeight / 2);
            }
        }
    }
}
