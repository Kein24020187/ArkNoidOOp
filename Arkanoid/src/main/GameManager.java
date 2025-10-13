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

    public void reset() {
        ball = new Ball(200, 300, 10,windowWith,windowHeight);
        paddle = new Paddle(180, 350, 80, 10,windowWith);
        scoreBoard = new ScoreBoard();
        bricks = new ArrayList<>();

        // Create some bricks
        for (int i = 0; i < 5; i++) {
            bricks.add(BrickFactory.createBrick("normal", 60 * i + 10, 50));
        }
    }

    public void update() {
        
        ball.update();
        if((ball.getY()+ball.height>=paddle.getY())&&(ball.getX()>=paddle.getX()
        &&ball.getX()<paddle.getX()+paddle.width)) ball.bounceY();//check paddle and ball  collision
        for(Brick b: bricks){
            if(b.isDestroyed()) continue;
           if((ball.getY()<=b.getY()+b.height)&&
           (ball.getX()>b.getX()&&ball.getX()<=b.getX()+b.width)){ball.bounceY(); b.hit();} 
           else if(ball.getY()+ball.height>=b.getY()&&ball.getY()+ball.height<b.getY()+b.height&&
           (ball.getX()>b.getX()&&ball.getX()<=b.getX()+b.width)){ball.bounceY(); b.hit();} 
        }// check bricks and ball collision
        bricks.removeIf(Brick::isDestroyed);
        
    }

    public Ball getBall() { return ball; }
    public Paddle getPaddle() { return paddle; }
    public List<Brick> getBricks() { return bricks; }
    public ScoreBoard getScoreBoard() { return scoreBoard; }
}
