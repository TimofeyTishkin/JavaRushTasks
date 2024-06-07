package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();
    private static final int PLAYER_CAR_DISTANCE = 12;
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private void generateMovingCar(Game game){
        int rng = game.getRandomNumber(100);
        if(rng < 10 && !isMovingCarExists()) addRoadObject(RoadObjectType.DRUNK_CAR, game);
    }
    private boolean isMovingCarExists(){
        boolean hasAny = false;
        for (RoadObject object:items) {
            if(object.type == RoadObjectType.DRUNK_CAR) hasAny = true;
        }
        return hasAny;
    }
    private boolean isRoadSpaceFree(RoadObject object){
        boolean da = true;
        for(RoadObject object1:items){
            if(object1.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) da = false;
        }
        return da;
    }
    private void generateRegularCar(Game game){
        int rng = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if(rng < 30) addRoadObject(RoadObjectType.values()[carTypeNumber], game);
    }
    public boolean checkCrush(PlayerCar player){
        boolean isCrushed = false;
        for(RoadObject object:items){
            if(object.isCollision(player)) isCrushed = true;
        }
        return isCrushed;
    }
    private void deletePassedItems(){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).y >= RacerGame.HEIGHT){
                if(items.get(i).type != RoadObjectType.THORN) passedCarsCount++;
                items.remove(i);
                i--;
            }
        }
    }
    public void generateNewRoadObjects(Game game){
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }
    private void generateThorn(Game game){
        int rng = game.getRandomNumber(100);
        if(!isThornExists() && rng < 10) addRoadObject(RoadObjectType.THORN, game);
    }
    private boolean isThornExists(){
        boolean hasAny = false;
        for (RoadObject object:items) {
            if(object.type == RoadObjectType.THORN) hasAny = true;
        }
        return hasAny;
    }
    public void move(int boost){
        for (RoadObject object:items) {
            object.move(boost+object.speed, items);
        }
        deletePassedItems();
    }
    public void draw(Game game){
        for (RoadObject object:items) {
            object.draw(game);
        }
    }
    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type, x, y);
        if(object != null && isRoadSpaceFree(object))
        items.add(object);
    }
    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        RoadObject object = null;
        switch (type) {
            case THORN : object = new Thorn(x, y); break;
            case DRUNK_CAR: object = new MovingCar(x, y); break;
            default: object = new Car(type, x, y); break;
        }
        return object;
    }
}
