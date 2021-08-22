/*
Правила игры:
https://clck.ru/TptCy

https://durbetsel.ru/2_blackjack_klassichesky.htm
*/

import java.util.Scanner;

public class Game {

    private Help help;
    private Player[] players;
    private Dealer dealer;
    private Player focusPlayer;
    private StepGame step;

    //команды
    public Command cmdHelp;
    public Command cmdTakeCard;
    public Command cmdPause;
    public Command cmdPass;
    public Command cmdTakeWin;
    public Command cmdSurrender;
    public Command cmdGameOver;
    public Command cmdRenamePlayer;
    public Command cmdPrintAllCards;
    public Command cmdPrintShoe;
    public Command cmdCardDel;
    public Command cmdShowDealerPoint;
    public Command cmdDealerOpenCard;
    public Command cmdDealerHiddenBlackJack;
    public Command[] commands;

    private boolean pauseState;
    private boolean gameOver;
    private String strCommand;
    private int numDeckShoe;        //колоды карт в шузе



    private StorageCard shoe;   //шуз- одна или несколько колод карт в игре

    private Scanner sc;

    public Game() {
        help = new Help();
        sc = new Scanner(System.in);
        pauseState = Const.PAUSE_ON;

        cmdHelp = Command.HELP;
        cmdPause = Command.PAUSE;
        cmdTakeCard = Command.TAKE_CARD;
        cmdPass = Command.PASS ;
        cmdTakeWin = Command.TAKE_WIN;
        cmdSurrender = Command.SURRENDER;
        cmdGameOver =  Command.GAME_OVER;
        cmdRenamePlayer = Command.RENAME_PLAYER;
        cmdPrintAllCards = Command.PRINT_ALL_CARDS;
        cmdPrintShoe = Command.PRINT_SHOE_CARDS;
        cmdCardDel = Command.CHEAT_CARD_DEL;
        cmdShowDealerPoint = Command.CHEAT_SHOW_DEALER_POINTS;
        cmdDealerOpenCard = Command.CHEAT_DEALER_OPEN_CARD;
        cmdDealerHiddenBlackJack =  Command.CHEAT_DEALER_HIDDEN_BLACK_JACK;
        commands = new Command[] {cmdHelp, cmdPause, cmdTakeCard, cmdPass, cmdTakeWin, cmdSurrender, cmdRenamePlayer, cmdGameOver};

        step = new StepGame();
    }

    //======================= ОСНОВНОЙ МЕТОД ===========================================================================
    public void go() {
        String cmd;

        printOnStart();
        initPlayers();      //игроки
        inputConfigShoe();  //колоды карт в шузе
        do {
//            test(true); //автоматическое тестирование
            gameReset();

            System.out.println();
            setResolutionCommands();    //разрешение на использование команд
            printHeader();
            do {
                gameSteps();                //шаги(этапы) игры
                setResolutionCommands();    //разрешение на использование команд

                if (!step.isInputBet()) {
                    printHeader();
                }

                // если этап ввода ставок или самой игры - вводим команды и обрабатываем их
                if (step.isInputBet() || step.isGame()) {
                    inputCommand();         //ввод команд
                    processCommand();       //обработка введенных команд
                }

                //автоматические действия
                automaticActions();

                if (gameOver) {
                    break;
                }
            } while (!step.isEnd());

            if(!gameOver) {
                gameOver = !againGame(); //продолжим играть?
            }

        } while(!gameOver);

        printOnEnd();
    }
    //==================================================================================================================

