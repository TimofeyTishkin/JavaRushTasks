package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private static final int RACE_GOAL_CARS_COUNT = 40;
    private ProgressBar progressBar;
    private int score;
    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU WIN!!!", Color.YELLOW, 50);
        stopTurnTimer();
    }
    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU LOST", Color.RED, 45);
        stopTurnTimer();
        player.stop();
    }
    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case RIGHT : if(player.getDirection() == Direction.RIGHT) player.setDirection(Direction.NONE); break;
            case LEFT  : if(player.getDirection() == Direction.LEFT) player.setDirection(Direction.NONE); break;
            case UP    : player.speed = 1; break;
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case SPACE : if(isGameStopped) createGame(); break;
            case RIGHT : player.setDirection(Direction.RIGHT); break;
            case LEFT  : player.setDirection(Direction.LEFT ); break;
            case UP    : player.speed = 2; break;
        }
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
        } else {
            score-= 5;
            setScore(score);
            roadManager.generateNewRoadObjects(this);
            if(roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) finishLine.show();
            if(finishLine.isCrossed(player)) {
                win();
                drawScene();
                return;
            }
            moveAll();
        }
        drawScene();
    }

    private void moveAll(){
        roadMarking.move(player.speed);
        player.move();
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
    }
    @Override
    public void setCellColor(int x, int y, Color color) {
        if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) return;
        super.setCellColor(x, y, color);
    }

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();

    }
    private void createGame(){
        setTurnTimer(40);
        isGameStopped = false;
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;
        setScore(score);
        drawScene();
    }
    private void drawScene(){
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void drawField() {
        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                if(x == CENTER_X) setCellColor(x, y, Color.WHITE);
                else if(x >= ROADSIDE_WIDTH && x < (WIDTH - ROADSIDE_WIDTH)) setCellColor(x, y, Color.DIMGREY);
                else setCellColor(x, y, Color.GREEN);
            }
        }
    }
}
