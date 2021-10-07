import java.util.Scanner;

public class My {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //BOLD
    public static final String ANSI_BOLD_BLACK =    "\033[1;30m";  // BLACK
    public static final String ANSI_BOLD_RED =      "\033[1;31m";    // RED
    public static final String ANSI_BOLD_GREEN =    "\033[1;32m";  // GREEN
    public static final String ANSI_BOLD_YELLOW =   "\033[1;33m"; // YELLOW
    public static final String ANSI_BOLD_BLUE =     "\033[1;34m";   // BLUE
    public static final String ANSI_BOLD_PURPLE =   "\033[1;35m"; // PURPLE
    public static final String ANSI_BOLD_CYAN =     "\033[1;36m";   // CYAN
    public static final String ANSI_BOLD_WHITE =    "\033[1;37m";  // WHITE

    //
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    //
    private My(){

    }

    public static void printColor(String strPrint, String color){
//        System.out.print(color);
//        System.out.print(strPrint);
//        System.out.print(ANSI_RESET);
        System.out.print(color + strPrint + ANSI_RESET);
    }

    public static void printlnColor(String strPrint, String color){
//        printColor(color, strPrint);
//        System.out.println();
        System.out.println(color + strPrint + ANSI_RESET);
    }

    public static void printColorYellow(String strPrint) {
        printColor(strPrint, ANSI_YELLOW);
    }
    public static void printlnColorYellow(String strPrint) {
        printlnColor(strPrint, ANSI_YELLOW);
    }

    public static void printColorBlue(String strPrint) {
        printColor(strPrint, ANSI_BLUE);
    }
    public static void printlnColorBlue(String strPrint) {
        printlnColor(strPrint, ANSI_BLUE);
    }

    public static void printColorPurple(String strPrint) {
        printColor(strPrint, ANSI_PURPLE);
    }
    public static void printlnColorPurple(String strPrint) {
        printlnColor(strPrint, ANSI_PURPLE);
    }

    public static void printColorGreen(String strPrint) {
        printColor(strPrint, ANSI_GREEN);
    }
    public static void printlnColorGreen(String strPrint) {
        printlnColor(strPrint, ANSI_GREEN);
    }

    public static void printColorRed(String strPrint) {
        printColor(strPrint, ANSI_RED);
    }
    public static void printlnColorRed(String strPrint) {
        printlnColor(strPrint, ANSI_RED);
    }

    public static void printColorBlack(String strPrint) {
        printColor(strPrint, ANSI_BLACK);
    }
    public static void printlnColorBlack(String strPrint) {
        printlnColor(strPrint, ANSI_BLACK);
    }

    public static void printColorCyan(String strPrint) {
        printColor(strPrint, ANSI_CYAN);
    }
    public static void printlnColorCyan(String strPrint) {
        printlnColor(strPrint, ANSI_CYAN);
    }

    public static void printColorWhite(String strPrint) {
        printColor(strPrint, ANSI_WHITE);
    }
    public static void printlnColorWhite(String strPrint) {
        printlnColor(strPrint, ANSI_WHITE);
    }

    public static void setTextColor(String color){
        System.out.print(color);
    }

    public static void setTextColor(String colorFont, String colorBackgound){
        System.out.print(colorFont + colorBackgound);
    }


