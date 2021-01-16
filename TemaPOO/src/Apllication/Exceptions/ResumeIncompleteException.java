package Apllication.Exceptions;

public class ResumeIncompleteException extends Exception {
    public ResumeIncompleteException() {
        super("Invalid Information exception");
    }

    public ResumeIncompleteException(String error) {
        super(error);
    }
}
