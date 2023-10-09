package gr.aueb.cf.schoolapppro.controller;


import gr.aueb.cf.schoolapppro.authentication.UserAuthenticator;
import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.LoginDTO;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final IUserDAO userDAO = new UserDAOImpl();
    private static final UserServiceImpl userSer = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isError = request.getParameter("isError");

        if (isError != null && isError.equals("true")) {
            request.setAttribute("isError", isError);
        } else {
            request.setAttribute("isError", false);
        }

        request.getRequestDispatcher("/school/static/templates/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       LoginDTO userLoginDTO = new LoginDTO();
       userLoginDTO.setUsername(username);
       userLoginDTO.setPassword(password);

        try{
            boolean principleIsAuthenticated = UserAuthenticator.isUserOk(userLoginDTO);
            System.out.println(principleIsAuthenticated);
            if (principleIsAuthenticated) {
            HttpSession session = request.getSession(false);
            session.setAttribute("loginName", username);
            response.sendRedirect(request.getContextPath() + "/school-app/menu");
            } else {
                response.sendRedirect(request.getContextPath() + "/login?isError=true");
            }
        } catch (UserDAOException e) {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }
    }
}
