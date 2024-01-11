package game;

import game.Exceptions.SetNumberException;

import java.util.Scanner;

public class NewGame {

    private static Scanner input = new Scanner(System.in);
    private static GameState gameStatus = GameState.IS_RUNNING;
    private static String answer;
    private static int randomNumber;
    private static String user;
    private static int score;
    private static int tryes;
    private static PlayerStatistics playerStatistics = new PlayerStatistics();


    public static void main(String[] args) {
    }

    void startGamez() {
        do {
            System.out.println("Привет давай поиграем в игру угадай число?(Да/Нет)");
            answer = input.nextLine();
            if (answer.equalsIgnoreCase("да")) {
                game();
            } else if (answer.equalsIgnoreCase("нет")) {
                System.out.println("Удачи! Заходи если что!");
                playerStatistics.printStatistics();
            }
        } while (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("нет"));
        input.close();
    }


    private static void setNumber() {
        System.out.println("Введи максимальное число которое я могу загодать");
        while (true) {
            try {
                if (!input.hasNextInt()) {
                    throw new SetNumberException();
                }
                int maxNumber = input.nextInt();
                if (maxNumber <= 0) {
                    throw new SetNumberException();
                } else {
                    randomNumber = (int) (Math.random() * maxNumber) + 1;
                    break;
                }
            } catch (SetNumberException e) {
                System.out.println("Попробуй заново!");
                input.nextLine();
            }
        }
    }

    private static void game() {
        setUserName();
        setNumber();
        while (gameStatus == GameState.IS_RUNNING) {
            System.out.println("Попробуй угадать загаданное число! Введи свой вариант");
            int userNumber = input.nextInt();
            input.nextLine();
            switch (Integer.compare(userNumber, randomNumber)) {
                case -1:
                    System.out.println("Мало");
                    score++;
                    tryes++;
                    break;
                case 0:
                    score++;
                    tryes++;
                    playerStatistics.addResult(user, score, tryes);
                    gotGuessed();
                    break;
                case 1:
                    System.out.println("Много!");
                    score++;
                    tryes++;
                    break;
            }
        }
    }


    private static void gotGuessed() {
        System.out.println("Угадал!");
        String askPlayer;
        do {
            System.out.println("Еще раз?Да/Нет");
            askPlayer = input.nextLine();
            if (askPlayer.equalsIgnoreCase("да")) {
                game();
            } else if (askPlayer.equalsIgnoreCase("нет")) {
                System.out.println("Удачи! Заходи если что!");
                playerStatistics.printStatistics();
                gameStatus = GameState.END;
            }
        } while (!askPlayer.equalsIgnoreCase("да") && !askPlayer.equalsIgnoreCase("нет"));
    }

    private static void setUserName() {
        System.out.println("Введи свой ник");
        String namePlayer = input.nextLine();
        user = namePlayer;
    }
}
