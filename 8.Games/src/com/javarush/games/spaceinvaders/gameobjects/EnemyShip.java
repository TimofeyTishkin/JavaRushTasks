package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class EnemyShip extends Ship{
    public int score = 15;

    @Override
    public void kill() {
        if(!isAlive) return;
        isAlive = false;
        setAnimatedView(false, ShapeMatrix.KILL_ENEMY_ANIMATION_FIRST,
                ShapeMatrix.KILL_ENEMY_ANIMATION_SECOND,
                ShapeMatrix.KILL_ENEMY_ANIMATION_THIRD);
    }

    public EnemyShip(double x, double y) {
        super(x, y);
        super.setStaticView(ShapeMatrix.ENEMY);
    }
    public void move(Direction direction, double speed){
        switch (direction){
            case UP: break;
            case DOWN: this.y+=2; break;
            case LEFT: this.x-=speed; break;
            case RIGHT: this.x+=speed; break;
        }
    }

    @Override
    public Bullet fire() {
        return new Bullet( x + 1, y + height, Direction.DOWN);
    }
}
