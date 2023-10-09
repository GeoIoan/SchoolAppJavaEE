package gr.aueb.cf.schoolapppro.controller.StudentCotnrollers;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.service.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.ICityService;
import gr.aueb.cf.schoolapppro.service.IUserService;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/school-app/students-menu")
public class StudentMenuController  extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);
    private final ICityDAO cityDAO = new CityDAOImpl();
    private final ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Map<Integer, String> usernameMap = userService.getAllUsers();
            Map<Integer, String> citiesMap = cityService.getAllCities();

            request.setAttribute("usernameMap", usernameMap);
            request.setAttribute("citiesMap", citiesMap);
            request.getRequestDispatcher("/school/static/templates/studentsPages/menu.jsp").forward(request, response);
        } catch (UserDAOException | CityDAOException e) {
            request.setAttribute("errorMessage","A problem occurred while loading the students menu, please try again");
            request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/menu-errors.jsp").forward(request, response);
        }
    }
}
