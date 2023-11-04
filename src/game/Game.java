package SimpleGame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random r = new Random();
        int randomNumber = r.nextInt(10);
        int count = 1;
        System.out.println("Попробуй угадать число до 10!");

        while (true) {
            if (count > 1) {
                System.out.print("Еще раз? Введи ДА или НЕТ!:");
                input.nextLine();
            } else {
                System.out.print("Играем? Введи ДА или НЕТ!:");
            }
            String answer = input.nextLine();
            if (answer.equals("ДА")) {
                System.out.println("Введи любое число от 0 до 10!");
                while (true) {
                    try {
                        int number = input.nextInt();
                        if (number < randomNumber) {
                            System.out.println("Слишком мало!");
                        } else if (number > randomNumber) {
                            System.out.println("Слишком много!");
                        } else {
                            System.out.println("Правильно!");
                            count++;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Введите корректное число!");
                        input.next();
                    }
                }
            } else if (answer.equals("НЕТ")) {
                System.out.println("Хорошо, пока!");
                break;
            }
        }

    }
}
