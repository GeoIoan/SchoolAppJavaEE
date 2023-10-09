package gr.aueb.cf.schoolapppro.controller.StudentCotnrollers;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertStudentDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.*;
import gr.aueb.cf.schoolapppro.validator.StudentValidator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/insert-student")

public class InsertStudentController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();

    private final ICityDAO cityDAO = new CityDAOImpl();

    private final ICityService cityService = new CityServiceImpl(cityDAO);

    private final IUserService userService = new UserServiceImpl(userDAO);

    private final IStudentDAO studentDAO = new StudentDAOImpl();

    private final IStudentService studentService = new StudentServiceImpl(studentDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/school-app/students-menu").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InsertStudentDTO insertStudentDTO = new InsertStudentDTO();
        String birthdate = request.getParameter("birthdate");
        try{
            String firstname = request.getParameter("firstname").trim();
            String lastname = request.getParameter("lastname").trim();
            String gender = request.getParameter("gender");

            int username = Integer.parseInt(request.getParameter("username"));
            int city = Integer.parseInt(request.getParameter("city"));



            insertStudentDTO.setFirstname(firstname);
            insertStudentDTO.setLastname(lastname);
            insertStudentDTO.setGender(gender);
            insertStudentDTO.setBirthdate(birthdate);
            insertStudentDTO.setUsername(username);
            insertStudentDTO.setCity(city);

            Map<String, String> errors = StudentValidator.validate(insertStudentDTO);
            if (!errors.isEmpty()) {
                String firstnameMessage = (errors.get("Firstname") != null) ? "Firstname " + errors.get("Firstname") : "";
                String firstnameMessage2 = (errors.get("firstname") != null) ? "Firstname " + errors.get("firstname") : "";
                String lastnameMessage = (errors.get("Lastname") != null) ? "Lastname " + errors.get("Lastname") : "";
                String lastnameMessage2 = (errors.get("lastname") != null) ? "Lastname " + errors.get("lastname") : "";
                String unMessage = (errors.get("Username") != null) ? "Username " + errors.get("Username") : "";
                String bdMessage = (errors.get("Birthdate") != null) ? "Birthdate " + errors.get("Birthdate") : "";
                String genderMessage = (errors.get("gender") != null) ? "Gender " + errors.get("gender") : "";

                List<String> errorMessages = new ArrayList<>();

                errorMessages.add("The following errors occurred while executing the request");
                if (!firstnameMessage.equals("")) errorMessages.add(firstnameMessage);
                if (!firstnameMessage2.equals("")) errorMessages.add(firstnameMessage2);
                if (!lastnameMessage.equals("")) errorMessages.add(lastnameMessage);
                if (!lastnameMessage2.equals("")) errorMessages.add(lastnameMessage2);
                if (!bdMessage.equals("")) errorMessages.add(bdMessage);
                if (!unMessage.equals("")) errorMessages.add(unMessage);
                if (!genderMessage.equals("")) errorMessages.add(genderMessage);

                request.setAttribute("errorMessage", "");
                request.setAttribute("birthdate", birthdate);
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("insertedStudent", insertStudentDTO);
                request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/insert-errors.jsp").forward(request, response);

                Iterator<Map.Entry<String, String>> mapIt = errors.entrySet().iterator();

                while (mapIt.hasNext()) {
                    mapIt.next();
                    mapIt.remove();
                }

                return;
            }

            Student student = studentService.insertStudent(insertStudentDTO);
            String sBirthdate = request.getParameter("birthdate");
            request.setAttribute("birthdate", sBirthdate);
            request.setAttribute("insertedStudent", student);

            try{
                String sUsername = userService.getAllUsers().get(student.getUserId());
                String sCity = cityService.getAllCities().get(student.getCityId());

                request.setAttribute("username",sUsername);
                request.setAttribute("city",sCity);
            }catch (UserDAOException | CityDAOException e) {
                request.setAttribute("birthdate", birthdate);
                request.setAttribute("insertedStudent", insertStudentDTO);
                request.setAttribute("errorMessages", "");
                request.setAttribute("errorMessage", "The student was inserted but an internal error occurred while loading the inserted student's page");
                request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/insert-errors.jsp").forward(request, response);
            }
                request.getRequestDispatcher("/school/static/templates/studentsPages/inserted.jsp").forward(request, response);

        } catch (ParseException e1) {
            request.setAttribute("insertedStudent", insertStudentDTO);
            request.setAttribute("birthdate", birthdate);
            request.setAttribute("errorMessages","");
            request.setAttribute("errorMessage", "Wrong date format");
            request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/insert-errors.jsp").forward(request, response);
        } catch (StudentDAOException | UserDAOException e2) {
            request.setAttribute("insertedStudent", insertStudentDTO);
            request.setAttribute("birthdate", birthdate);
            request.setAttribute("errorMessages","");
            String message = "Internal error occurred while inserting the student";
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/insert-errors.jsp")
                    .forward(request, response);}
    }
}



