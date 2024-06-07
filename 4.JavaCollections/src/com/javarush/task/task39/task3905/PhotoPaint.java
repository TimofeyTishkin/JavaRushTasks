package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if(image.length <= y || y < 0 || x < 0 || image[0].length <= x) return false;
        if(image[y][x].equals(desiredColor)) return false;
        paintFill(image, x+1, y, desiredColor);
        paintFill(image, x, y+1, desiredColor);


        return true;
    }
}
