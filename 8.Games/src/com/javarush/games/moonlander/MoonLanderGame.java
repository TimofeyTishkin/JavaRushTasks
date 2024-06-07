package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private GameObject landscape;
    private Rocket rocket;
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private GameObject platform;
    private boolean isGameStopped;
    private int score;

    private void gameOver(){
        rocket.crash();
        score = 0;
        setScore(score);
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "YOU LOST", Color.RED, 40);
        stopTurnTimer();
    }
    private void win(){
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "YOU WIN! CONGRATS!", Color.WHITE, 40);
        stopTurnTimer();
    }
    private void check(){
        if(rocket.isCollision(landscape) && !(rocket.isCollision(platform) && rocket.isStopped())) gameOver();
        if(rocket.isCollision(platform) && !rocket.isStopped()) win();
    }
    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case UP:
                isUpPressed = false;
                break;
            case RIGHT:
                isRightPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case UP:
                isUpPressed = true;
                break;
            case RIGHT:
                isRightPressed = true;
                isLeftPressed = false;
                break;
            case LEFT:
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case SPACE:
                if(isGameStopped){
                    createGame();
                    break;
                }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if(x >= WIDTH || x < 0 || y < 0 || y >= HEIGHT) return;
        super.setCellColor(x, y, color);
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        if(score > 0)score--;
        setScore(score);
        drawScene();
    }

    private void createGameObjects(){
        rocket = new Rocket(WIDTH >> 1, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }
    private void createGame(){
        createGameObjects();
        drawScene();
        setTurnTimer(50);
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        isGameStopped = false;
        score = 1000;
    }
    private void drawScene(){
        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                setCellColor(x, y, Color.BLACK);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }
}
