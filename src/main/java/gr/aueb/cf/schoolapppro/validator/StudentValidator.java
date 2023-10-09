package gr.aueb.cf.schoolapppro.validator;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertStudentDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateStudentDTO;
import gr.aueb.cf.schoolapppro.service.util.DateUtil;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class StudentValidator {

    private final static IUserDAO userDAO = new UserDAOImpl();

    private StudentValidator(){
    }

    public static Map<String, String> validate (InsertStudentDTO dto) throws  UserDAOException{
        Map<String, String> errors = new HashMap<>();
        try{

        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32){
            errors.put("Firstname", "must be have at least 3 characters and less than 32");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32){
            errors.put("Lastname", "must be have at least 3 characters and less than 32");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")){
            errors.put("lastname", "should not have whitespaces");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")){
            errors.put("firstname", "should not have whitespaces");
        }

        if (dto.getGender().equals("")){
            errors.put("gender","option must be checked");
        }


        if (userDAO.isStudentsUsernameRegistered(dto.getUsername()) != 0 || userDAO.isTeachersUsernameRegistered(dto.getUsername()) != 0) {
            errors.put("Username", "already registered");
        }

        if (!DateUtil.isDateValid(dto.getBirthdate())) {
            errors.put("Birthdate", "is wrong");
        }
        return errors;

    } catch (UserDAOException e) {
        e.printStackTrace();
        throw e;
    }
    }


    public static Map<String, String> validate (UpdateStudentDTO dto) throws  UserDAOException{
        Map<String, String> errors = new HashMap<>();

        try{

            if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32){
                errors.put("Firstname", "must be have at least 3 characters and less than 32");
            }

            if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32){
                errors.put("lastname", "must be have at least 3 characters and less than 32");
            }

            if (dto.getFirstname().matches("^.*\\s+.*$")){
                errors.put("firstname", "should not have whitespaces");
            }

            if (dto.getLastname().matches("^.*\\s+.*$")){
                errors.put("Lastname", "should not have whitespaces");
            }

            if ((userDAO.isTeachersUsernameRegistered(dto.getUsername()) != 0 && userDAO.isTeachersUsernameRegistered(dto.getUsername()) != dto.getId())
                    || (userDAO.isStudentsUsernameRegistered(dto.getUsername()) != 0 && userDAO.isStudentsUsernameRegistered(dto.getUsername()) != dto.getId())) {
                errors.put("Username", "is already registered");
            }

            if (dto.getGender().equals("")){
                errors.put("gender","option must be checked");
            }

            if (!DateUtil.isDateValid(dto.getBirthdate())) {
                errors.put("Birthdate", "is wrong");
            }
            return errors;

        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
