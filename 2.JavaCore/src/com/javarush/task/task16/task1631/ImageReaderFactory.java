package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes ImageType){
        if(ImageType == null) throw new IllegalArgumentException("Неизвестный тип картинки");
        ImageReader readerus = null;
        if(ImageType.equals(ImageTypes.JPG) ||
                ImageType.equals(ImageTypes.BMP)||
                ImageType.equals(ImageTypes.PNG)) {
            if(ImageType.equals(ImageTypes.JPG)) {
                readerus = new JpgReader();
            }
            if(ImageType.equals(ImageTypes.BMP)){
                readerus =  new BmpReader();
            }
            if(ImageType.equals(ImageTypes.PNG)){
            readerus = new PngReader();
        }
        return readerus;}
        else throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
