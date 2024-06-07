package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class Star extends GameObject{
    private static final String STAR_SIGN = "\u2605";
    public void draw(Game game){
        game.setCellValueEx((int)this.x, (int)this.y, Color.NONE, STAR_SIGN, Color.CORNSILK, 100);
    }
    public Star(double x, double y) {
        super(x, y);
    }
}
