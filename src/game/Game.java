package game;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Game {
    private String userName;
    private int count = 1;
    private int tryToGuess = 0;
    private int maxNumber;
    private int randomNumber;
    private Scanner input = new Scanner(System.in);
    private GameState gameState = GameState.IS_RUNNING ;
    private PlayerStatistics playerStatistics = new PlayerStatistics();

    public void startGame() {
        setNumber();
        while (GameState.IS_RUNNING == gameState) {
            if (GameState.RESTART != gameState) {
                if (GameState.PAUSED != gameState) {
                    setRandomNumber();
                    if (count > 1) {
                        System.out.println("Еще раз? Да/Нет/Пауза? Или рестарт игры? - ");
                        input.nextLine();
                    } else {
                        System.out.println("Играем? Да/Нет/Пауза? - ");
                        input.nextLine();
                    }
                    String answer = input.nextLine();
                    if (answer.equalsIgnoreCase("Да") && count == 1) {
                        System.out.print("Введи никнейм -  ");
                        userName = input.nextLine();
                        guessNumber();
                    } else if (answer.equalsIgnoreCase("Да") && count > 1) {
                        System.out.print("Хочешь сменить никнейм? Да/Нет? -  ");
                        String changeNickName = input.nextLine();
                        count = 1;
                        if (changeNickName.equalsIgnoreCase("Да")) {
                            System.out.print("Введи новый никнейм -  ");
                            userName = input.nextLine();
                        } else if (changeNickName.equalsIgnoreCase("Нет")) {
                            System.out.println("Никнейм не изменен");
                        }
                        guessNumber();
                    } else if (answer.equalsIgnoreCase("Нет")) {
                        System.out.println("Показать статистику игр? Да/Нет?");
                        String showScore = input.nextLine();
                        if (showScore.equalsIgnoreCase("да")) {
                            playerStatistics.printStatistics();
                            System.out.println("Спасибо за игру, удачи!");
                            break;
                        } else if (showScore.equalsIgnoreCase("нет")) {
                            System.out.println("Хорошо, удачи!");
                            break;
                        }
                    } else if (answer.equalsIgnoreCase("Пауза")) {
                        setPauseGame();
                    } else if (answer.equalsIgnoreCase("Рестарт")) {
                        setRestartGame();
                    }
                } else {
                    System.out.println("Игра на паузе! Возообновить? Да/Нет");
                    String resumeGame = input.nextLine();
                    if (resumeGame.equalsIgnoreCase("Да")) {
                        setResumeGame();
                    } else if (resumeGame.equalsIgnoreCase("Нет")) {
                        System.out.println("Спасибо за игру, удачи!");
                        break;
                    }
                }
            } else {
                startGame();
            }
        }
    }

    public void setRestartGame() {
        gameState = GameState.RESTART;
        count = 1;
        tryToGuess = 0;
    }

    public void setPauseGame() {
        gameState = GameState.PAUSED;
    }

    public void setResumeGame() {
        gameState = GameState.IS_RUNNING;
    }

    public void guessNumber() {
        System.out.println("Введи любое число от 0 до " + maxNumber);
        while (true) {
            try {
                int number = input.nextInt();
                if (number < randomNumber) {
                    System.out.println("Слишком мало!");
                    count++;
                } else if (number > randomNumber) {
                    System.out.println("Слишком много!");
                    count++;
                } else {
                    System.out.println("Правильно!");
                    System.out.println(userName + " угадал число c " + count + " раза!");
                    tryToGuess = tryToGuess + 1;
//                    playerStatistics.addResult(userName, count, tryToGuess);
                    count++;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите корректное число!");
                input.next();
            }
        }
    }

    public void setRandomNumber() {
        randomNumber = (int) (Math.random() * (maxNumber + 1));
    }

    public void setNumber() {
        System.out.println("Привет! Это игра угадай число! Введи максимальное число которое я могу загадать:");
        while (true) {
            try {
                while (true) {
                    maxNumber = input.nextInt();
                    if (maxNumber > 0) {
                        input.nextLine();
                        break;
                    } else if (maxNumber < 0) {
                        System.out.println("Число не может быть отрицательным!");
                        input.nextLine();
                    } else if (maxNumber == 0) {
                        System.out.println("Слишком просто! Хитрец :)");
                        input.nextLine();
                    }
                }
                System.out.println("Загадываю......Попробуешь угадать?");
                break;
            } catch (InputMismatchException i) {
                System.out.println("Введите корректное число!");
                input.next();
            }
        }
    }
}

