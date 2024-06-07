package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String MINE =  "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE*SIDE;
    private  int score;
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }
    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.ALICEBLUE, "Game Over", Color.BLACK, 30);
    }
    private void restart(){
        isGameStopped = false;
        countClosedTiles = SIDE*SIDE;
        score = 0;
        setScore(score);
        countMinesOnField = 0;
        createGame();
    }
    private void markTile(int x, int y) {
        if (gameField[y][x].isOpen || (countFlags < 1&&!gameField[y][x].isFlag) || isGameStopped) {
            return;
        }
        if (!gameField[y][x].isFlag) {
            gameField[y][x].isFlag = true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        } else {
            gameField[y][x].isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.ORANGE);
        }

    }
    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.AQUA, "You Win!", Color.GOLD, 50);
    }
    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
        } else {
            openTile(x, y);
        }
    }

    private void openTile(int x, int y) {
        if(gameField[y][x].isOpen || gameField[y][x].isFlag || isGameStopped) return;
        gameField[y][x].isOpen = true;
        countClosedTiles--;
        setCellColor(x,y, Color.GREEN);
        if(gameField[y][x].isMine){
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        } else {
            score+=5;
            setScore(score);
            if(countClosedTiles == countMinesOnField) win();
            if(gameField[y][x].countMineNeighbors == 0){
                setCellValue(x, y, "");
                for(GameObject object :getNeighbors(gameField[y][x])){
                    if(!object.isOpen) {
                        openTile(object.x, object.y);
                    }
                }
            }
            if(gameField[y][x].countMineNeighbors != 0)
            setCellNumber(x, y, gameField[y][x].countMineNeighbors);

        }
    }
    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
                setCellValue(x, y, "");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }
    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if(gameField[y][x].isMine) continue;
                List<GameObject> array = getNeighbors(gameField[y][x]);
                int countMineNeighbors = 0;
                for (GameObject gameObject: array) {
                    if(gameObject.isMine) countMineNeighbors++;
                }
                gameField[y][x].countMineNeighbors = countMineNeighbors;
            }
        }
    }
}