    private void automaticActions() {
        //первоначальная раздача карт
        if(step.isFirstDistribCards()) {
            takeCardFirst();
        }

        //кто-то взял карты, нужна проверка на проигрыш
        if(needPlayersCheck()) {
            //проверка на проигрыш  - каждого, сразу после того, как он взял карты
            checkLoose();
        }

        //у кого-то из игроков блекджек и у дилера точно не будет блекджека- ставим сразу игроку выигрыш
        if(needPlayersCheckBlackJack() && !dealer.isPossibleBlackJack()) {
            checkPlayersBlackJack();
        }

        //у дилера все карты открыты- проверяем, есть ли блекджек у игроков
        if(dealer.isCardsOpen()) {
            if(needPlayersCheckBlackJack()) {
                checkPlayersBlackJack();
            }
        }

        //дилер набрал нужные очки - подводим итоги
        if(dealer.isPointEnough()){
            checkGameStatus();
        }

        //если кого-то вывели из игры и он в фокусе - переводим фокус дальше
        if(!allPlayersOutGame()) {
            if(!focusPlayer.isInGame() && focusPlayer != dealer) {
                nextFocus();
            }
        }

        //конец игры?
        if(step.isEnd()) {
            System.out.println("РАУНД ОКОНЧЕН");
            System.out.println("---------------");
            delPlayerWithoutMoney();    //убираем из казино игроков без денег
            if(allPlayersWithoutMoney()) {
                System.out.println("Все игроки остались без денег, игра окончена! ");
                gameOver = true;
            }
        }
    }

    //проверяем на проигрыш и выигрыш
    private void checkResult(){


        //если нужно проверять дилера- значит все взяли карты и можно проверить всех и на проигрыши и на выигрыши
        if(dealer.needCheck()) {
            checkGameStatus();
        }
    }

    //
    private void gameSteps() {
        //шаг 0 - старт
      if(step.isInit()) {
          setFirstFocus();
          step.inc();
      }

        //шаг 1 - ввод ставок
      if(step.isInputBet()) {
          if(allPlayersSetBet()) {
              step.inc();
          }
      }

        // шаг 2 - первоначальная раздача карт
        if(step.isFirstDistribCards() && allPlayersHaveCards()) {
            step.inc();
            setFirstFocus();
        }

        //шаг 3 - игра
        if(step.isGame() && allPlayersOutGame()) {
            step.inc();
        }

        //шаг 4 - игра окончена
    }

    //будем продолжать игру?
    private boolean againGame() {
        char ch = My.nextCharLowerCase("Продолжить игру? (Y - да, N - нет): " , 'y', 'n');
        if(ch == 'y') {
            return true;
        }
        else {
         return false;
        }

    }


    //все игроки сделали ставки?
    private boolean allPlayersSetBet() {
        for (Player player : players) {
            if(player != dealer && !player.isSetBet()) {
                return false;
            }
        }
        return true;
    }

    private boolean allPlayersHaveCards() {
        for (Player player : players) {
            if(player.isNoCards()) {
                return false;
            }
        }
        return true;
    }


    private void printOnStart() {
        System.out.println(Const.NAME_GAME + "  " + Const.VERSION);
        Picture.printSmallGameLogo();
        System.out.println();
    }

    private void printOnEnd() {
        System.out.println();
        My.printlnColor("The robot Bender casino says goodbye to you", My.ANSI_PURPLE);
        System.out.println();
        System.out.println(Const.COPYRIGHT);
        System.out.println(Const.AUTHOR);
    }

    private void printHeader() {

        //готовим строку с текстом о доступных командах
        String textLine = "";
        for (Command tmp : commands) {
            if(tmp.isVisible() && tmp.isActive()) {    //печатаем инфо о команда, только если она активная и видимая
                textLine += tmp.info() + "   ";
            }
        }
        
        My.setTextColor(Const.COLOR_HEADER);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(textLine);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        My.resetTextColor();
        printStateGame();
    }


    //инициализация игроков
    private void initPlayers() {
        String text = String.format("Количество игроков (%d-%d, %d по умолчанию): ", Const.PLAYERS_MIN, Const.PLAYERS_MAX, Const.DEF_PLAYERS);
        int numPlayers = My.nextInt(text, Const.PLAYERS_MIN, Const.PLAYERS_MAX, Const.DEF_PLAYERS);

        text = String.format("Количество ботов (%d-%d, %d по умолчанию): ", Const.BOOTS_MIN, Const.BOOTS_MAX, Const.DEF_BOOTS);
        int numBots = My.nextInt(text, Const.BOOTS_MIN, Const.BOOTS_MAX, Const.DEF_BOOTS);

        players = new Player[numPlayers + numBots + 1];
        for (int i = 0; i < players.length - 1; i++) {
            if(i < numPlayers) {
                players[i] = new Player("Игрок" + (i + 1), Const.START_MONEY);
            }
            else {
                players[i] = new Bot("Игрок" + (i + 1) + "[" + "БОТ" + "]", Const.START_MONEY);
            }
            players[i].gameOn();
        }
        dealer = new Dealer();
        dealer.gameOn();
        players[players.length - 1] = dealer;
    }

