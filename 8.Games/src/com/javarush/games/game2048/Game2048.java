package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.Arrays;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score;

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER", Color.RED, 30);
    }
    private boolean canUserMove(){
        for(int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if(gameField[i][j] == 0) return true;
            }
        }
        for(int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if((j+1 < SIDE) && gameField[i][j] == gameField[i][j+1]) return true;
                if((i+1 < SIDE) && gameField[i][j] == gameField[i+1][j]) return true;
                if((i-1 > 0) && gameField[i][j] == gameField[i-1][j]) return true;
                if((j-1 > 0) && gameField[i][j] == gameField[i][j-1]) return true;
            }
        }
        return false;
    }
    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.WHITE, "YOU WIN!!!", Color.ORANGE, 30);
    }
    private int getMaxTileValue(){
        int max = gameField[0][0];
        for(int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if(gameField[i][j] > max) max = gameField[i][j];
            }
        }
        return max;
    }
    private void rotateClockwise(){
        int[][] temptArray = new int[SIDE][SIDE];
        for(int i = 0; i < SIDE; i++){
            for(int j = 0; j < SIDE; j++){
                temptArray[j][SIDE - i - 1] = gameField[i][j];
            }
        }
        gameField = temptArray;
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if(isGameStopped){
            isGameStopped = false;
            createGame();
            drawScene();
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if (!canUserMove()){
            gameOver();
        }
        switch (key){
            case LEFT:
                if(isGameStopped){
                    break;
                }
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                if(isGameStopped){
                    break;
                }
                moveRight();
                drawScene();
                break;
            case DOWN:
                if(isGameStopped){
                    break;
                }
                moveDown();
                drawScene();
                break;
            case UP:
                if(isGameStopped){
                    break;
                }
                moveUp();
                drawScene();
                break;
        }
    }
    private void moveLeft(){
        boolean isChanged = false;
        for(int[] i : gameField){
            if(compressRow(i) | mergeRow(i) |compressRow(i)) isChanged = true;
        }
        if(isChanged) createNewNumber();
    }
    private void moveRight(){
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();

    }
    private void moveUp(){
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }
    private void moveDown(){
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }
    private boolean mergeRow(int[] row){
        boolean changed = false;
        for(int i = 0; i < row.length-1; i++) {
            if (row[i] != 0 && row[i + 1] != 0) {
                if (row[i] == row[i + 1]) {
                    score += row[i]+row[i+1];
                    setScore(score);
                    row[i] += row[i + 1];
                    row[i + 1] = 0;
                    compressRow(Arrays.copyOfRange(row, i, row.length));
                    changed = true;
                }
            }
        }
        return changed;
    }
    private boolean compressRow(int[] row){
        boolean changed = false;
        for(int i = 0; i < row.length; i++){
            for(int k = i+1; k < row.length; k++){
                if((k < i && row[k] == 0 && row[i] != 0)||(i < k && row[i] == 0 && row[k] != 0)){
                    int rowTemp = row[k];
                    row[k] = row[i];
                    row[i] = rowTemp;
                    changed = true;
                }
            }
        }
        return changed;
    }
    private Color getColorByValue(int value){
        switch (value){
            case 0: return Color.WHITE;
            case 2: return Color.AQUA;
            case 4: return Color.VIOLET;
            case 8: return Color.AQUAMARINE;
            case 16: return Color.BEIGE;
            case 32: return Color.BISQUE;
            case 64: return Color.BLUE;
            case 128: return Color.BROWN;
            case 256: return Color.YELLOW;
            case 512: return Color.GREEN;
            case 1024: return Color.ORANGE;
            case 2048: return Color.RED;
            default:return Color.BLACK;
        }
    }
    private void setCellColoredNumber(int x, int y, int value){
        if(value != 0)
        setCellValueEx(x, y, getColorByValue(value), String.valueOf(value));
        else setCellValueEx(x, y, getColorByValue(value), "");
    }
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }
    private void createGame(){
        gameField = new int[SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();
    }
    private void drawScene(){
        for(int y =0; y < SIDE; y++){
            for(int x = 0; x < SIDE; x++){
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }
    private void createNewNumber(){
        if(getMaxTileValue() == 2048) win();
        int maxY = getRandomNumber(SIDE);
        int maxX = getRandomNumber(SIDE);
        int rng = getRandomNumber(10);
        if(rng < 1){
            if(gameField[maxY][maxX] == 0)
            gameField[maxY][maxX] = 4;
            else{
                createNewNumber();
            }
        } else{
            if(gameField[maxY][maxX] == 0)
                gameField[maxY][maxX] = 2;
            else{
                createNewNumber();
            }
        }
    }
}
