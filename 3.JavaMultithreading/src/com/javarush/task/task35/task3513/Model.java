package com.javarush.task.task35.task3513;
import java.util.*;

public class Model {
    private Stack<Tile[][]> previousStates = new Stack<>();

    private Stack<Integer> previousScores = new Stack<>();

    private boolean isSaveNeeded = true;
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score;
    protected int maxTile;
    public void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));

        priorityQueue.peek().getMove().move();
    }
    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency result = new MoveEfficiency(getEmptyTiles().size(), score, move);
        move.move();
        if(!hasBoardChanged()){
            result = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return result;
    }
    public boolean hasBoardChanged(){
        boolean isChanged = false;
        for (int i = 0; i < gameTiles.length; i++){
            for(int j = 0; j < gameTiles[0].length; j++){
                if(gameTiles[i][j].value != previousStates.peek()[i][j].value){
                    isChanged = true; break;
                }
            }
        }
        return isChanged;
    }
    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        if(n == 0) left();
        if(n == 1) right();
        if(n == 2) up();
        if(n == 3) down();

    }
    private void saveState(Tile[][] atas){
        if(atas.length <= 0) return;
        Tile[][] atos = new Tile[atas.length][atas[0].length];
        for (int i = 0; i < atos.length; i++){
            for(int j = 0; j < atos[0].length; j++){
                atos[i][j] = new Tile();
                atos[i][j].value = atas[i][j].value;
            }
        }
        previousStates.push(atos);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if(previousStates.empty() || previousScores.empty()) return;
        gameTiles = previousStates.pop();
        score = previousScores.pop();

    }
    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value) {
                    return true;
                }
                if (gameTiles[j][i].value == gameTiles[j + 1][i].value) {
                    return true;
                }
            }
        }
        return false;
    }
    public void up(){
        saveState(gameTiles);
        turnArrayToTheRight();
        turnArrayToTheRight();
        turnArrayToTheRight();
        left();
        turnArrayToTheRight();
    }

    public void down(){
        saveState(gameTiles);
        turnArrayToTheRight();
        left();
        turnArrayToTheRight();
        turnArrayToTheRight();
        turnArrayToTheRight();
    }

    public void right(){
        saveState(gameTiles);
        turnArrayToTheRight();
        turnArrayToTheRight();
        left();
        turnArrayToTheRight();
        turnArrayToTheRight();
    }
    public void left() {
        if(isSaveNeeded){
            saveState(gameTiles);
        }
        boolean isChange = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                isChange = true;
        }

        if (isChange) {
            addTile();
        }
        isSaveNeeded = true;
    }
    void turnArrayToTheRight (){
        Tile[][] tempTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                tempTile[i][FIELD_WIDTH - 1 - j] = gameTiles[j][i];
            }
        }
        gameTiles = tempTile.clone();
    }

    private boolean compressTiles(Tile[] tiles){
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            for (int j = i + 1; j < tiles.length; j++) {
                if (tiles[i].value == 0) {
                    tiles[i].value = tiles[j].value;
                    tiles[j].value = 0;
                    if(!(tiles[i].isEmpty() && tiles[j].isEmpty())) isChanged = true;
                }
            }
        }
        return isChanged;
    }
    private boolean mergeTiles(Tile[] tiles){
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value) {
                if(!(tiles[i].isEmpty() && tiles[i+1].isEmpty())) isChanged = true;
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1].value = 0;

                if(compressTiles(tiles)) isChanged = true;

                score += tiles[i].value;
                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
            }
        }
        return isChanged;
    }
    void resetGameTiles(){
        score = 0;
        maxTile = 0;
        for(int y = 0; y < FIELD_WIDTH; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                gameTiles[y][x] = new Tile();
            }
        }
        addTile();
        addTile();
    }
    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if(emptyTiles.size() > 0)
        emptyTiles.get((int)(emptyTiles.size() * Math.random())).value = (Math.random() < 0.9 ? 2 : 4);
    }
    public Model() {
        resetGameTiles();
    }
    private List<Tile> getEmptyTiles(){
        List<Tile> result = new ArrayList<>();
        for(int y = 0; y < FIELD_WIDTH; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                if(gameTiles[y][x].isEmpty()) result.add(gameTiles[y][x]);
            }
        }
        return result;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}
