/*
Термины блэкджек
https://clck.ru/TtMe4

Правила блекджек:
википедия, правила описаны мутно
"https://clck.ru/TptCy
тут правила понятнее изложены
https://pythonru.com/primery/igraem-v-bljekdzhek-na-python
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Help {
    private final static String GIT_URL = "https://github.com/AlexeyPertsukh/hw14-java-enum-game-black-jack";

    private int numPage;
    private String color;
    boolean end;

    private final Command cmdPagePrev;
    private final Command cmdPageNext;
    private final Command cmdReturnToGame;

    private final static String FILENAME = "\\src\\BlackjackRules.txt";

    private final static String[] HEADER1 = {
            "              ┌--------------------------------------------------------------------------",
            "  Общее       │  Правила                                      ",
            "--------------┘---------------------------------------------------------------------------",
    };

    private final static String[] HEADER2 = {
            "--------------┐                                                 ",
            "  Общее       │  Правила                                      ",
            "--------------└----------------------------------------------------------------------------",
    };

    private final static String[][] HEADERS = {HEADER1, HEADER2};

    // СТРАНИЦЫ
    private final static String[] PAGE1 = {
        "Блэкджек " + Const.VERSION,
            Const.COPYRIGHT,
            Const.AUTHOR,
        "-----------------",
        "Базовый (американский) вариант игры: с одной открытой картой дилера и одной скрытой (hole card)",
        "-----------------",
        "Дополнительные команды: ",
            Command.PRINT_ALL_CARDS.info(),
         "-----------------",
         "Чит-коды:",
            Command.PRINT_SHOE_CARDS.info(),
            Command.CHEAT_SHOW_DEALER_POINTS.info(),
            Command.CHEAT_DEALER_HIDDEN_BLACK_JACK.info(),
            Command.CHEAT_CARD_DEL.info(),
            Const.CMD_CHEAT_CARD_ADD + "XX добавить текущему игроку карту заданного номинала случайной масти,",
         "      где XX - номинал карты (XX = 2... 10, J - валет, Q - дама, K - король, A - туз)",
         "      напр.: addQ - добавить даму",

         "-----------------",
         "Термины: ",
         "Шуз (“shoe” - “туфля“) - устройство для хранения нескольких колод карт",
         "     в блэкджеке принято использовать до 8 колод карт в шузе",
         "     •Single Deck Blackjack (блэк джек одной колодой) - игра в блэкджек одной колодой в 52 карты.",
         "     •Six Deck Blackjack (блэкджек шестью колодами) - игра в блэкджек с помощью 6 колод, ",
         "      это наиболее часто встречающаяся версия блэкджека.",
         "Блэкджэк - две карты(только две!), которые в сумме дают 21 очко",
    };

    // СТРАНИЦЫ
    private final static String[] PAGE2 = {
    "",
    };

    private final static String[][] PAGES = {PAGE1, PAGE2};

    public Help(){
        cmdPagePrev = Command.PAGE_PREVIEW;
        cmdPageNext = Command.PAGE_NEXT;
        cmdReturnToGame = Command.RETURN_TO_GAME;
    }

    private void printFooter() {
        System.out.println();
        My.setTextColor(Const.COLOR_HELP);
        My.printArr(HEADERS[numPage]);
        System.out.println(GIT_URL);
        System.out.println();
        My.resetTextColor();
    };

    private  void printHeader() {
        My.setTextColor(Const.COLOR_HELP);
        System.out.println("-------------------------------------------------------------------------------------------");
        My.resetTextColor();
    };

    public void printPage() {
        My.setTextColor(Const.COLOR_HELP);
        My.printArr(PAGES[numPage]);
        if(numPage == 1) {
            printFromFile(FILENAME);
        }
        My.resetTextColor();
    }


    private void printFromFile(String filename) {

        String path = new java.io.File(".").getAbsolutePath();
        filename = path + filename;

        try(FileReader reader = new FileReader(filename))
        {
            FileReader fr= new FileReader(filename);
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
            System.out.println();
            System.out.println(filename);
            fr.close();
        }
        catch(IOException ex){

            System.out.println("Ошибка при открытии файла " + filename);
        }

    }

    public void nextPage() {
        numPage++;
        if(numPage >= PAGES.length) {
            numPage = 0;
        }
    }

    public void prevPage() {
        numPage--;
        if(numPage < 0) {
            numPage = PAGES.length - 1;
        }
    }

    public void show() {
        do {
            printHeader();
            printPage();
            printFooter();
            processCommand();
        }while(!end);
    }

    private void processCommand() {
        String cmd;
        boolean endInput;
        Scanner sc = new Scanner(System.in);

        do {

            endInput = true;
            System.out.printf("Введите команду (%s%s перейти на страницу, %s вернуться в игру): ", cmdPagePrev.getKey(), cmdPageNext.getKey(), cmdReturnToGame.getKey());
            cmd = sc.next();

            //следующая страница
            if(cmd.equalsIgnoreCase(cmdPageNext.getKey())) {
                nextPage();
                System.out.println();
            }
            //предыдущая страница
            else if(cmd.equalsIgnoreCase(cmdPagePrev.getKey())) {
                prevPage();
                System.out.println();
            }
            //вернуться в игру
            else if(cmd.equalsIgnoreCase(cmdReturnToGame.getKey())) {
                end = true;
                System.out.println();
            }
            //ничего из вышеперечисленного- повторный ввод команды
            else {
                endInput = false;
            }

        }while(!endInput);
    }

}
