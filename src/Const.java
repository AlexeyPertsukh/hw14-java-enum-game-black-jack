//основные константы
public final class Const {
    public static final String NAME_GAME = "JAVA BLACKJACK";
    public static final String VERSION = "1.10";
    public static final String COPYRIGHT = "JAVA 01 \"ШАГ\", Запорожье 2021 ";
    public static final String AUTHOR = "Перцух Алексей";

    //основные цвета в программе
    public static final String COLOR_HEADER = My.ANSI_BLUE;
    public static final String COLOR_HELP = My.ANSI_BLUE;
    public static final String COLOR_FOCUS = My.ANSI_YELLOW;
    public static final String COLOR_ALARM = My.ANSI_RED;


    //коеф. выигрышей
    public static final double COEF_WIN_BLACK_JACK = 1.5;        //у игрока блекджек, у дилера нет
    public static final double BASIC_COEF_WIN = 1;              //обычный выигрышный коеф.

    public static final String STR_CHEAT_OK = ";)"; //подтверждение приема чит-команды

    //комментарии ботов
    public static final String TELL_TAKE_CARD = "еще";
    public static final String TELL_PASS = "хватит";
    public static final String TELL_OPEN_CARD = "открываю карту";


    public static final String CMD_CHEAT_CARD_ADD = "add";


    public static final String FORMAT_PRINT = "%-30s";

    //
    public static final double START_MONEY = 50;

    public static final int SHOE_DECK_MIN = 1;
    public static final int SHOE_DECK_MAX = 8;

    public static final int PLAYERS_MIN = 1;
    public static final int PLAYERS_MAX = 5;

    public static final int BOOTS_MIN = 0;
    public static final int BOOTS_MAX = 5;
    public static final int MAX_NAME_LENGTH = 14;


    //по умолчанию
    public static final int DEF_PLAYERS = 1;
    public static final int DEF_BOOTS = 0;
    public static final int DEF_SHOE = 6;


    //пауза в игре
    public static final int PAUSE = 2000;
    public static final boolean PAUSE_ON = true;

    //
    public static final String SKULL = "💀";

    //
    private Const() {
    }
}
