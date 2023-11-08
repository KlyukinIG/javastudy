package game;

import java.util.*;

public class Game {
    String userName;
    int count = 1;
    int tryToGuess = 0;
    int randomNumber;
    int maxNumber;
    List<String> score = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public void startGame() {
        System.out.println("Привет! Это игра угадай число! Я загадываю число.... ");
        while (true) {
            try {
                System.out.println("Введи максимальное число которое я могу загодать :");
                maxNumber = input.nextInt();
                break;
            } catch (InputMismatchException i) {
                System.out.println("Введите корректное число!");
                input.next();
            }
        }

            System.out.println("Попробуешь угадать?");

            while (true) {
                randomNumber = (int) (Math.random() * maxNumber);
                if (count > 1) {
                    System.out.print("Еще раз? Да/Нет? - ");
                    input.nextLine();
                } else {
                    System.out.print("Да/Нет? - ");
                }
                String answer = input.nextLine();
                if (answer.equalsIgnoreCase("ДА") && count == 1) {
                    System.out.print("Введи никнейм -  ");
                    userName = input.nextLine();
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
                                score.add("Попытка №" + tryToGuess + ":" + "Пользователь"  + "[" + userName + "]" + ":" + "Угадал цифру" + "[" + maxNumber + "]" + " c " + count + " раз(а);");
                                count++;
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите корректное число!");
                            input.next();
                        }
                    }
                } else if (answer.equalsIgnoreCase("ДА") && count > 1) {
                    System.out.print("Хочешь сменить никнейм? Да/Нет? -  ");
                    String changeNickName = input.nextLine();
                    count = 1;
                    if (changeNickName.equalsIgnoreCase("Да")) {
                        System.out.print("Введи новый никнейм -  ");
                        userName = input.nextLine();
                        System.out.println("Введи любое число от 0 до " + maxNumber);
                    } else {
                        System.out.println("Введи любое число от 0 до " + maxNumber);
                    }
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
                                score.add("Попытка №" + tryToGuess + ":" + "Пользователь"  + "[" + userName + "]" + ":" + "Угадал цифру" + "[" + maxNumber + "]" + " c " + count + " раз(а);");
                                count++;
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите корректное число!");
                            input.next();
                        }
                    }
                } else if (answer.equalsIgnoreCase("НЕТ")) {
                    System.out.println("Показать статистику игр? Да/Нет?");
                    String showScore = input.nextLine();
                    if (showScore.equalsIgnoreCase("да")) {
                        for(String i:score){
                            System.out.println(i);
                        }
                        System.out.println("Спасибо за игру, удачи!");
                        break;
                    } else if (showScore.equalsIgnoreCase("нет")) {
                        System.out.println("Хорошо, удачи!");
                        break;
                    }
                }
            }


    }

}

