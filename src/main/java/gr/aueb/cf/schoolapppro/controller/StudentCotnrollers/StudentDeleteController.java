package gr.aueb.cf.schoolapppro.controller.StudentCotnrollers;


import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/school-app/delete-student")
public class StudentDeleteController extends HttpServlet {
    private final IStudentDAO studentDAO = new StudentDAOImpl();

    private final IStudentService studentService = new StudentServiceImpl(studentDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try{
            studentService.deleteStudent(id);
            request.getRequestDispatcher("/school/static/templates/studentsPages/deleted.jsp")
                    .forward(request, response);
        }catch (StudentDAOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/delete-error.jsp")
                    .forward(request, response);
        }
    }
}
