package gr.aueb.cf.schoolapppro.controller.TeacherControllers;

import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.service.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.IUserService;
import gr.aueb.cf.schoolapppro.service.SpecialityServiceImpl;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/school-app/teachers-menu")
public class TeacherMenuController extends HttpServlet {
    private final IUserDAO userDAO = new UserDAOImpl();
    private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Map<Integer, String> usernameMap = userService.getAllUsers();
            Map<?, ?> specialitiesMap = specialityService.getSpecialitiesMap();

            request.setAttribute("usernameMap", usernameMap);
            request.setAttribute("specialitiesMap", specialitiesMap);
            request.getRequestDispatcher("/school/static/templates/teachersPages/menu.jsp").forward(request, response);
        } catch (UserDAOException | SpecialityDAOException e) {
            request.setAttribute("errorMessage","A problem occurred while loading the teachers menu, please try again");
            request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/menu-errors.jsp").forward(request, response);
        }

    }
}
