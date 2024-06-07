package com.javarush.games.racer;

public class FinishLine extends GameObject{
    private boolean isVisible = false;
    public boolean isCrossed(PlayerCar playerCar){
        return playerCar.y < this.y;
    }
    public void move(int boost){
        if(!isVisible) return;
        y+=boost;
    }
    public void show(){
        isVisible = true;
    }
    public FinishLine() {
        super(RacerGame.ROADSIDE_WIDTH, -1 * ShapeMatrix.FINISH_LINE.length, ShapeMatrix.FINISH_LINE);
    }
}
