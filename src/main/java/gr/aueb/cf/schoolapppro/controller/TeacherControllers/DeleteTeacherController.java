package gr.aueb.cf.schoolapppro.controller.TeacherControllers;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UpdateTeacherDTO;
import gr.aueb.cf.schoolapppro.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/school-app/delete-teacher")
public class DeleteTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			teacherServ.deleteTeacher(id);
			request.getRequestDispatcher("/school/static/templates/teachersPages/deleted.jsp")
					.forward(request, response);
		} catch (TeacherDAOException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/delete-error.jsp")
					.forward(request, response);
		}
	}
}

