package gr.aueb.cf.schoolapppro.service.exceptions;

public class WrongPasswordException extends Exception{

    private static final long serialVersionUID = 123456L;

    public WrongPasswordException (){
        super("Wrong password. Please try again");
    }
}
