package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;
    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU WIN!!!", Color.YELLOW, 45);
    }
    private void gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.AZURE, "YOU LOSE", Color.RED, 45);
    }
    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case DOWN:  snake.setDirection(Direction.DOWN ); break;
            case UP:    snake.setDirection(Direction.UP   ); break;
            case LEFT:  snake.setDirection(Direction.LEFT ); break;
            case RIGHT: snake.setDirection(Direction.RIGHT); break;
            case SPACE: if(isGameStopped) createGame();
        }
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if(!apple.isAlive){
            setScore(score+=5);
            createNewApple();
            turnDelay-=10;
            setTurnTimer(turnDelay);
        }
        if(!snake.isAlive) gameOver();
        if(snake.getLength() > GOAL) win();
        drawScene();
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    private void createGame(){
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        isGameStopped = false;
        score = 0;
        setScore(score);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }
    private void drawScene(){
        for(int y  = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }
    private void createNewApple(){
        int rngByWidth = getRandomNumber(WIDTH);
        int rngByHeight = getRandomNumber(HEIGHT);
        apple = new Apple(rngByWidth, rngByHeight);
        if(snake.checkCollision(apple)) createNewApple();
    }
}
