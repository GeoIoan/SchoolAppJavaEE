package gr.aueb.cf.schoolapppro.authentication;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.LoginDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.security.SecUtil;

public class UserAuthenticator {

    private final static IUserDAO userDAO = new UserDAOImpl();

    public static boolean isUserOk (LoginDTO dto) throws UserDAOException {
        try {
            User user = userDAO.getByUsername(dto.getUsername());
            return user != null && SecUtil.checkPassword(dto.getPassword(), user.getPassword());
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
