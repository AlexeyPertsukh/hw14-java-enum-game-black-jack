public enum Command {
    HELP("помощь", "?"),
    TAKE_CARD("взять карту","@"),
    PASS("пропустить ход","#"),
    TAKE_WIN("взять выигрыш","*"),
    SURRENDER("сдаться","S"),
    GAME_OVER("выйти из игры","END"),
    PAUSE("пауза вкл/откл","~"),
    RENAME_PLAYER("переименовать игрока","R"),

    //для хелпа
    PAGE_PREVIEW("предыдущая страница", "<"),
    PAGE_NEXT("следующая страница", ">"),
    RETURN_TO_GAME("вернуться в игру", "G" ),

    //"невидимые" для печати команды - читерские, дополнительные и т.д.
    PRINT_ALL_CARDS("распечатать  изображения всех карт в колоде","%", false),
    PRINT_SHOE_CARDS("показать все карты в шузе", "^", false),
    CHEAT_CARD_DEL("удалить карту игрока","DEL", false),
    CHEAT_SHOW_DEALER_POINTS("подсмотреть очки дилера", "DPOINT", false),
    CHEAT_DEALER_OPEN_CARD("показать скрытую карту дилера", "SHOW_HIDDEN_CARD_DEALER", false),
    CHEAT_DEALER_HIDDEN_BLACK_JACK("установить дилеру 2 карты: открытую и закрытую, которые дают блэкджек", "DHIDBJ", false),
    ;

    private String name;
    private String key;
    private boolean isActive;
    private boolean isVisible;

    Command(String name, String key) {
        this(name, key, true);
    }

    Command(String name, String key, boolean isVisible) {
        this.name = name;
        this.key = key;
        this.isActive = true;
        this.isVisible = isVisible;
    }


    //включение команды
    public void activation(boolean on) {
        isActive = on;
    }

   //команда включена?
    public boolean isActive() {
        return isActive;
    }

    public String info() {
        return key + " " + name;
    }

    public String getKey() {
        return key;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setInvisible() {
        isVisible = false;
    }

}
