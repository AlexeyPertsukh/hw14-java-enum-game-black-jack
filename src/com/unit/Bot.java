package com.unit;

import com.game.Command;
import com.game.Const;

import java.util.Scanner;

public class Bot extends Player {
    public Bot(String name, double money) {
        super(name, money);
    }

    @Override
    public String nextCmd(Scanner sc) {
        String cmd;
        String tell;
        if(getPoint() < 18) {
            cmd = Command.TAKE_CARD.getKey();
            tell = Const.TELL_TAKE_CARD;
        }
        else {
            cmd = Command.PASS.getKey();
            tell = Const.TELL_PASS;
        }
        System.out.println(tell);
        return cmd;
    }

    @Override
    public String nextBet(Scanner sc) {
        String str = "";
        double bet;
        double money = getMoney();

        if(money >= 50) {
          bet = 25;
        }
        else if(money >= 15) {
            bet = 10;
        }
        else if(money >= 5) {
            bet = 5;
        }
        else {
            bet = money;
        }

        System.out.println(bet);

        return Double.toString(bet);
    }

}

