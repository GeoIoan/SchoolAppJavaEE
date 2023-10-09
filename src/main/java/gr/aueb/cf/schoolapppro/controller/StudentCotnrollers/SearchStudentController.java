package gr.aueb.cf.schoolapppro.controller.StudentCotnrollers;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.*;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.*;
import gr.aueb.cf.schoolapppro.service.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/search-student")
public class SearchStudentController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();

    private final IUserService userService = new UserServiceImpl(userDAO);

    private final ICityDAO cityDAO = new CityDAOImpl();

    private final ICityService cityService = new CityServiceImpl(cityDAO);

    private final IStudentDAO studentDAO = new StudentDAOImpl();

    private final IStudentService studentService = new StudentServiceImpl(studentDAO);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/school-app/students-menu")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lastname = request.getParameter("lastname").trim();
        Map<Integer, String> userMap;
        Map<Integer, String> citiesMap;


        String message = "";
        try {
            List<Student> students;
            students = studentService.getStudentByLastName(lastname);
            if (students.size() == 0) {
                request.setAttribute("errorMessage", "Student with lastname " + lastname + " was not found");
                request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/search-errors.jsp").forward(request, response);
                return;
            }
            userMap = userService.getAllUsers();
            citiesMap = cityService.getAllCities();

            Map<Integer, String> birthdates = new HashMap<>();

           students.forEach(s -> {
              birthdates.put(s.getId(),DateUtil.toString(s.getBirthDate()));
           });

            request.setAttribute("birthdates", birthdates);
            request.setAttribute("students", students);
            request.setAttribute("userMap", userMap);
            request.setAttribute("citiesMap", citiesMap);
            request.getRequestDispatcher("/school/static/templates/studentsPages/students.jsp").forward(request, response);
        } catch (StudentDAOException | UserDAOException | CityDAOException e) {
            message = "A problem occurred while loading the page. Please try again.";
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/search-errors.jsp").forward(request, response);
        }
    }
}
