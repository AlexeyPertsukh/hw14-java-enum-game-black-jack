/*
https://ru.wikipedia.org/wiki/%D0%98%D0%B3%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5_%D0%BA%D0%B0%D1%80%D1%82%D1%8B
 */

public class Card {

    public final static String STR_UNKNOWN = "?";
    public final static boolean OPEN = true;
    public final static boolean HIDDEN = false;

    private CardSuit suit;
    private CardRating rating;
    private boolean isOpen;

    public Card(CardRating rating, CardSuit suit) {
        this(rating, suit, true);
    }

    public Card(CardRating rating, CardSuit suit, boolean isVisible) {
        this.rating = rating;
        this.suit = suit;
        this.isOpen = isVisible;

    }

    public int getPoint() {
        return rating.getPoint();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setStateOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void print() {
        System.out.println(getInfoColor());
    }

    public String getInfoColor() {
        String str;
        if(isOpen) {
            str = String.format("%s %s", suit.getColor() + suit.getPicChar() + My.ANSI_RESET, rating.getNameRus());
        }    else
        {
            str = STR_UNKNOWN;
        }
        return str;
    }

    public String getInfo() {
        String str;
        if(isOpen) {
            str = String.format("%s %s", suit.getPicChar(), rating.getNameRus());
        } else {
            str = STR_UNKNOWN;
        }
        return str;
    }


    public String[] getColorPictureCard() {
        String[] pic = getPictureCard();

        //если карта видна - показываем еще и цвет
        if(isOpen) {
            for (int i = 0; i < pic.length; i++) {
                pic[i] = suit.getColor() + pic[i] + My.ANSI_RESET;
            }
        }
        return pic;
    }


    public String[] getPictureCard() {
        String[] pic;
        if(isOpen) {
            pic = rating.getPicture().clone();
        }
        else {
            pic = Picture.BACK.clone();
        }

        My.changeCharInArr(pic, Picture.CHANGE_CHAR, suit.getPrimitivePicChar());

        return pic;
    }

    public CardRating getRating() {
        return rating;
    }
}
