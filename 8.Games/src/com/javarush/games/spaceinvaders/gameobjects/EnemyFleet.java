package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public int getShipsCount(){
        if(ships == null) return 0;
        return ships.size();
    }
    public double getBottomBorder(){
        if(ships == null || ships.isEmpty()) return 0;
        double max = ships.get(0).y+ships.get(0).height;
        for(Ship ship : ships){
            if(ship.y+ship.height > max) max = ship.y+ship.height;
        }
        return max;
    }
    public void deleteHiddenShips(){
        ships.removeIf(enemyShip -> !enemyShip.isVisible());
    }
    public int verifyHit(List<Bullet> bullets){
        if(bullets.isEmpty()) return 0;
        int allBullets = 0;
        for(Bullet bullet : bullets) {
            for (EnemyShip ship : ships) {
                if (bullet.isCollision(ship) && bullet.isAlive && ship.isAlive) {
                    bullet.kill();
                    ship.kill();
                    allBullets += ship.score;
                }
            }
        }
        return allBullets;
    }
    public Bullet fire(Game game){
        if(ships.size() < 1) return null;
        int rare = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY);
        if(rare > 0) return null;
        int rng = game.getRandomNumber(ships.size());
        return ships.get(rng).fire();
    }
    public void move(){
        if(ships.size() < 1) return;
        if(direction == Direction.LEFT && getLeftBorder() < 0.0){
            direction = Direction.RIGHT;
        } else if(direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH){
            direction = Direction.LEFT;
        } else {
            double speed = getSpeed();
            for (EnemyShip ship : ships) {
                ship.move(direction, speed);
            }
            return;
        }
        double speed = getSpeed();
        for (EnemyShip ship : ships) {
            ship.move(Direction.DOWN, speed);
        }
    }
    private double getSpeed(){
        return Double.min(2.0, 3.0/ships.size());
    }
    private void createShips(){
        this.ships = new ArrayList<>();
        for(int y = 0; y < ROWS_COUNT; y++) {
            for (int x = 0; x < COLUMNS_COUNT; x++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
    }
    private double getLeftBorder(){
        double minX = ships.get(0).x;
        for(EnemyShip enemyShip : ships){
            if(enemyShip.x < minX) minX = enemyShip.x;
        }
        return minX;
    }
    private double getRightBorder(){
        double maxX = ships.get(0).x + ships.get(0).width;
        for(EnemyShip enemyShip : ships){
            if((enemyShip.x+enemyShip.width) > maxX) maxX = enemyShip.x + enemyShip.width;
        }
        return maxX;
    }
    public EnemyFleet() {
        createShips();
    }
    public void draw(Game game){
        for(EnemyShip ship : this.ships){
            ship.draw(game);
        }
    }
}
