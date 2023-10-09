package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertStudentDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateStudentDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.exceptions.*;

import java.text.ParseException;
import java.util.List;

public interface IStudentService {
    Student insertStudent (InsertStudentDTO insertStudentDTO) throws ParseException, StudentDAOException;

    Student updateStudent (UpdateStudentDTO updateStudentDTO) throws ParseException, StudentDAOException;

    void deleteStudent (int studentId) throws StudentDAOException;

    List<Student> getStudentByLastName(String studentLastname) throws StudentDAOException;

    List<Student> getAllStudents() throws StudentDAOException;

}
