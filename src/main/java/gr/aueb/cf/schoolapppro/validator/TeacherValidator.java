package gr.aueb.cf.schoolapppro.validator;



import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertTeacherDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateTeacherDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator {
    private final static Map<String, String> errors = new HashMap<>();

    private final static IUserDAO userDAO = new UserDAOImpl();

    private final static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private TeacherValidator(){
    }

    public static Map<String, String> validate (InsertTeacherDTO dto) throws TeacherDAOException, UserDAOException{

        try{
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32){
            errors.put("Firstname", "size is wrong");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32){
            errors.put("Lastname", "size is wrong");
        }

        if (dto.getSsn() == 0) errors.put("SSN", "field is empty");

        if (dto.getSsn() == 1) errors.put("ssn", "must be exactly 6 digits");

        teacherDAO.getAllTeachers().forEach(t -> {
            if (dto.getSsn() == t.getSsn()) {
                errors.put("Ssn", "already exists");
            }
        });

        if (dto.getSsn() == 2) errors.put("sSn", "field must no contain characters");

        if (dto.getFirstname().matches("^.*\\s+.*$")){
            errors.put("firstname", "field has whitespaces");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")){
            errors.put("lastname", "field has whitespaces");
        }

        if (userDAO.isTeachersUsernameRegistered(dto.getUsername()) != 0 || userDAO.isStudentsUsernameRegistered(dto.getUsername()) != 0) {
            errors.put("Username", "is already registered");
        }

        return errors;

        } catch (UserDAOException | TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Map<String, String> validate (UpdateTeacherDTO dto) throws TeacherDAOException, UserDAOException{

        try{
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32){
            errors.put("Firstname", "size is wrong");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32){
            errors.put("Lastname", "size is wrong");
        }

        if (dto.getSsn() == 0) errors.put("SSN", "field is empty");

        if (dto.getSsn() == 1) errors.put("ssn", "must be exactly 6 digits");

        teacherDAO.getAllTeachers().forEach(t -> {
            if ((dto.getSsn() == t.getSsn()) && (dto.getId() != t.getId())) {
                errors.put("Ssn", "already exists");
            }
        });

        if (dto.getSsn() == 2) errors.put("sSn", "field must not contain characters");

        if (dto.getFirstname().matches("^.*\\s+.*$")){
            errors.put("firstname", "field has whitespaces");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")){
            errors.put("lastname", "field has whitespaces");
        }

        if ((userDAO.isTeachersUsernameRegistered(dto.getUsernameId()) != 0 && userDAO.isTeachersUsernameRegistered(dto.getUsernameId()) != dto.getId())
                || (userDAO.isStudentsUsernameRegistered(dto.getUsernameId()) != 0 && userDAO.isStudentsUsernameRegistered(dto.getUsernameId()) != dto.getId())) {
            errors.put("Username", "is already registered");
        }

        return errors;
        } catch (UserDAOException | TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
