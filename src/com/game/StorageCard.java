package com.game;

import com.card.Card;
import com.card.CardRating;
import com.card.CardSuit;

public class StorageCard {

    private static final int NUM_COLUMN = 6;

    private Card[] cards;

    public StorageCard() {
        cards = new Card[0];
    }

    public void add(Card card) {
        Card[] tmp = new Card[cards.length + 1];
        System.arraycopy(cards, 0, tmp, 0, cards.length);
        tmp[cards.length] = card;
        cards = tmp;
    }

    public void addRandom() {
        CardRating[] ratings = CardRating.values();
        CardSuit[] suits = CardSuit.values();
        int nRating = Util.random(ratings.length);
        int nSuit = Util.random(suits.length);

        add(new Card(ratings[nRating], suits[nSuit], Card.OPEN));
    }



    public void del(int num) {
        Card[] tmp = new Card[cards.length - 1];
        for (int i = 0; i < tmp.length; i++) {
            if(i < num) {
                tmp[i] = cards[i];
            }
            else {
                tmp[i] = cards[i+1];
            }
        }
        cards = tmp;
    }


    //добавить полную колоду карт
    void addFullDeck(boolean isOpen) {
        CardRating[] ratings = CardRating.values();
        for (CardRating rating : ratings) {
            for (CardSuit suit : CardSuit.values()) {
                add(new Card(rating, suit, isOpen));
            }
        }
    }

    public void clear(){
        cards = new Card[0];
    }

    //перемешать все карты
    public void mix() {
        for (int i = 0; i < cards.length * 2; i++) {
            int num1 = random(cards.length);
            int num2 = random(cards.length);
            Card tmp = cards[num1];
            cards[num1] = cards[num2];
            cards[num2] = tmp;
        }
    }


    //распечатать все карты
    public void print() {
        int row = cards.length;

        if(cards.length > 15) {
            row = cards.length / NUM_COLUMN;
            if(cards.length % NUM_COLUMN != 0) {
                row++;
            }
            if (row < 15) {
                row = 15;
            }

        }

        int i =0;
        while(i < row) {
            String str = "";
            for (int j = 0; j < NUM_COLUMN; j++) {
                int num = i + (j * row) ;

                String info = getCardInfoColor(num);
                if(!info.isEmpty()) {
                    info = String.format("%d.", num + 1);
                    info = String.format("%s %-10s", info, getCardInfoColor(num));
                    info = String.format("%-30s", info);
                    str += info;
                }
            }
            i++;
            System.out.println(str);
        }
        System.out.println();
    }

    private int random(int max) {
        return  (int)(Math.random() * max);
    }

    public String getCardInfoColor(int num) {
        if(num < 0 || num >= cards.length) {
            return "";
        }

        return cards[num].getInfoColor();
    }

    //отдать верхнюю карту
    public Card getTopCard() {
        Card topCard = cards[cards.length - 1];
        del(cards.length - 1);
        return topCard;
    }

    public int getCardsLength() {
        return cards.length;
    }

    public int getPoint() {
        int sum = 0;
        for (Card tmp:cards) {
            sum += tmp.getPoint();
        }
        return  sum;
    }

    public String getPointStr() {
        String str;
        if(isCardsOpen()) {
            str = Integer.toString(getPoint());
        }
        else {
            str = Card.STR_UNKNOWN;
        }
        return  str;
    }

    public String[] getPictureCard(int num) {
        String[] arr = {"",""};
        if(num < 0 || num >= cards.length) {
            return arr;
        }
        return cards[num].getColorPictureCard();
    }

    public boolean isCardsOpen() {
        for (Card tmp : cards) {
            if(!tmp.isOpen()) {
                return false;
            }
        }
        return true;
    }

    //открыть все карты
    public void cardsOpen() {
        for (Card card : cards) {
            card.setStateOpen(true);
        }
    }

    //возвращает скрытую карту
    public Card getHiddenCard() {
        for (Card card : cards) {
            if(!card.isOpen()) {
                return card;
            }
        }
        return null;
    }

    //блэкджек: 21 и только две карты
    public boolean isBlackJack() {
        return (getPoint() == 21) && (getCardsLength() == 2);
    }

    //количество очков в открытых картах
    public int getPointOpenCard() {
        int point = 0;
        for (Card card : cards) {
            if(card.isOpen()) {
                point += card.getPoint();
            }
        }
        return point;
    }

}