    //сброс параметров для повторной игры
    private void gameReset() {
        initShoe();         //шуз
        playersReset();
        step.reset();
        focusPlayer = null;

    }


    private void initShoe() {
        shoe = new StorageCard();
        for (int i = 0; i < numDeckShoe; i++) {
            shoe.addFullDeck(Card.OPEN);
        }

        shoe.mix();
    }

    private void inputConfigShoe() {
        String textShoe = String.format("Количество колод карт в шузе (%d-%d, %d по умолчанию): ", Const.SHOE_DECK_MIN, Const.SHOE_DECK_MAX, Const.DEF_SHOE);
        numDeckShoe = My.nextInt(textShoe, Const.SHOE_DECK_MIN, Const.SHOE_DECK_MAX, Const.DEF_SHOE);
    }




    //фокус на игрока
    private void setFocus(int num) {
        focusPlayer = players[num];
    }

    private void setFirstFocus() {
        for (int i = 0; i < players.length; i++) {
            if( players[i].isInGame()) {
                setFocus(i);
                break;
            }
        }

    }

    //фокус на следующего игрока по порядку
    private void nextFocus() {
        int num = -1;
        for (int i = 0; i < players.length; i++)
        {
            if(focusPlayer == players[i]) {
                num = i;
                break;
            }
        }

        do
        {
            num++;
            if(num >= players.length) {
                num = 0;
            }
        } while(!players[num].isInGame());

        setFocus(num);

    }


    //ввод команд
    private void inputCommand()  {
        if(step.isInputBet()) { //если ввод ставок, но вместо ставки можно ввести и разрешенные команды
            System.out.print(focusPlayer.getName()+ ", введите ставку: " );
            strCommand = focusPlayer.nextBet(sc);
        }
        else {                  //если это не ввод ставок- то только ввод команд
            System.out.print(focusPlayer.getName()+ ", введите команду: " );
            strCommand = focusPlayer.nextCmd(sc);
        }

        //если это бот - рисуем паузу
        if(focusPlayer instanceof Bot) {
            My.sleepAnimationLn(Const.PAUSE , pauseState);
        }
    }

    //обработка команд
    private boolean processCommand()  {

        //сложные составные команды
        //чит: добавить игроку карту определенного номинала
        if(addCard(focusPlayer, strCommand)) {
            return true;
        }

        //если ввод ставок
        if(step.isInputBet()) {
            if(My.isDouble(strCommand)) {
                inputBet(focusPlayer, strCommand);
                return true;
            }
        }

        //основные команды
        Command command;

        //пауза
        command = cmdPause;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            pauseSwitch();
            return true;
        }


