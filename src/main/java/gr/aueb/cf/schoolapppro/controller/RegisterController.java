package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.RegisterDTO;
import gr.aueb.cf.schoolapppro.service.IUserService;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;
import gr.aueb.cf.schoolapppro.service.exceptions.UserAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final IUserDAO userDAO = new UserDAOImpl();
    private static final IUserService userSer = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isError = request.getParameter("isError");
        String success = request.getParameter("success");

        if (isError != null && isError.equals("true")) {
            request.setAttribute("isError", isError);
        } else {
            request.setAttribute("isError", false);
        }

        if(success != null && success.equals("true")){
            request.setAttribute("success", success);
        }  else {
            request.setAttribute("success", false);
        }

        System.out.println(success);

        request.getRequestDispatcher("/school/static/templates/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmedPass = request.getParameter("confirmedPassword");

        String message;

        if (!password.equals(confirmedPass)) {
            message = "The passwords you provided do not match, please try again";
            response.sendRedirect(request.getContextPath() + "/register?isError=true&message=" + URLEncoder.encode(message, "UTF-8"));
            return;
        }

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername(username);
        registerDTO.setPassword(password);
        registerDTO.setConfirmedPassword(confirmedPass);

        try {
            userSer.registerUser(registerDTO);
            response.sendRedirect(request.getContextPath() + "/register?success=true");
        } catch (UserDAOException e) {
             message = "An error occurred while checking for the username's availability. Please try again";
            response.sendRedirect(request.getContextPath() + "/register?isError=true&message=" + URLEncoder.encode(message, "UTF-8"));
        } catch (UserAlreadyExistsException e1) {
            message = e1.getMessage();
            response.sendRedirect(request.getContextPath() + "/register?isError=true&message=" + URLEncoder.encode(message, "UTF-8"));
        }
    }
}
