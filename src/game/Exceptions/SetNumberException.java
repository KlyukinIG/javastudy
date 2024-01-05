package game.Exceptions;

public class SetNumberException extends Exception{
    public SetNumberException(){
        System.out.println("Поле принимает только число! Число не может быть отрицательным или равным нулю!");
    }

}
