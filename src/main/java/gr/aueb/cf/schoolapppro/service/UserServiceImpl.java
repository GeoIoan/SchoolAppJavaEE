package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.RegisterDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.exceptions.UserAlreadyExistsException;
import gr.aueb.cf.schoolapppro.service.security.SecUtil;

import java.util.HashMap;

public class UserServiceImpl implements IUserService{

    private IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(RegisterDTO registerDTO) throws UserDAOException, UserAlreadyExistsException {
        User user;
        try {
            if (userDAO.getByUsername(registerDTO.getUsername()) != null) throw new UserAlreadyExistsException(registerDTO.getUsername());
            else {
                user = mapRegisterDTO(registerDTO);
                userDAO.insertUser(user);
            }
        }catch (UserAlreadyExistsException | UserDAOException e){
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public HashMap<Integer, String> getAllUsers() throws UserDAOException {
        try{
            return userDAO.usersMap();
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private User mapRegisterDTO (RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(SecUtil.hashPassword(registerDTO.getPassword()));
        return user;
    }
}