        //хелп
        command = cmdHelp;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            help.show();
            return true;
        }

        //взять карту
        command = cmdTakeCard;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            takeCards(focusPlayer, 1);
            return true;
        }

        //сдаться
        command = cmdSurrender;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            surrender(focusPlayer);
            return true;
        }


        //пас
        command = cmdPass;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            nextFocus();
            return true;
        }

        //досрочно взять выигрыш при блекджеке
        command = cmdTakeWin;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            takeWinBlackJack(focusPlayer);
            return true;
        }

        //переименовать игрока
        command = cmdRenamePlayer;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            renamePlayer(focusPlayer);
            return true;
        }

        //выйти из игры
        command = cmdGameOver;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            gameOver = true;
            return true;
        }

        //открыть скрытую карту дилера
        command = cmdDealerOpenCard;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive() && focusPlayer == dealer) {
            openCardDealer();
            return true;
        }

        // установить дилеру скрытый блекджек
        command = cmdDealerHiddenBlackJack;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            dealerHiddenBlackJack();
            return true;
        }


        //распечатать все номиналы карт
        command = cmdPrintAllCards;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            printAllCards();
            return true;
        }

        //чит: распечатать все карты в шузе
        command = cmdPrintShoe;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            printShoe();
            return true;
        }

        //чит: распечатать все карты в шузе
        command = cmdShowDealerPoint;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            My.printlnColor("очки дилера: " + dealer.getPoint(), Const.COLOR_HELP);
            return true;
        }


                //чит: удалить карту у игрока
        command = cmdCardDel;
        if(My.cmpStr(strCommand, command.getKey()) && command.isActive()) {
            delCard(focusPlayer);
            return true;
        }
        //если дошли сюда- команда не введена или введена с ошибкой, повторяем ввод
        My.printlnColor("неправильная команда, повторите еще раз", Const.COLOR_HELP);
        return false;
    }

    //разрешение для использования команд
    private void setResolutionCommands() {
        cmdTakeCard.activation(step.isGame());
        cmdPass.activation(step.isGame());
        cmdSurrender.activation(step.isGame());
        cmdTakeWin.activation(step.isGame());
        cmdDealerHiddenBlackJack.activation(step.isGame());
    }

    //распечатать карты в шузе
    private void printShoe() {
        System.out.println();
        System.out.println("Карты в шузе");
        System.out.println("------------");
        shoe.print();
    }

    //распечатать карты всех номиналов
    private void printAllCards() {

        My.printlnColor("Карты всех номиналов:", Const.COLOR_HELP);
//        System.out.println("------------");
        Picture.printAllCardPic();
    }

    //игрок сдался
    private void surrender(Player player) {
        if(player.getCardLength()!= 2) {
            My.printlnColor("Сдаться могут только игроки с двумя картами на руках. А вы нет.", Const.COLOR_HELP);
            My.sleepAnimationLn(Const.PAUSE , pauseState);
            return;
        }
        player.surrender();
        My.printlnColor(player.getName() + " сдался", Const.COLOR_HELP);
        My.sleepAnimationLn(Const.PAUSE, pauseState);
        nextFocus();
    }

    //первоначальная раздача карт
    public void takeCardFirst() {
        My.printlnColor("Дилер сдает карты", Const.COLOR_HELP);
        My.sleepAnimationLn(Const.PAUSE, pauseState);
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if(player == dealer)   //дилеру раздаем 2 карты: открытую и закрытую
            {
                takeCards(player, 2, Card.OPEN, Card.HIDDEN);
            }
            else {
                takeCards(player, 2);       //игрокам даем две открытые карты
            }
        }

    }

    // раздача карт игроку- можно дать сразу несколько карт
    // после того, как игрок взял карты, проверяем на перебор (тогда игрок проиграл)
    public void takeCards(Player player, int num, boolean... opened )  {
        boolean isStateOpen = true;   //видимость карты при раздаче
        Card[] cards = new Card[num];
        String[][] pics = new String[num][];

        for (int i = 0; i < num; i++) {
            if(i < opened.length) {
                isStateOpen = opened[i];
            }

            //если в шузе пусто- подкидываем случайную карту, что бы программа не вылетела
            if(shoe.getCardsLength() <= 0) {
               shoe.addRandom();
            }

            cards[i] = shoe.getTopCard();

            cards[i].setStateOpen(isStateOpen);
            player.addCard(cards[i]);
            pics[i] = cards[i].getColorPictureCard();
        }

        System.out.printf("%s получил карты:\n", player.getName());
        My.printArr(pics);      // печатаем красивые картинки с картами

        My.sleepAnimationLn((int)(Const.PAUSE * 1.5), pauseState);

    }


    private void openCardDealer() {
        if(dealer.isCardsOpen()) {
            return;
        }

        My.printlnColor("Дилер открывает скрытую карту", Const.COLOR_HELP);
        Card card = dealer.getHiddenCard();
        card.setStateOpen(true);
        My.printArr(card.getColorPictureCard());
        My.sleepAnimationLn(Const.PAUSE, pauseState);

    }


    private void printStateGame() {
        System.out.println();

        printStateGameNames();    //имена
        printStateGameMoney();
        printStateGameBet();
        printStateGameMoneyWin();   //выигрыш
        printStateGamePoint();      //очки
        printStateGameUnderline("---------------");     //подчеркивание
        printStateGameCards();      //карты на руках играков
        System.out.println();
    }

    //подчеркивание
    public void printStateGameUnderline(String strUnderline) {
        String text = "";
        String[] str = new String[players.length];
        text = "";
        for (int i = 0; i < players.length; i++) {
            str[i] = strUnderline;
            if(players[i] == focusPlayer) {
                str[i] = Const.COLOR_FOCUS + str[i] + My.ANSI_RESET;
            }
            str[i] = String.format(Const.FORMAT_PRINT, str[i]);
            text += My.formatedStrInvisChar(str[i]);
        }
        System.out.println(text);
    }

    //имена
    public void printStateGameNames() {
        String text = "";
        String[] str = new String[players.length];
        text = "";
        for (int i = 0; i < players.length; i++) {
            str[i] = players[i].getName() + " " + players[i].getTextGameState();
            if(players[i] == focusPlayer) {
                str[i] = Const.COLOR_FOCUS + str[i] + My.ANSI_RESET;
            }
            str[i] = String.format(Const.FORMAT_PRINT, str[i]);
            text += My.formatedStrInvisChar(str[i]);
        }
        System.out.println(text);
    }

    //печать карт
    public void printStateGameCards() {
        //карты на руках
        int max = 1;
        String text = "";
        String[] str = new String[players.length];

        for (Player tmp : players) {
            if(tmp.getCardLength() > max) {
                max = tmp.getCardLength();
            }
        }
        for (int n = 0; n < max; n++) {
            text = "";
            for (int i = 0; i < players.length; i++) {

                str[i] = players[i].getCardInfoColor(n);
                if ((n == 0) && players[i].isNoCards()) {
                    str[i] = "нет карт";
                }
                str[i] = String.format(Const.FORMAT_PRINT, str[i]);
                text += My.formatedStrInvisChar(str[i]);
            }
            System.out.println(text);
        }
    }

    //печать очков
    public void printStateGamePoint() {
        //Очки
        String text = "";
        String[] str = new String[players.length];
        for (int i = 0; i < players.length; i++) {
                str[i] = "Очки:  " + players[i].getPointStr();
            if(players[i] == focusPlayer) {
                str[i] = Const.COLOR_FOCUS + str[i] + My.ANSI_RESET;
            }
            str[i] = String.format(Const.FORMAT_PRINT, str[i]);
            text += My.formatedStrInvisChar(str[i]);
        }
        System.out.println(text);
//        System.out.println();
    }



    //печать денег
    public void printStateGameMoney() {
        String text = "";
        String[] str = new String[players.length];
        for (int i = 0; i < players.length; i++) {

            if(players[i] == dealer) {
                str[i] = "-";
            } else {
                str[i] = "Деньги: " + players[i].getMoney() + " $";
            }

            if(players[i] == focusPlayer) {
                str[i] = Const.COLOR_FOCUS + str[i] + My.ANSI_RESET;
            }
            str[i] = String.format(Const.FORMAT_PRINT, str[i]);
            text += My.formatedStrInvisChar(str[i]);
        }
        System.out.println(text);
    }

    //печать ставки
    public void printStateGameBet() {
        String text = "";
        String[] str = new String[players.length];
        for (int i = 0; i < players.length; i++) {

            if(players[i] == dealer) {
                str[i] = "-";
            } else {
                str[i] = "Ставка: " + players[i].getBet() + " $";
            }

            if(players[i] == focusPlayer) {
                str[i] = Const.COLOR_FOCUS + str[i] + My.ANSI_RESET;
            }
            str[i] = String.format(Const.FORMAT_PRINT, str[i]);
            text += My.formatedStrInvisChar(str[i]);
        }
        System.out.println(text);
    }

    //печать ставки
    public void printStateGameMoneyWin() {
        String text = "";
        String[] str = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            str[i] = "-";
            if(players[i] == dealer) {
                str[i] = "-";
            } else {
                str[i] = String.format("Выигрыш: %.1f $", players[i].getMoneyWin());
            }

            if(players[i] == focusPlayer) {
                str[i] = Const.COLOR_FOCUS + str[i] + My.ANSI_RESET;
            }
            str[i] = String.format(Const.FORMAT_PRINT, str[i]);
            text += My.formatedStrInvisChar(str[i]);
        }
        System.out.println(text);
    }


    private boolean inputBet(Player player, String strBet) {

        double bet = Double.parseDouble(strBet);
        if(bet > player.getMoney()) {
            My.printlnColor("у вас нет столько денег, попробуйте еще раз", Const.COLOR_HELP);
            return false;
        }
        if(bet < 1) {
            My.printlnColor("вы не можете поставить такую ставку, попробуйте еще раз", Const.COLOR_HELP);
            return false;
        }

        //если все нормально- записываем ставку
        player.setBet(bet);
        nextFocus();
        return true;
    }


    public void pauseSwitch() {
        pauseState = !pauseState;
        String str = (pauseState) ? "пауза включена" : "пауза отключена";
        My.printlnColor(str, Const.COLOR_HELP);

    }

    public boolean isPauseState() {
        return pauseState;
    }

    //проверка игроков: проигравшие
    private void checkLoose() {
        for (Player player : players) {
            if(player.needCheck()) {
                checkPlayerLoose(player);
                player.checked();
            }
        }
    }

    private void checkPlayerLoose(Player player) {
        if(player.getPoint() > 21) {
            player.gameLoose();
        }

    }

    //проверка игроков на блекджек- вызывать, как только дилер открыл обе свои карты
    private void checkPlayersBlackJack() {
        for (Player player : players) {
            if(player.isInGame() && player != dealer && player.isBlackJack()) {

                    if(!dealer.isBlackJack()) {     // у дилера не блекджек- самый большой выигрыш
                        player.gameWin(Const.COEF_WIN_BLACK_JACK);
                    }
                    else {                          // у дилера тоже блекджек - ничья
                        player.gamePush();
                    }
            }
        }
    }


    //проверка выиграшей
    private void checkGameStatus() {
        if(dealer.isBlackJack()) {   //дилер выиграл !!!! добавить - все остальные проиграли
            dealer.gameWin(0);
        }

        //проходим по кажому игроку и определяем, выиграл ли он
        for (Player player : players) {
            if(player.isInGame() && player != dealer) {
                checkGameStatusPlayer(player);
            }
        }
    }

    private void checkGameStatusPlayer(Player player) {

        //дилер проиграл - игрок выиграл
        if(dealer.isLoose()) {
            player.gameWin(Const.BASIC_COEF_WIN);
            return;
        }

        //дилер набрал заданное количество очков - сравниваем результаты
        if(dealer.isPointEnough()) {
            if(player.getPoint() > dealer.getPoint())   //игрок обыграл дилера
            {
                player.gameWin(Const.BASIC_COEF_WIN);
            }
            else if(player.getPoint() == dealer.getPoint())     //ничья с дилером
            {
                player.gamePush();
            }
            else    //игрок проиграл дилеру
            {
                player.gameLoose();
            }
        }
    }


    //есть игроки(кроме дилера) в игре?
    private boolean playersInGame() {
        for (Player player : players) {
            if(player != dealer && player.isInGame()) {
                return true;
            }
        }
        return false;
    }


    //======== ЧИТ КОДЫ

    //чит-команда: удалить карту
    private void delCard(Player player) {
        if (player == null) {
            return;
        }

        if(player.delCard()) {
            My.printlnColor(Const.STR_CHEAT_OK, Const.COLOR_HELP);
        }
    }

    //чит-команда: добавить игроку карту заданного номинала
    private boolean addCard(Player player, String cmd) {
        if (player == null) {
            return false;
        }

        String str = My.getStrCmd(cmd, Const.CMD_CHEAT_CARD_ADD);
        if(My.isClearStr(str)) {        //если вернулась пустая строка- то cmd не является нужной нам командой, выходим
            return false;
        }

        for (CardRating cardRating: CardRating.values()) {
            String shortName = cardRating.getShortName();
            if(My.cmpStr(shortName, str)) {
                CardSuit[] arrSuit = CardSuit.values();
                CardSuit cardSuit = arrSuit[My.random(arrSuit.length)];     //случайная масть

                player.addCard(new Card(cardRating, cardSuit));

                My.printlnColor(Const.STR_CHEAT_OK, Const.COLOR_HELP);

                return true;
            }
        }
        return false;
    }


    //все игроки отыгрались?
    private boolean allPlayersOutGame() {
        for (Player player : players) {
            if(player != dealer && player.isInGame()) {
                return false;
            }
        }
        return true;
    }

    //игроки нуждаются в проверке результатов?
    private boolean needPlayersCheck() {
        for (Player player : players) {
            if(player.needCheck()) {
                return true;
            }
        }
        return false;
    }


    //у кого-то из игроков есть блэкджек и он еще в игре?
    private boolean needPlayersCheckBlackJack() {
        for (Player player : players) {
            if(player.isBlackJack() && player.isInGame()) {
                return true;
            }
        }
        return false;
    }


    private void playersReset() {
        for (Player player : players) {
            player.reset();
        }
    }


    //удалить игрока из массива
    private void delPlayer(Player player) {
        int num = -1;

        for (int i = 0; i < players.length; i++) {
            if(players[i] == player) {
                num = i;
                break;
            }
        }

        if(num < 0) {
            return;
        }

        Player[] tmp = new Player[players.length - 1];
        for (int i = 0; i < tmp.length; i++) {
            if(i < num) {
                tmp[i] = players[i];
            }
            else {
                tmp[i] = players[i + 1];
            }
        }
        players = tmp;
    }

    //удаляем из казино игроков, у которых нет денег
    private void delPlayerWithoutMoney() {

        for (int i = 0; i < players.length; i++) {
            if(players[i].getMoney() < 1 && players[i] != dealer)
            {
                My.printlnColor("💀 " + players[i].getName() + " проиграл все деньги и покидает казино", Const.COLOR_ALARM);
                delPlayer(players[i]);
                i--;
            }
        }
    }

    //все игроки остались без денег?
    private boolean allPlayersWithoutMoney() {
        for (Player tmp : players) {
            if(tmp!= dealer && tmp.getMoney() >= 1) {
                return false;
            }
        }
        return true;
    }

    //тестирование игры: 5 ботов, без паузы
    private void test(boolean testState) {
        if (!testState) {
            return;
        }

        players = new Player[6];
        for (int i = 0; i < players.length - 1; i++) {
            players[i] = new Bot("Игрок" + (i + 1) + "[" + "БОТ" + "]", Const.START_MONEY);
            players[i].gameOn();
        }
        dealer = new Dealer();
        dealer.gameOn();
        players[players.length - 1] = dealer;

        pauseState = false;
    }

    //переименовать юзера
    private void renamePlayer(Player player) {
        System.out.printf("Введите новое имя вместо \"%s\" (до %d символов): ", player.getName(), Const.MAX_NAME_LENGTH);
        String name = sc.next();
        if(name.length() > Const.MAX_NAME_LENGTH) {
            name = name.substring(0,Const.MAX_NAME_LENGTH);
        }
        player.setName(name);
    }

    //взять выигрыш - только есть у игрока блекджек, а у дилера закрыта карта, но может быть блекджек
    private void takeWinBlackJack(Player player) {
        if(!player.isBlackJack()) {
            My.printlnColor(player.getName() + ", досрочно выигрыш могут взять только игроки с блэкджеком. А вы нет. ", Const.COLOR_HELP);
            My.sleepAnimationLn(Const.PAUSE * 2, pauseState);
            return;
        }
        My.printlnColor(player.getName() + ", у вас блэкджек и вы досрочно взяли выигрыш с коефициентом 1:1", Const.COLOR_HELP);
        player.gameWin(Const.BASIC_COEF_WIN);
        My.sleepAnimationLn(Const.PAUSE * 2, pauseState);
    }

    //установить дилеру скрытый блекджек
    private void dealerHiddenBlackJack() {
        dealer.clearCard();

        Card card = new Card(CardRating.ACE, CardSuit.SPADES, Card.OPEN);
        dealer.addCard(card);

        card = new Card(CardRating.JACK, CardSuit.CLUBS, Card.HIDDEN);
        dealer.addCard(card);

        My.printlnColor(Const.STR_CHEAT_OK, Const.COLOR_HELP);
        My.sleepAnimationLn(Const.PAUSE, pauseState);
    }

}
