package com.card;

import com.game.PictureStorage;
import com.game.Util;

public enum CardRating {
    TWO("2", 2, PictureStorage.TWO),
    THREE("3", 3, PictureStorage.THREE),
    FOUR("4", 4, PictureStorage.FOUR),
    FIVE("5", 5, PictureStorage.FIVE),
    SIX("6", 6, PictureStorage.SIX),
    SEVEN("7", 7, PictureStorage.SEVEN),
    EIGHT("8", 8, PictureStorage.EIGHT),
    NINE("9", 9, PictureStorage.NINE),
    TEEN("10", 10, PictureStorage.TEEN),
    JACK("Валет", 10, PictureStorage.JACK),
    QUEEN("Дама", 10, PictureStorage.QUEEN),
    KING("Король", 10, PictureStorage.KING),
    ACE("Туз", 11, PictureStorage.ACE);

    private final String nameRus;
    private final int point;
    private final String[] picture;

    CardRating(String nameRus, int point, String[] picture){
        this.nameRus = nameRus;
        this.point = point;
        this.picture = picture;
    }

    public String getNameRus() {
        return nameRus;
    }

    public int getPoint() {
        return point;
    }

    public String[] getPicture() {
        return picture;
    }

    //короткое имя номинала- цифра(для карт 2... 10), либо первая буква (J ... A)
    public String getShortName() {
        if(Util.isInteger(nameRus)) {
            return nameRus;
        }

        return Character.toString(this.toString().charAt(0));
    }
}
