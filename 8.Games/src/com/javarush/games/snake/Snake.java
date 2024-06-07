package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    private List<GameObject> snakeParts = new ArrayList<>();

    public int getLength(){
        return snakeParts.size();
    }
    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x+1, y));
        snakeParts.add(new GameObject(x+2, y));
    }
    public boolean checkCollision(GameObject uppa){
        for(GameObject object : snakeParts){
            if(object.x == uppa.x && object.y == uppa.y) return true;
        }
        return false;
    }
    public void removeTail(){
        snakeParts.remove(snakeParts.size()-1);
    }
    public GameObject createNewHead(){
        GameObject head = snakeParts.get(0);
        int headX = head.x;
        int headY = head.y;
        switch (direction){
            case RIGHT:return new GameObject(headX+1, headY);
            case UP:   return new GameObject(headX, headY-1);
            case LEFT: return new GameObject(headX-1, headY);
            case DOWN: return new GameObject(headX, headY+1);
            default:throw new UnsupportedOperationException();
        }
    }
    public void move(Apple apple){
        GameObject newHead = createNewHead();
        if(checkCollision(newHead)){
            this.isAlive = false;
            return;
        }
        if(newHead.x < 0||newHead.y < 0||newHead.x>=SnakeGame.WIDTH||newHead.y>=SnakeGame.HEIGHT){
            this.isAlive = false;
            return;
        }
        snakeParts.add(0, newHead);
        if(!(apple.x == newHead.x && apple.y == newHead.y))
        removeTail();
        else apple.isAlive = false;
    }
    public void draw(Game game){
        for(GameObject object : snakeParts){
            String toPrint = snakeParts.get(0).equals(object) ? HEAD_SIGN : BODY_SIGN;
            game.setCellValueEx(object.x, object.y,
                    Color.NONE, toPrint, isAlive?Color.BLACK : Color.RED, 75);
        }
    }

    public void setDirection(Direction direction) {
        if(this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.UP && direction == Direction.DOWN) return;
        else if(this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT) return;
        if(this.direction == Direction.LEFT && snakeParts.get(0).x == snakeParts.get(1).x) return;
        if(this.direction == Direction.RIGHT && snakeParts.get(0).x == snakeParts.get(1).x) return;
        if(this.direction == Direction.DOWN && snakeParts.get(0).y == snakeParts.get(1).y) return;
        if(this.direction == Direction.UP && snakeParts.get(0).y == snakeParts.get(1).y) return;
        this.direction = direction;
    }

}
