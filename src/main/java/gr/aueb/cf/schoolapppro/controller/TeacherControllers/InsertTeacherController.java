package gr.aueb.cf.schoolapppro.controller.TeacherControllers;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertTeacherDTO;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.*;
import gr.aueb.cf.schoolapppro.validator.TeacherValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/insert-teacher")
public class InsertTeacherController extends HttpServlet {
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();

    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private final IUserDAO userDAO = new UserDAOImpl();

    private final IUserService userService = new UserServiceImpl(userDAO);

    private final ISpecialityDAO specialityDAO = new SpecialityDAOImpl();

    private final ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws   ServletException, IOException {
        request.getRequestDispatcher("/school-app/teachers-menu").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();

        String SSN = request.getParameter("ssn");
        Teacher teacher;
        int ssn;
        String un = "";
        String sp = "";

        if (request.getParameter("ssn").equals("")) ssn = 0;
        else if(request.getParameter("ssn").length() != 6) ssn = 1;
        for (int i = 0; i < 6; i++) {
            if (Character.isDigit(request.getParameter("ssn").charAt(i))) ssn = 2;
            break;
        }

        ssn = Integer.parseInt(request.getParameter("ssn"));

        int username = Integer.parseInt(request.getParameter("username"));
        int speciality = Integer.parseInt(request.getParameter("speciality"));


        InsertTeacherDTO teacherInsertDTO = new InsertTeacherDTO();

        teacherInsertDTO.setFirstname(firstname);
        teacherInsertDTO.setLastname(lastname);
        teacherInsertDTO.setSsn(ssn);
        teacherInsertDTO.setUsername(username);
        teacherInsertDTO.setSpeciality(speciality);

        try{
            Map<String, String> errors = TeacherValidator.validate(teacherInsertDTO);
            if (!errors.isEmpty()) {
                String firstnameMessage = (errors.get("Firstname") != null) ? "Firstname " + errors.get("Firstname") : "";
                String firstnameMessage2 = (errors.get("firstname") != null) ? "Firstname " + errors.get("firstname") : "";
                String lastnameMessage = (errors.get("Lastname") != null) ? "Lastname " + errors.get("Lastname") : "";
                String lastnameMessage2 = (errors.get("lastname") != null) ? "Lastname " + errors.get("lastname") : "";
                String ssnMessage = (errors.get("ssn") != null) ? "SSN " + errors.get("ssn") : "";
                String ssnMessage2 = (errors.get("SSN") != null) ? "SSN " + errors.get("SSN") : "";
                String ssnMessage3 = (errors.get("Ssn") != null) ? "SSN " + errors.get("Ssn") : "";
                String ssnMessage4 = (errors.get("sSn") != null) ? "SSN" + errors.get("sSn") : "";
                String unMessage = (errors.get("Username") != null) ? "Username " + errors.get("Username") : "";

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
                request.setAttribute("insertedTeacher", teacherInsertDTO);
                request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/insert-errors.jsp").forward(request, response);

                Iterator<Map.Entry<String, String>> mapIt = errors.entrySet().iterator();

                while (mapIt.hasNext()) {
                    mapIt.next();
                    mapIt.remove();
                }

                return;
            }

            teacher = teacherService.insertTeacher(teacherInsertDTO);

            try{
             un = userService.getAllUsers().get(teacher.getUserId());
             sp = specialityService.getSpecialitiesMap().get(teacher.getSpecialityId());
            }catch (UserDAOException | SpecialityDAOException e) {
                request.setAttribute("ssn", SSN);
                request.setAttribute("insertedTeacher", teacherInsertDTO);
                request.setAttribute("errorMessages","");
                request.setAttribute("errorMessage", "The teacher was inserted but a problem occurred while loading the page of the inserted teacher");
                request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/insert-errors.jsp").forward(request, response);
            }
            request.setAttribute("username", un);
            request.setAttribute("speciality", sp);
            request.setAttribute("insertedTeacher", teacher);
            request.getRequestDispatcher("/school/static/templates/teachersPages/inserted.jsp").forward(request, response);
        }catch (TeacherDAOException | UserDAOException e1) {
            request.setAttribute("ssn", SSN);
            request.setAttribute("insertedTeacher", teacherInsertDTO);
            request.setAttribute("errorMessages","");
            request.setAttribute("errorMessage", "A problem occurred while inserting the new teacher, please try again");
            request.getRequestDispatcher("/school/static/templates/teachersPages/errorPages/insert-errors.jsp").forward(request, response);
        }
    }
}
