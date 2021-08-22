/*
псевдографика
https://clck.ru/LS5nk

генератор текстов ascii:
http://patorjk.com/software/taag/#p=display&f=Cards&t=Game%20Over

картинки карт
https://www.asciiart.eu/miscellaneous/playing-cards
 */

public class Picture {

    public static final char CHANGE_CHAR = '$';

    public static final String[] ACE = {
            "┌───────────┐",
            "│$ Туз      │",
            "│           │",
            "│     @     │",
            "│   __│__   │",
            "│    │ │    │",
            "│    │ │    │",
            "│     V     │",
            "│           │",
            "│      Туз $│",
            "└───────────┘",
    };

    public static final String[] KING = {
            "┌───────────┐",
            "│$ Король   │",
            "│           │",
            "│  /|/|/|   │",
            "│  ######   │",
            "│ [|O,O |]  │",
            "│  | = /    │",
            "│   vvv     │",
            "│           │",
            "│   Король $│",
            "└───────────┘",
    };

    public static final String[] QUEEN = {
            "┌───────────┐",
            "│$ Дама     │",
            "│           │",
            "│   /===Q   │",
            "│  ││─l─ │  │",
            "│  ││ o .│  │",
            "│     ||    │",
            "│  ─(.)(.)─ │",
            "│           │",
            "│     Дама $│",
            "└───────────┘",
    };

    public static final String[] JACK = {
            "┌───────────┐",
            "│$ Валет    │",
            "│           │",
            "│   ~~~~~~  │",
            "│   |0   |  │",
            "│  /_   З|  │",
            "│   |~   |  │",
            "│   |_/  |  │",
            "│           │",
            "│    Валет $│",
            "└───────────┘",
    };

    public static final String[] TWO = {
            "┌───────────┐",
            "│$ 2        │",
            "│     $     │",
            "│           │",
            "│           │",
            "│           │",
            "│           │",
            "│           │",
            "│     $     │",
            "│       2 $ │",
            "└───────────┘",
    };

    public static final String[] THREE = {
            "┌───────────┐",
            "│$ 3        │",
            "│     $     │",
            "│           │",
            "│           │",
            "│     $     │",
            "│           │",
            "│           │",
            "│     $     │",
            "│        3 $│",
            "└───────────┘",
    };

    public static final String[] FOUR = {
            "┌───────────┐",
            "│$ 4        │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│           │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│        4 $│",
            "└───────────┘",
    };

    public static final String[] FIVE = {
            "┌───────────┐",
            "│$ 5        │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│     $     │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│        5 $│",
            "└───────────┘",
    };


    public static final String[] SIX = {
            "┌───────────┐",
            "│$ 6        │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│        6 $│",
            "└───────────┘",
    };

    public static final String[] SEVEN = {
            "┌───────────┐",
            "│$ 7        │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│  $  $  $  │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│        7 $│",
            "└───────────┘",
    };

    public static final String[] EIGHT = {
            "┌───────────┐",
            "│$ 8        │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│        8 $│",
            "└───────────┘",
    };


    public static final String[] NINE = {
            "┌───────────┐",
            "│$ 9        │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│     $     │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│        9 $│",
            "└───────────┘",
    };


    public static final String[] TEEN = {
            "┌───────────┐",
            "│$ 10       │",
            "│  $     $  │",
            "│     $     │",
            "│  $     $  │",
            "│           │",
            "│  $     $  │",
            "│     $     │",
            "│  $     $  │",
            "│       10 $│",
            "└───────────┘",
    };

    public static final String[] BACK = {
            "┌───────────┐",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "│░░░░░░░░░░░│",
            "└───────────┘",
    };


    public static final String[] CARD_LOGO = {
            ".------. .------. .------. .------. .------. .------. .------. .------. .------.",
            "|B.--. | |L.--. | |A.--. | |C.--. | |K.--. | |J.--. | |A.--. | |C.--. | |K.--. |",
            "| :(): | | :'\\: | | (\\/) | | :/\\: | | :'\\: | | :(): | | (\\/) | | :/\\: | | :'\\: |",
            "| ()() | | (__) | | :\\/: | | :\\/: | | :\\/: | | ()() | | :\\/: | | :\\/: | | :\\/: |",
            "| '--'B| | '--'L| | '--'A| | '--'C| | '--'K| | '--'J| | '--'A| | '--'C| | '--'K|",
            "`------' `------' `------' `------' `------' `------' `------' `------' `------'",
    };


    public static final String[][] CARDS = {BACK, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEEN, JACK, QUEEN, KING, ACE};


    private Picture() {
    }

    public static void printAllCardPic() {
        My.printArr(CARDS[0], CARDS[1], CARDS[2], CARDS[3], CARDS[4]);
        My.printArr(CARDS[5], CARDS[6], CARDS[7], CARDS[8], CARDS[9]);
        My.printArr(CARDS[10], CARDS[11], CARDS[12], CARDS[13]);
    }

    //маленький логотип игры
    public static void printSmallGameLogo() {
        My.printArrColor(CARD_LOGO, 9, My.ANSI_YELLOW, My.ANSI_RED);
    }


    //большой логотип игры
    public static void printBigGameLogo() {
        String[][] arr = new String[][]{QUEEN.clone(), TWO.clone(), ACE.clone(), SEVEN.clone(), KING.clone(),
                                        TEEN.clone(), JACK.clone(), FIVE.clone(), QUEEN.clone(),
                                        };
        char[] newChars = {'*','+', '•', '#','•','*','+','•','#'};
        String text = "BLACKJACK";

        for (int i = 0; i < arr.length; i++) {
            String str1 = String.format("|%c %c        |", CHANGE_CHAR, text.charAt(i));
            String str2 = String.format("|        %c %c|", text.charAt(i), CHANGE_CHAR);
            arr[i][1] = str1;
            arr[i][9] = str2;
            My.changeCharInArr(arr[i], CHANGE_CHAR, newChars[i]);
        }

        String[] logo = My.unityArr(arr);
        My.printArrColor(logo, 16, My.ANSI_YELLOW, My.ANSI_PURPLE);
    }

}
