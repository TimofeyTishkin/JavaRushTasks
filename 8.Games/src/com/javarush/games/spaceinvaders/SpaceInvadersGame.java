package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    private EnemyFleet enemyFleet;
    private List<Star> stars;
    public static final int HEIGHT = 64;
    public static final int WIDTH = 64;
    public static final int COMPLEXITY = 5;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private List<Bullet> playerBullets;
    private static final int PLAYER_BULLETS_MAX = 1;
    private int score;
    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if(x >= WIDTH || x < 0 ||y < 0|| y >= HEIGHT) return;
        super.setCellValueEx(x, y, cellColor, value);
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (playerShip.getDirection()){
            case RIGHT:
                if(key == Key.RIGHT){
                    playerShip.setDirection(Direction.UP);
                } break;
            case LEFT:
                if(key == Key.LEFT){
                    playerShip.setDirection(Direction.UP);
                } break;
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if(key == Key.SPACE ){
            if(isGameStopped) {
                createGame();
                return;
            }
            Bullet bullet = playerShip.fire();
            if(bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX){
                playerBullets.add(bullet);
            }
        }
        if(key == Key.LEFT){
            playerShip.setDirection(Direction.LEFT);
        } else if(key == Key.RIGHT){
            playerShip.setDirection(Direction.RIGHT);
        }

    }

    private void stopGameWithDelay(){
        animationsCount++;
        if(animationsCount >= 10) stopGame(playerShip.isAlive);

    }
    private void stopGame(boolean isWin){
        isGameStopped = true;
        stopTurnTimer();
        if(isWin){
            showMessageDialog(Color.WHITE, "YOU WIN!!!", Color.GREEN, 30);
        } else {
            showMessageDialog(Color.BLACK, "GAME OVER", Color.RED, 20);
        }
    }
    private void check(){
        playerShip.verifyHit(enemyBullets);
        if(!playerShip.isAlive) stopGameWithDelay();
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if(enemyFleet.getBottomBorder()>=playerShip.y) playerShip.kill();
        if(enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
    }
    private void removeDeadBullets(){
        for(int i = 0; i < enemyBullets.size(); i++){
            Bullet bullet;
            try {
                bullet = enemyBullets.get(i);
            } catch (IndexOutOfBoundsException e){
                break;
            }
            if(bullet.y >= HEIGHT-1 || !bullet.isAlive){
                enemyBullets.remove(bullet);
                i--;
            }
        }
        for(int i = 0; i < playerBullets.size(); i++){
            Bullet bullet;
            try {
                bullet = playerBullets.get(i);
            } catch (IndexOutOfBoundsException e){
                break;
            }
            if(bullet.y+bullet.height < 0 || !bullet.isAlive){
                playerBullets.remove(bullet);
                i--;
            }
        }
    }
    private void moveSpaceObjects(){
        enemyFleet.move();
        for (Bullet bullet : enemyBullets){
            bullet.move();
        }
        playerShip.move();
        for (Bullet bullet : playerBullets){
            bullet.move();
        }
    }
    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if(bullet != null) enemyBullets.add(bullet);
        setScore(score);
        drawScene();
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    private void createGame(){
        createStars();
        this.enemyFleet = new EnemyFleet();
        this.enemyBullets = new ArrayList<>();
        this.playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        this.playerBullets = new ArrayList<>();
        score = 0;
        drawScene();
        setTurnTimer(40);
    }
    private void drawScene(){
        drawField();
        for(Bullet bullet : enemyBullets){
            bullet.draw(this);
        }
        enemyFleet.draw(this);
        playerShip.draw(this);
        for(Bullet bullet : playerBullets){
            bullet.draw(this);
        }
    }

    private void drawField(){
        for (int y = 0; y < WIDTH; y++){
            for(int x = 0; x < HEIGHT; x++){
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }
        for(Star star : stars){
            star.draw(this);
        }
    }

    private void createStars(){
        this.stars = new ArrayList<>();
        for(int i = 0; i < 8; i++)
        this.stars.add(new Star(getRandomNumber(64),getRandomNumber(64)));
    }
}
