package gr.aueb.cf.schoolapppro.controller.TeacherControllers;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.UpdateTeacherDTO;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.*;
import gr.aueb.cf.schoolapppro.validator.TeacherValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/school-app/update-teacher")
public class UpdateTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final IUserDAO userDAO = new UserDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	private final IUserService userService = new UserServiceImpl(userDAO);

	private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
	private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Integer, String> userMap;
		Map<Integer, String> specialitiesMap;
		try{
		userMap = userService.getAllUsers();
		specialitiesMap = specialityService.getSpecialitiesMap();
		request.setAttribute("userMap", userMap);
		request.setAttribute("specialitiesMap", specialitiesMap);
		request.getRequestDispatcher("/school/static/templates/teachersPages/update.jsp")
				.forward(request, response);
		}catch (UserDAOException | SpecialityDAOException e) {
			request.setAttribute("errorMessage", "A problem occurred while loading the page, please try again");
			request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/loading-error.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Integer, String> userMap;
		Map<Integer, String> specialitiesMap;

		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		int ssn;
		String SSN = request.getParameter("ssn");
		if (request.getParameter("ssn").equals("")) ssn = 0;
		else if(request.getParameter("ssn").length() != 6) ssn = 1;

		for (int i = 0; i < 6; i++) {
			if (Character.isDigit(request.getParameter("ssn").charAt(i))) ssn = 2;
			break;
		}

		ssn = Integer.parseInt(request.getParameter("ssn"));
		int usernameId = Integer.parseInt(request.getParameter("username"));
		int specialityId = Integer.parseInt(request.getParameter("speciality"));

		UpdateTeacherDTO newTeacherDTO = new UpdateTeacherDTO();
		newTeacherDTO.setId(id);
		newTeacherDTO.setFirstname(firstname);
		newTeacherDTO.setLastname(lastname);
		newTeacherDTO.setSsn(ssn);
		newTeacherDTO.setUsernameId(usernameId);
		newTeacherDTO.setSpecialityId(specialityId);


		try {
			Map<String, String > errors = TeacherValidator.validate(newTeacherDTO);

			if (!errors.isEmpty()) {
				String firstnameMessage = (errors.get("Firstname") != null) ? "Firstname " + errors.get("Firstname") : "";
				String firstnameMessage2 = (errors.get("firstname") != null) ? "Firstname " + errors.get("firstname") : "";
				String lastnameMessage = (errors.get("Lastname") != null) ? "Lastname " + errors.get("Lastname") : "";
				String lastnameMessage2 = (errors.get("lastname") != null) ? "Lastname " + errors.get("lastname") : "";
				String ssnMessage = (errors.get("ssn") != null) ? "SSN " + errors.get("ssn") : "";
				String ssnMessage2 = (errors.get("SSN") != null) ? "SSN " + errors.get("SSN") : "";
				String ssnMessage3 = (errors.get("Ssn") != null) ? "SSN " + errors.get("Ssn") : "";
				String ssnMessage4 = (errors.get("sSn") != null) ? "SSN" + errors.get("sSn") : "";
				String unMessage = (errors.get("Username") != null) ? "Username" + errors.get("Username") : "";

				List<String> errorMessages = new ArrayList<>();

				errorMessages.add("The following errors occurred while executing the request");
				if (!firstnameMessage.equals("")) errorMessages.add(firstnameMessage);
				if (!firstnameMessage2.equals("")) errorMessages.add(firstnameMessage2);
				if (!lastnameMessage.equals("")) errorMessages.add(lastnameMessage);
				if (!lastnameMessage2.equals("")) errorMessages.add(lastnameMessage2);
				if (!ssnMessage.equals("")) errorMessages.add(ssnMessage);
				if (!ssnMessage2.equals("")) errorMessages.add(ssnMessage2);
				if (!ssnMessage3.equals("")) errorMessages.add(ssnMessage3);
				if (!ssnMessage4.equals("")) errorMessages.add(ssnMessage4);
				if (!unMessage.equals("")) errorMessages.add(unMessage);

				request.setAttribute("ssn", SSN);
				request.setAttribute("errorMessage", "");
				request.setAttribute("errorMessages", errorMessages);
				request.setAttribute("updatedTeacher", newTeacherDTO);

				Iterator<Map.Entry<String, String>> mapIt = errors.entrySet().iterator();

				while (mapIt.hasNext()) {
					mapIt.next();
					mapIt.remove();
				}

				request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/update-errors.jsp")
						.forward(request, response);

				return;
			}

				Teacher teacher = teacherServ.updateTeacher(newTeacherDTO);

				try {
					userMap = userService.getAllUsers();
					specialitiesMap = specialityService.getSpecialitiesMap();

					String username = userMap.get(teacher.getUserId());
					String speciality = specialitiesMap.get(teacher.getSpecialityId());

					request.setAttribute("username", username);
					request.setAttribute("speciality", speciality);
				} catch (UserDAOException | SpecialityDAOException e) {
					String message = "The teacher was updated but a problem occurred while loading the updated teacher's page";
					request.setAttribute("ssn", SSN);
					request.setAttribute("updatedTeacher", newTeacherDTO);
					request.setAttribute("errorMessages","");
					request.setAttribute("errorMessage", message);
					request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/update-errors.jsp")
							.forward(request, response);
				}

				request.setAttribute("teacher", teacher);

				request.getRequestDispatcher("/school/static/templates/teachersPages/updated.jsp")
						.forward(request, response);

		} catch (TeacherDAOException | UserDAOException e1) {
			String message = "An internal error occurred while updating the teacher";
			request.setAttribute("ssn", SSN);
			request.setAttribute("updatedTeacher", newTeacherDTO);
			request.setAttribute("errorMessages","");
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/update-errors.jsp")
					.forward(request, response);
		}
	}
}
