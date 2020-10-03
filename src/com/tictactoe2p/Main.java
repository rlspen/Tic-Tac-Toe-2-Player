package com.tictactoe2p;

import java.util.Scanner;

public class Main {

    private static String winner;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] board = new String[3][3];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                board[row][column] = "_";
            }
        }

        int cordX, cordY, turn = 0;
        String user;
        boolean gameOver = false;
        while (!gameOver) {

            printBoard(board);

            if (evaluateBoard(board)){
                gameOver = true;
                continue;
            }

            System.out.print("Enter the coordinates: ");

            if(turn % 2 == 0) {
                user = "X";
            }
            else {
                user = "O";
            }

            try {
                cordX = Integer.parseInt(scanner.next());
                cordY = Integer.parseInt(scanner.next());

                if (!((cordX >= 1 && cordX <= 3) && (cordY >= 1 && cordY <= 3))) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (board[3 - cordY][cordX - 1].equals("_")) {
                    board[3 - cordY][cordX - 1] = user;
                    isLegal(board);
                    turn++;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static void printBoard (String[][] board) {

        System.out.println("---------");
        System.out.println("| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |");
        System.out.println("---------");

    }

    private static String getWinner () {

        return winner;

    }

    private static void setWinner (String w) {

        winner = w;

    }

    private static boolean evaluateBoard (String[][] board) {

        if (checkRows(board) && isLegal(board)) {
            System.out.println(getWinner() + " wins");
            return true;

        }

        if (checkColumns(board) && isLegal(board)) {
            System.out.println(getWinner() + " wins");
            return true;
        }

        if (checkDiagonals(board) && isLegal(board)) {
            System.out.println(getWinner() + " wins");
            return true;
        }

        if (checkDraw(board) && isLegal(board)) {
            System.out.println("Draw");
            return true;
        }

//        if (emptyCells(board) && isLegal(board)) {
//            System.out.println("Game not finished");
//            return false;
//        }

        if (!isLegal(board)) {
            System.out.println("Impossible");
            return true;
        }

        return false;

    }

    private static boolean checkRowOrCol(String s1, String s2, String s3) {

        return ((!s1.equals("_")) && (s1.equals(s2)) && (s2.equals(s3)));

    }

    private static boolean checkRows(String[][] board) {

        for (int row = 0; row < board.length; row++) {
            if (checkRowOrCol(board[row][0], board[row][1], board[row][2])) {
                setWinner(board[row][0]);
                return true;
            }
        }
        return false;

    }

    private static boolean checkColumns(String[][] board) {

        for (int column = 0; column < board[0].length; column++) {
            if (checkRowOrCol(board[0][column], board[1][column], board[2][column])) {
                setWinner(board[0][column]);
                return true;
            }
        }
        return false;

    }

    private static boolean checkDiagonals(String[][] board) {

        if (checkRowOrCol(board[0][0], board[1][1], board[2][2])) {
            setWinner(board[0][0]);
            return true;
        }

        if (checkRowOrCol(board[0][2], board[1][1], board[2][0])) {
            setWinner(board[0][2]);
            return true;
        }

        return false;

    }

    private static boolean emptyCells(String[][] board) {

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column].equals("_")) {
                    return true;
                }
            }
        }
        return false;

    }

    private static boolean checkDraw(String[][] board) {

        return (!checkRows(board) && !checkColumns(board) && !checkDiagonals(board) && !emptyCells(board));

    }

    private static boolean isLegal(String[][] board) {

        int rowWins = 0, colWins = 0;
        boolean turnCheck = false, diagCheck = false, rowColCheck = false;

        for (int row = 0; row < board.length; row++) {
            if (checkRowOrCol(board[row][0], board[row][1], board[row][2])) {
                rowWins++;
            }
        }

        for (int column = 0; column < board.length; column++) {
            if (checkRowOrCol(board[0][column], board[1][column], board[2][column])) {
                colWins++;
            }
        }

        if (((numberOfX(board) - numberOfO(board)) == -1) || ((numberOfX(board) - numberOfO(board)) == 0) || ((numberOfX(board) - numberOfO(board)) == 1)) {
            turnCheck = true;
        }

        if (!((checkRowOrCol(board[0][0], board[1][1], board[2][2])) && (checkRowOrCol(board[0][2], board[1][1], board[2][0])))) {
            diagCheck = true;
        }

        if ((rowWins <= 1) && (colWins <= 1)) {
            rowColCheck = true;
        }

        return turnCheck && diagCheck && rowColCheck;

    }

    private static int numberOfX (String[][] board) {

        int x = 0;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column].equals("X")) {
                    x++;
                }
            }
        }

        return x;

    }

    private static int numberOfO (String[][] board) {

        int o = 0;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column].equals("O")) {
                    o++;
                }
            }
        }

        return o;

    }


}


