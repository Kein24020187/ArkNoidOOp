package main;

import java.util.*;

public class GameManager {
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private ScoreBoard scoreBoard;
    public static int windowWith = 500;
    public static int windowHeight = 400;
    public enum GameState{
        Playing,
        GameOver,
        Menu
     }
     GameState currentState = GameState.Playing;
    public GameManager() {
        reset();
    }

    public GameState getCurrentState(){
        return currentState;
    }
    int startX = (windowWith - (windowWith/13 * 11) - (windowWith/195) * 10)/2;
    int spacing = windowWith/165;
    public void createbrick(int startX, int startY, int spacing){
        bricks.clear();
        for( int i = 0 ; i<11; i++){
            for(int j = 0 ; j< 3 ; j++){
                int x = startX + i*( windowWith/13+spacing);
                int y = startY + j*(windowWith/25+spacing);
                String type = (Math.random() < 0.2) ? "strong" : "normal";
                bricks.add(BrickFactory.createBrick(type, x, y));
            }
        }
    }

    public void reset() {
        paddle = new Paddle(windowWith / 2 - windowWith / 12,
                windowHeight - windowHeight / 25,
                windowWith / 6,
                windowHeight / 35,
                windowWith);

        ball = new Ball(paddle.getX() + paddle.width / 2,
                paddle.getY() - 10,
                10,
                windowWith,
                windowHeight);

        scoreBoard = new ScoreBoard();
        bricks = new ArrayList<>();

        // Tạo gạch với khoảng cách đẹp và vị trí chính xác
        createbrick(startX,windowHeight/20,spacing);
    }


    public void update() {
        switch (currentState) {
            case Playing:
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

                if(ball.getY()+ball.height>=windowHeight){
                    currentState = GameState.GameOver;
                    getCurrentState();
                } 
                break;
            case GameOver:
                
            default:
                break;
        }
    }

    public Ball getBall() { return ball; }
    public Paddle getPaddle() { return paddle; }
    public List<Brick> getBricks() { return bricks; }
    public ScoreBoard getScoreBoard() { return scoreBoard; }
}
