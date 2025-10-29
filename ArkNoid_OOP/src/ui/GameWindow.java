package ui;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.GameManager;
import main.Brick;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameWindow extends Pane {
    private int currentSelection = 0;
    private boolean blink = true;
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager game;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private void setupBlinkingEffect() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.5), e -> blink = !blink)
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public GameWindow(int width, int height) {
        setPrefSize(width, height);
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);

        game = new GameManager();
        setupBlinkingEffect();
        // Key events
        setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case LEFT -> leftPressed = true;
                case RIGHT -> rightPressed = true;

                // Điều khiển menu khi đang ở trạng thái MENU
                case UP -> {
                    if (game.getCurrentState() == GameManager.GameState.Menu) {
                        currentSelection = (currentSelection - 1 + 3) % 3;
                    }
                }
                case DOWN -> {
                    if (game.getCurrentState() == GameManager.GameState.Menu) {
                        currentSelection = (currentSelection + 1) % 3;
                    }
                }
                case SPACE -> {
                    if (game.getCurrentState() == GameManager.GameState.Menu) {
                        if (currentSelection == 0) game.startGame();
                        else if (currentSelection == 1) System.out.println("oke");
                        else if (currentSelection == 2) System.out.println("oke");;
                    } else {
                        if (game.getCurrentState() != GameManager.GameState.Playing) {
                            game.reset();
                            game.startGame();
                        }
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
                // Load ảnh 1 lần (nên đưa ra ngoài để không load liên tục)
                Image background = new Image(getClass().getResource("/images/menu_bg.jpg").toExternalForm());

                // Vẽ ảnh phủ màn hình
                gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

                // Vẽ chữ phía trên
                // Vẽ nền mờ sau chữ
                // 
                String[] menuItems = {"Start Game", "Settings", "Instruction"};
                double startX = GameManager.windowWith / 2 - 55;
                double startY = GameManager.windowHeight / 2 - 30;
                

                for (int i = 0; i < menuItems.length; i++) {
                    if (i == currentSelection) {
                        if (blink) { // chỉ vẽ khi hiệu ứng bật
                            gc.setFill(Color.YELLOW); // mục đang chọn (nổi bật)
                            gc.fillText("> " + menuItems[i], startX, startY + i * 40);
                        }
                    } else {
                        gc.setFill(Color.WHITE);
                        gc.fillText("  " + menuItems[i], startX, startY + i * 40);
                    }
                }

            }

        }
    }
}
