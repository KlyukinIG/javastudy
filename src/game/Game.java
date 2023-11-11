package game;

import java.util.*;

public class Game {
    private String userName;
    private boolean pauseGame = false;
    private boolean restartGame = false;
    private int count = 1;
    private int tryToGuess = 0;
    private int maxNumber;
    private int randomNumber;
    private List<String> score = new ArrayList<>();
    private Scanner input = new Scanner(System.in);


    public void startGame() {
        setNumber();
        while (true) {
            if (!restartGame) {
                if (!pauseGame) {
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
                            getStatistics();
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
                restartGame = false;
                startGame();
            }
        }
    }

    public void getStatistics() {
        if (score.isEmpty()) {
            System.out.println("Пока тут пусто!");
        } else {
            System.out.println("Статистика игры:");
            for (String i : score) {
                System.out.println(i);
            }
        }
    }


    public void setRestartGame() {
        restartGame = true;
        count = 1;
        tryToGuess = 0;
        score.clear();
    }
    public void setPauseGame() {
        pauseGame = true;
    }

    public void setResumeGame() {
        pauseGame = false;
    }

    private void guessNumber() {
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
                    addStatistics();
                    count++;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите корректное число!");
                input.next();
            }
        }
    }

    private void addStatistics() {
        score.add("Попытка №" + tryToGuess + ":" + "Пользователь"  + "[" + userName + "]" + ":" + "Угадал цифру" + "[" + randomNumber + "]" + " c " + count + " раз(а);");
    }

    private void setRandomNumber() {
        randomNumber = (int) (Math.random() * maxNumber);
    }

    private void setNumber() {
        System.out.println("Привет! Это игра угадай число! Введи максимальное число которое я могу загадать:");
        while (true) {
            try {
                maxNumber = input.nextInt();
                System.out.println("Загадываю......Попробуешь угадать?");
                break;
            } catch (InputMismatchException i) {
                System.out.println("Введите корректное число!");
                input.next();
            }
        }
    }



}

