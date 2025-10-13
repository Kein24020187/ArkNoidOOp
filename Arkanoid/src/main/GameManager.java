package main;

import java.util.*;

public class GameManager {
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private ScoreBoard scoreBoard;
     int windowWith = 500;
     int windowHeight = 400;
    public GameManager() {
        reset();
    }
    public void createbrick(int rows, int cols, int startX, int startY, int spacing){
        bricks.clear();
        for( int i = 0 ; i<rows; i++){
            for(int j = 0 ; j< cols ; j++){
                int x = i*(60+spacing);
                int y = j*25 + spacing;
                String type = (Math.random() < 0.2) ? "strong" : "normal";
                bricks.add(BrickFactory.createBrick(type, x, y));
            }
        }
    }

    public void reset() {
        ball = new Ball(200, 300, 10,windowWith,windowHeight);
        paddle = new Paddle(180, 350, 80, 10,windowWith);
        scoreBoard = new ScoreBoard();
        bricks = new ArrayList<>();

        // Create some bricks
        createbrick(7,5,10,50,30);
    }

    public void update() {
        ball.update();
        if((ball.getY()+ball.height>=paddle.getY())&&(ball.getX()>=paddle.getX()
        &&ball.getX()<=paddle.getX()+paddle.width)) ball.bounceY();//check paddle and ball  collision
        for(Brick b: bricks){
            if(b.isDestroyed()) continue;
            if (ball.intersects(b)) {
                // Tính độ chồng (overlap) theo hai trục
                double overlapX = Math.min(ball.x + ball.width - b.x, b.x + b.width - ball.x);
                double overlapY = Math.min(ball.y + ball.height - b.y, b.y + b.height - ball.y);
                // Nảy theo hướng có độ chồng nhỏ hơn (tức là hướng tiếp xúc trước)
                if (overlapX < overlapY) {
                    ball.bounceX();  // Nảy theo trục X
                } else {
                    ball.bounceY();  // Nảy theo trục Y
                }
                scoreBoard.addScore();
                b.hit();
            }

        }// check bricks and ball collision
        bricks.removeIf(Brick::isDestroyed);
        System.out.println(scoreBoard.getScore());
    }

    public Ball getBall() { return ball; }
    public Paddle getPaddle() { return paddle; }
    public List<Brick> getBricks() { return bricks; }
    public ScoreBoard getScoreBoard() { return scoreBoard; }
}
