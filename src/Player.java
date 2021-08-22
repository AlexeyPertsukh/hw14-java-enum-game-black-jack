import java.util.Scanner;

public class Player {
    private String name;
    protected StorageCard storage;
    private double money;
    private double bet;
    private double moneyWin;

    private boolean isLoose;        //проиграл
    private boolean isWin;          //выиграл
    private boolean isSurrender;    //сдался
    private boolean isPush;         //ничья

    private boolean needCheck;


    public Player(String name, double money) {
        this.name = name;
        this.money = money;
        storage = new StorageCard();
    }

    public void reset() {
        isLoose = false;
        isWin = false;
        isSurrender = false;
        isPush = false;
        bet = 0;
        moneyWin = 0;
        storage.clear();
    }

    public void addCard(Card card) {
        storage.add(card);
        needCheck = true;
    }

    public void clearCard() {
        storage.clear();
    }

    public int getPoint() {
        return storage.getPoint();
    }
    public String getPointStr() {
        return storage.getPointStr();
    }

    public String nextCmd(Scanner sc) {
        String cmd = sc.next();
        return cmd;
    }

    public String getName() {
        return name;
    }

    public void printCards() {
        System.out.printf("[%s] карты: \n", name);
        storage.print();
        System.out.println("очки: " + storage.getPoint() );
    }

    public String getCardInfoColor(int num) {
        if(num < 0 || num >= storage.getCardsLength()) {
            return "";
        }
        return storage.getCardInfoColor(num);
    }

    public int getCardLength(){
        return storage.getCardsLength();
    }

    public boolean isNoCards() {
        return (storage.getCardsLength() == 0);
    }

    public double getMoney() {
        return money;
    }

    public double getBet() {
        return bet;
    }

    public double getMoneyWin() {
        return moneyWin;
    }


    public boolean isInGame() {
        boolean inGame = !(isLoose || isWin || isSurrender || isPush);
        return inGame;
    }

    private void addMoney(double money) {
        this.money += money;
        if(this.money < 0) {
            this.money = 0;
        }
    }

    public void gameOn() {
        isWin = false;
        isLoose = false;
        isSurrender = false;
    }


    //выиграл
    public void gameWin(double coefficientWin) {
        addMoneyWin(bet * coefficientWin);
        isWin = true;
    }

    //проиграл
    public void gameLoose() {
        addMoneyWin(-1 * bet);
        isLoose = true;
    }

    //ничья
    public void gamePush() {
        addMoneyWin(0);
        isPush = true;
    }


    //добавляем выигрыш
    private void addMoneyWin(double moneyWin) {
        this.moneyWin = moneyWin;
        addMoney(moneyWin);
    }

    //сдался
    public void surrender() {
        moneyWin = -1 * bet/2;
        addMoney(moneyWin);
        isSurrender = true;
    }

    //стаус в игре
    public String getTextGameState() {
        String str ="";  // в игре
        if(isLoose) {
            if(getPoint() > 21) {
                str = "(перебор)";
            }
            else {
                str = "(проиграл)";
            }

        }
        else if(isWin) {
          if(isBlackJack()) {
              str = "(БЛЭКДЖЭК)";
          }
          else {
             str = "(ВЫИГРАЛ)";
          }
        }
        else
        if(isSurrender) {
            str = "(сдался)";
        }
        else
        if(isPush) {
            str = "(ничья)";
        }
        return str;
    }



    public String[] getPictureCard(int num) {
        return storage.getPictureCard(num);
    }

    public String nextBet(Scanner sc) {
        String str = sc.next();
        return str;
    }

    public boolean isLoose() {
        return isLoose;
    }

    public boolean isWin() {
        return isWin;
    }

    //открыть все карты
    public void cardsOpen() {
        storage.cardsOpen();
    }



    //удалить последнюю карту
    public boolean delCard() {
        if(storage.getCardsLength() > 0) {
            storage.del(storage.getCardsLength() - 1);
            return true;
        }
        return false;
    }

    public boolean needCheck() {
        return (needCheck && isCardsOpen());    //
    }

    public void checked() {
        needCheck = false;
    }

    //сделал ставку?
    public boolean isSetBet() {
        return (bet > 0);
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    //
    public boolean isCardsOpen() {
        return storage.isCardsOpen();
    }

    //Блэкджек на руках
    public boolean isBlackJack() {
        return storage.isBlackJack();
    }


    //возвращает скрытую карту
    public Card getHiddenCard() {
        return storage.getHiddenCard();
    }

    public void setName(String name) {
        this.name = name;
    }

}