    public static void resetTextColor(){
        System.out.print(ANSI_RESET);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    //пауза
    public static void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepAnimation(int n, boolean on){
        n /= 500;
        for (int i = 0; i < n; i++) {
            if (on) {
                sleep(500);
                System.out.print(".");
            }
        }
    }

    public static void sleepAnimationLn(int n, boolean on){
        sleepAnimation(n, on);
        System.out.println();
    }


    public static int random(int min, int max) {
        if(min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max) {
        return random(0, max);
    }

    //возвращает заданное количество пробелов
    public static String getStrSpaces(int num) {
        if(num <= 0 ) {
            return "";
        }
        String format = "%" + num + "s";
        return String.format(format,"");
    }

    //если в строке есть цветовые коды- добавляем пробелы
    public static String formatedStrInvisChar(String str) {
        int cnt = 0;
        boolean isInvis = false;
        for (int i = 0; i < str.length() - 1 ; i++) {
            if((int)str.charAt(i) == 27 && (int)str.charAt(i  + 1) == 91)    // 91
            {
                cnt += 2;
                isInvis = true;
                i++;
            }
            else {
                if(isInvis) {
                    cnt++;
                }
                if((int)str.charAt(i) == 109){
                    isInvis = false;
                }
            }
        }
        return str + getStrSpaces(cnt);
    }

//    public static void printArr(String[] arr) {
//        for (String str : arr) {
//            System.out.println(str);
//        }
//    }

    static int maxLength(String[] arr) {
        int max = 0;
        for (String tmp : arr) {
            if(tmp.length() > max) {
                max = tmp.length();
            }
        }
        return max;
    }

    public static void printArr(String[]... arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].length > max) {
                max = arr[i].length;
            }
        }

        for (int n = 0; n < max; n++) {
            for (int i = 0; i < arr.length; i++) {
                String[] pic = arr[i];
                System.out.print(pic[n] + "  ");
            }
            System.out.println();
        }
    }

    //склеить несколько массивов в один
    public static String[] unityArr(int interval, String[]... arr) {
    String[] newArr = new String[arr[0].length];

        for (int i = 0; i < arr[0].length; i++)
        {
            newArr[i] = "";
            for (String[] tmp : arr)
            {
                newArr[i] += tmp[i] + getStrSpaces(interval);
            }
        }
    return newArr;
    }

    public static String[] unityArr(String[]... arr) {
        return unityArr(3, arr);
    }


    public static void printArrColor(String[] arr, int interval, String... colors)
    {
        int cntColor = 0;
        String line = "";
        int start = 0;
        int end = 0;

        for (String str : arr)
        {
            cntColor = 0;
            line = "";

            for (int i = 0; i < str.length(); i += interval) {
                String color = colors[cntColor];
                cntColor++;
                start = i;
                end = Math.min(start + interval, str.length());
                if (cntColor >= colors.length) {
                    cntColor = 0;
                }
                line += color + str.substring(start, end) + My.ANSI_RESET;
            }
            System.out.println(line);
        }

    }


    private static void printStrArrInt(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print((int)str.charAt(i) + " ");
        }
        System.out.println("[" + str.length() + "]");
    }

    //вытягивает команду из строки типа "<key><команда>"
    public static String getStrCmd(String str, String keyStr) {
        int keyLength = keyStr.length();
        if(str.length() > keyLength){
            String key = str.substring(0, keyLength);

            if(key.equals(keyStr)){
                return str.substring(keyLength);
            }
        }
        return "";
    }

    //ввод цифры
    public static int nextInt(String text, int min, int max, int defaultvalue){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(text);
            String cmd = sc.nextLine();

            if(cmd.isEmpty() && defaultvalue != Integer.MIN_VALUE) {
                System.out.println(defaultvalue);
                return defaultvalue;
            }

            if(My.isInteger(cmd)) {
                int num = Integer.parseInt(cmd);
                if(num >= min && num <= max) {
                    return num;
                }
            }
        }
    }


    public static int nextInt(String text, int min, int max){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print(text);
            String cmd = sc.next();

            if(My.isInteger(cmd)) {
                int num = Integer.parseInt(cmd);
                if(num >= min && num <= max) {
                    return num;
                }
            }
        }
    }

    public static int nextInt(String text, int max){
        return nextInt(text, 0, max);
    }

    //ввод символа из нужного списка
    public static char nextCharLowerCase(String text, char... arr) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print(text);
            String cmd = sc.next().toLowerCase();

            if(cmd.length() == 1) {
                char ch = cmd.charAt(0);
                ch = Character.toLowerCase(ch);
                for (char tmp : arr) {
                    if(tmp == ch) {
                        return tmp;
                    }
                }
            }
        }
    }

    //замена символа в массиве string
    public static void changeCharInArr(String[] arr, char oldChar, char newChar ) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].replace(oldChar, newChar);
        }
    }


}

