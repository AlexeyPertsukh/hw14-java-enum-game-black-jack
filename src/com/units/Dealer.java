package com.units;

import com.game.Command;
import com.game.Const;
import com.game.Color;

import java.util.Scanner;

public class Dealer extends Bot{

    public final static int POINT_ENOUGH = 17;    //очки, при которых дилер не берет карты (правила казино)
    public Dealer() {
        super("Дилер", 1000_000_000);
    }

    @Override
    public String nextCmd(Scanner sc) {
        String cmd;
        String tell;
        if(!isCardsOpen()) {
            cmd = Command.CHEAT_DEALER_OPEN_CARD.getKey();
            tell = Const.TELL_OPEN_CARD;
        }
        else if(!isPointEnough()) {
            cmd = Command.TAKE_CARD.getKey();
            tell = Const.TELL_TAKE_CARD;

        }
        else {
            cmd = Command.PASS.getKey();
            tell = Const.TELL_PASS;
        }

        Color.printlnColor(tell, Const.COLOR_HELP);
        return cmd;
    }

    //очки в открытых картах
    public int getPointOpenCard() {
        return storage.getPointOpenCard();
    }

    //у дилера может быть блекджек, если две карты, одна из них закрыта, а вторая 10 или 11 очков
    public boolean isPossibleBlackJack() {
        return (!isCardsOpen() && (getPointOpenCard() == 10 || getPointOpenCard() == 11));
    }

    public boolean isPointEnough() {
        return (getPoint() >= POINT_ENOUGH && isCardsOpen());
    }



}
