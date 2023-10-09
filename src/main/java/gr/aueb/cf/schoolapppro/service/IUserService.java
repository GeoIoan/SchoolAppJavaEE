package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.RegisterDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.exceptions.UserAlreadyExistsException;

import java.util.HashMap;

public interface IUserService {
    User registerUser (RegisterDTO registerDTO) throws UserDAOException, UserAlreadyExistsException;
    HashMap<Integer, String> getAllUsers() throws UserDAOException;


}

