/*
♦ ♢
♥ ♡
♠ ♤
♣ ♧
*/

public enum CardSuit {
    //упрощенные символы мастей- для изображения на картинках карт, т.к. нормальные символы мастей сдвигают картинку
    DIAMONDS("бубны", My.ANSI_RED,'♦','■' ),    //♦  ■
    HEARTS("червы", My.ANSI_RED,'♥','▼' ),      //♥  ▼
    SPADES("пики", My.ANSI_GREEN,'♠','▲'),      //♠  ▲
    CLUBS("крести", My.ANSI_GREEN, '♣','+');    //♣  *

    private final char pictureChar;
    private final char primitivePictureChar;
    private final String nameRus;
    private final String color;

    CardSuit(String rusName, String color, char pictureChar, char primitivePictureChar) {
        this.nameRus = rusName;
        this.color = color;
        this.pictureChar = pictureChar;
        this.primitivePictureChar = primitivePictureChar;

    }

    public char getPicChar() {
        return pictureChar;
    }

    public String getNameRus() {
        return nameRus;
    }

    public String getColor() {
        return color;
    }

    public char getPrimitivePicChar() {
        return primitivePictureChar;
    }
}
