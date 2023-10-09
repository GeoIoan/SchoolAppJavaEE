package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.*;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertStudentDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateStudentDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.exceptions.*;
import gr.aueb.cf.schoolapppro.service.util.DateUtil;

import java.text.ParseException;
import java.util.List;

public class StudentServiceImpl implements IStudentService{

    private IStudentDAO studentDAO;

    public StudentServiceImpl(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student insertStudent(InsertStudentDTO insertStudentDTO) throws ParseException, StudentDAOException {
        Student student;
        try {
                student = studentMapper(insertStudentDTO);
                studentDAO.insert(student);
        } catch (StudentDAOException e){
            e.printStackTrace();
            throw e;
        }
        return student;
    }

    @Override
    public Student updateStudent(UpdateStudentDTO updateStudentDTO) throws
            ParseException, StudentDAOException {
        Student student;
        try {
                student = studentMapper(updateStudentDTO);
                studentDAO.update(student);
        } catch (StudentDAOException | ParseException e){
            e.printStackTrace();
            throw e;
        }
        return student;
    }

    @Override
    public void deleteStudent(int studentId) throws StudentDAOException {
        try {
            studentDAO.delete(studentId);
        } catch (StudentDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Student> getStudentByLastName(String studentLastname) throws StudentDAOException {
        List<Student> students;
        try{
            students = studentDAO.getStudentByLastname(studentLastname);
                return students;
        } catch (StudentDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Student> getAllStudents() throws StudentDAOException {
        List<Student> students;
        try{
            students = studentDAO.getAllStudents();
               return students;
        } catch (StudentDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    private Student studentMapper (InsertStudentDTO insertStudentDTO) throws ParseException {
        Student student = new Student();
                student.setFirstname(insertStudentDTO.getFirstname());
                student.setLastname(insertStudentDTO.getLastname());
                student.setBirthDate(DateUtil.toDate(insertStudentDTO.getBirthdate()));
                student.setUserId(insertStudentDTO.getUsername());
                student.setGender(insertStudentDTO.getGender());
                student.setCityId((insertStudentDTO.getCity()));
        return student;
    }

    private Student studentMapper (UpdateStudentDTO updateStudentDTO) throws ParseException {
        Student student = new Student();
            student.setId(updateStudentDTO.getId());
            student.setFirstname(updateStudentDTO.getFirstname());
            student.setLastname(updateStudentDTO.getLastname());
            student.setBirthDate(DateUtil.toDate(updateStudentDTO.getBirthdate()));
            student.setUserId(updateStudentDTO.getUsername());
            student.setGender(updateStudentDTO.getGender());
            student.setCityId(updateStudentDTO.getCity());
        return student;
    }
}
