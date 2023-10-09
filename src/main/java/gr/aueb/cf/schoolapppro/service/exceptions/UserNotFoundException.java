package gr.aueb.cf.schoolapppro.service.exceptions;

public class UserNotFoundException extends Exception{

    private static final long serialVersionUID = 123456L;

    public UserNotFoundException(String username){
        super("The username " + username + " was not found");
    }
}
