package com.card;

import com.game.Picture;
import com.game.Util;

public enum CardRating {
    TWO("2", 2, Picture.TWO),
    THREE("3", 3, Picture.THREE),
    FOUR("4", 4, Picture.FOUR),
    FIVE("5", 5, Picture.FIVE),
    SIX("6", 6, Picture.SIX),
    SEVEN("7", 7, Picture.SEVEN),
    EIGHT("8", 8, Picture.EIGHT),
    NINE("9", 9, Picture.NINE),
    TEEN("10", 10, Picture.TEEN),
    JACK("Валет", 10, Picture.JACK),
    QUEEN("Дама", 10, Picture.QUEEN),
    KING("Король", 10, Picture.KING),
    ACE("Туз", 11, Picture.ACE);

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
