package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.model.Student;

import java.util.List;

public interface IStudentDAO {

   Student insert (Student student) throws StudentDAOException;
   Student update (Student student) throws StudentDAOException;
   void delete (int studentId) throws StudentDAOException;
   Student getStudentById(int studentId) throws StudentDAOException;
   List<Student> getStudentByLastname(String lastname) throws StudentDAOException;
   List<Student> getAllStudents() throws StudentDAOException;
}
