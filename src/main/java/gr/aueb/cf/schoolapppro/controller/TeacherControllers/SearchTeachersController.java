package gr.aueb.cf.schoolapppro.controller.TeacherControllers;



import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/search-teacher")
public class SearchTeachersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();

    private final IUserDAO userDAO = new UserDAOImpl();

    private final IUserService userService = new UserServiceImpl(userDAO);

    private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/school-app/teachers-menu.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lastname = request.getParameter("lastname").trim();
        Map<Integer, String> userMap;
        Map<Integer, String> specialitiesMap ;

        String message = "";
        try {
            List<Teacher> teachers;
            teachers = teacherService.getTeacherByLastName(lastname);
            if (teachers.size() == 0) {
                request.setAttribute("errorMessage", "Teacher with lastname " + lastname + " was not found");
                request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/search-errors.jsp").forward(request, response);
                return;
            }

            userMap = userService.getAllUsers();
            specialitiesMap = specialityService.getSpecialitiesMap();

            request.setAttribute("teachers", teachers);
            request.setAttribute("userMap", userMap);
            request.setAttribute("specialitiesMap", specialitiesMap);
            request.getRequestDispatcher("/school/static/templates/teachersPages/teachers.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            message = e.getMessage();
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/search-errors.jsp").forward(request, response);
        } catch (UserDAOException | SpecialityDAOException e1) {
            message = "A problem occurred while loading the page. Please try again.";
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/search-errors.jsp").forward(request, response);
        }
    }
}
