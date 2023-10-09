package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.model.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public interface IUserDAO {

    User insertUser (User user) throws UserDAOException;
    User updateUser (User user) throws UserDAOException;
    void deleteUser (int usersId) throws UserDAOException;
    void deleteUser (String username) throws UserDAOException;
    User getById (int usersId) throws UserDAOException;
    User getByUsername (String username) throws UserDAOException;

    HashMap<Integer,String> usersMap() throws UserDAOException;
    int isTeachersUsernameRegistered(int usersId) throws UserDAOException;
    int isStudentsUsernameRegistered(int usersId) throws UserDAOException;

}
