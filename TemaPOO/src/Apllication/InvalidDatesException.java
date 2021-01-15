package Apllication;

public class InvalidDatesException extends Exception{
    public InvalidDatesException(){
        super("Invalid date exception");
    }
    public InvalidDatesException(String error){
        super(error);
    }
}
