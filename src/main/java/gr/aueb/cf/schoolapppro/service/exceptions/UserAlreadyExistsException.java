package gr.aueb.cf.schoolapppro.service.exceptions;

public class UserAlreadyExistsException extends Exception{

   private static final long serialVersionUID = 123456L;

   public UserAlreadyExistsException(String username){
       super("The username " + username + " already exists");
   }
}
