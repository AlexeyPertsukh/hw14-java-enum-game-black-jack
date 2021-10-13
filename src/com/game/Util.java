package com.game;

import java.util.Scanner;

public class Util {
    //
    private Util(){
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
                line += color + str.substring(start, end) + Color.ANSI_RESET;
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
    public static int nextInt(String text, int min, int max, int defaultValue){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(text);
            String cmd = sc.nextLine();

            if(cmd.isEmpty() && defaultValue != Integer.MIN_VALUE) {
                System.out.println(defaultValue);
                return defaultValue;
            }

            if(isInteger(cmd)) {
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

            if(isInteger(cmd)) {
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

