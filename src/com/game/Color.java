package com.game;
/*
Цвета: http://surl.li/mrnv
 */

public class Color {

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

    private Color(){
    }

    public static void printColor(String strPrint, String color){
        setTextColor(color);
        System.out.print(strPrint);
        resetTextColor();
    }

    public static void printlnColor(String strPrint, String color){
        printColor(strPrint, color);
        System.out.println();
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

    public static void resetTextColor(){
        System.out.print(ANSI_RESET);
    }

}
