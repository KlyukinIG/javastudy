package game;

import java.util.*;

public class Game {
    String userName;
    boolean pauseGame = false;
    int count = 1;
    int tryToGuess = 0;
    int maxNumber;
    int randomNumber;
    List<String> score = new ArrayList<>();
    Scanner input = new Scanner(System.in);


    public void startGame() {
        setNumber();
        while (true) {
            setRandomNumber();
            if (count > 1) {
                System.out.print("Еще раз? Да/Нет/Пауза? - ");
                input.nextLine();
            } else {
                System.out.print("Да/Нет/Пауза? - ");
                input.nextLine();
            }
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("ДА") && count == 1) {
                System.out.print("Введи никнейм -  ");
                userName = input.nextLine();
                guessNumber();
            } else if (answer.equalsIgnoreCase("ДА") && count > 1) {
                System.out.print("Хочешь сменить никнейм? Да/Нет? -  ");
                String changeNickName = input.nextLine();
                count = 1;
                if (changeNickName.equalsIgnoreCase("Да")) {
                    System.out.print("Введи новый никнейм -  ");
                    userName = input.nextLine();
                }else if (changeNickName.equalsIgnoreCase("Нет")) {
                    System.out.println("Никнейм не изменен");
                }
                guessNumber();
            } else if (answer.equalsIgnoreCase("НЕТ")) {
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
            }
        }


    }

    public void getStatistics() {
        System.out.println("Статистика игры:");
        for(String i:score) {
            System.out.println(i);
        }
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
        this.score.add("Попытка №" + tryToGuess + ":" + "Пользователь"  + "[" + userName + "]" + ":" + "Угадал цифру" + "[" + randomNumber + "]" + " c " + count + " раз(а);");
    }

    private void setRandomNumber() {
        randomNumber = (int) (Math.random() * maxNumber);
    }

    private void setNumber() {
        System.out.println("Привет! Это игра угадай число! Введи максимальное число которое я могу загадать:");
        while (true) {
            try {
                this.maxNumber = input.nextInt();
                System.out.println("Загадываю......");
                break;
            } catch (InputMismatchException i) {
                System.out.println("Введите корректное число!");
                input.next();
            }
        }
        System.out.println("Попробуешь угадать?");
    }



}

