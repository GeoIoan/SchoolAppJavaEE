package gr.aueb.cf.schoolapppro.controller.StudentCotnrollers;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.*;
import gr.aueb.cf.schoolapppro.dto.UpdateStudentDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateTeacherDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.*;
import gr.aueb.cf.schoolapppro.validator.StudentValidator;
import gr.aueb.cf.schoolapppro.validator.TeacherValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@WebServlet("/school-app/update-student")
public class UpdateStudentController extends HttpServlet {

        private static final long serialVersionUID = 1L;
        private final IUserDAO userDAO = new UserDAOImpl();

        private final IUserService userService = new UserServiceImpl(userDAO);

        private final ICityDAO cityDAO = new CityDAOImpl();

        private final ICityService cityService = new CityServiceImpl(cityDAO);

        private final IStudentDAO studentDAO = new StudentDAOImpl();

        private final IStudentService studentService = new StudentServiceImpl(studentDAO);

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            Map<Integer, String> userMap;
            Map<Integer, String> citiesMap;


            try{
                userMap = userService.getAllUsers();
                citiesMap = cityService.getAllCities();
                request.setAttribute("userMap", userMap);
                request.setAttribute("citiesMap", citiesMap);
                request.getRequestDispatcher("/school/static/templates/studentsPages/update.jsp")
                        .forward(request, response);
            }catch (UserDAOException | CityDAOException e) {
                request.setAttribute("errorMessage", "a problem occurred while loading the page, please try again");
                request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/loading-error.jsp")
                        .forward(request, response);
            }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            Map<Integer, String> userMap;
            Map<Integer, String> citiesMap;

            int id = Integer.parseInt(request.getParameter("id"));
            String firstname = request.getParameter("firstname").trim();
            String lastname = request.getParameter("lastname").trim();
            String gender = request.getParameter("gender");
            String birthdate = request.getParameter("birthdate");
            int usernameId = Integer.parseInt(request.getParameter("username"));
            int cityId = Integer.parseInt(request.getParameter("city"));

            UpdateStudentDTO newStudentDTO = new UpdateStudentDTO();
            newStudentDTO.setId(id);
            newStudentDTO.setFirstname(firstname);
            newStudentDTO.setLastname(lastname);
            newStudentDTO.setGender(gender);
            newStudentDTO.setBirthdate(birthdate);
            newStudentDTO.setUsername(usernameId);
            newStudentDTO.setCity(cityId);


            try {
                Map<String, String > errors = StudentValidator.validate(newStudentDTO);

                if (!errors.isEmpty()) {
                    String firstnameMessage = (errors.get("Firstname") != null) ? "Firstname" + errors.get("Firstname") : "";
                    String firstnameMessage2 = (errors.get("firstname") != null) ? "Firstname" + errors.get("firstname") : "";
                    String lastnameMessage = (errors.get("Lastname") != null) ? "Lastname" + errors.get("Lastname") : "";
                    String lastnameMessage2 = (errors.get("lastname") != null) ? "Lastname" + errors.get("lastname") : "";
                    String unMessage = (errors.get("Username") != null) ? "Username" + errors.get("Username") : "";
                    String bdMessage = (errors.get("Birthdate") != null) ? "Birthdate" + errors.get("Birthdate") : "";
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
                    request.setAttribute("errorMessages", errorMessages);
                    request.setAttribute("studentToUpdate", newStudentDTO);
                    request.setAttribute("birthdate", birthdate);

                    Iterator<Map.Entry<String, String>> mapIt = errors.entrySet().iterator();

                    while (mapIt.hasNext()) {
                        mapIt.next();
                        mapIt.remove();
                    }

                    request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/update-errors.jsp").forward(request, response);

                    return;
                }

                    Student student = studentService.updateStudent(newStudentDTO);
                    request.setAttribute("birthdate", birthdate);
                    request.setAttribute("student", student);

                    try{
                        userMap = userService.getAllUsers();
                        citiesMap = cityService.getAllCities();
                        String username = userMap.get(usernameId);
                        String city = citiesMap.get(cityId);
                        request.setAttribute("username", username);
                        request.setAttribute("city", city);
                    } catch (UserDAOException | CityDAOException e) {
                        String message = "The student was updated but an internal error occurred while loading the updated student's page";
                        request.setAttribute("studentToUpdate", newStudentDTO);
                        request.setAttribute("birthdate", birthdate);
                        request.setAttribute("errorMessages", "");
                        request.setAttribute("errorMessage", message);
                        request.getRequestDispatcher("/school/static/templates/templates/studentsPages/errorPages/update-errors.jsp")
                                .forward(request, response);
                    }

                    request.getRequestDispatcher("/school/static/templates/studentsPages/updated.jsp")
                            .forward(request, response);

                } catch (ParseException e1) {
                String message = "Wrong birthdate format";
                request.setAttribute("studentToUpdate", newStudentDTO);
                request.setAttribute("birthdate", birthdate);
                request.setAttribute("errorMessages","");
                request.setAttribute("errorMessage", message);
                request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/update-errors.jsp")
                        .forward(request, response);
            } catch (StudentDAOException | UserDAOException e2) {
                String message = "A problem occurred while updating the student";
                request.setAttribute("studentToUpdate", newStudentDTO);
                request.setAttribute("birthdate", birthdate);
                request.setAttribute("errorMessages","");
                request.setAttribute("errorMessage", message);
                request.getRequestDispatcher("/school/static/templates/studentsPages/errorPages/update-errors.jsp")
                        .forward(request, response);}
        }
}

