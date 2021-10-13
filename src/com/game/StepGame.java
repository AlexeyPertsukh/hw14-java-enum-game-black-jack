package com.game;
//шаг в программе
//разные шаги - разное действие
public class StepGame {

    private enum Step {
        START,
        INPUT_BET,              //ввод ставок
        FIRST_DISTRIB_CARDS,    //начальная раздача карт
        GAME,                   //игра
        END;                    //конец игры
    };

    private int cntStep;

    public StepGame() {

    }

    public void reset(){
        cntStep = 0;
    }

    public void inc(){
        if(cntStep < Step.END.ordinal()) {
            cntStep++;
        }
    }

    public boolean isInit() {
        return (Step.START.ordinal() == cntStep);
    }

    public boolean isInputBet() {
        return (Step.INPUT_BET.ordinal() == cntStep);
    }

    public boolean isFirstDistribCards() {
        return (Step.FIRST_DISTRIB_CARDS.ordinal() == cntStep);
    }

    public boolean isGame() {
        return (Step.GAME.ordinal() == cntStep);
    }

    public boolean isEnd() {
        return (Step.END.ordinal() == cntStep);
    }

    public int getCntStep() {
        return cntStep;
    }
}
