package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.service.util.DBUtil;
import gr.aueb.cf.schoolapppro.service.util.DateUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME, GENDER, BIRTH_DATE, CITY_ID, USER_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            String firstname = student.getFirstname();
            String lastname = student.getLastname();
            String gender = student.getGender();
            Date birthDate = student.getBirthDate();
            int cityId = student.getCityId();
            int userId = student.getUserId();

            System.out.println(DateUtil.toSQLDate(birthDate));

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, String.valueOf(gender));
            ps.setDate(4, DateUtil.toSQLDate(birthDate));
            ps.setInt(5, cityId);
            ps.setInt(6, userId);

            int n = ps.executeUpdate();

            if (n >= 1) {
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("Internal error occurred while inserting student");
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME= ?, LASTNAME= ?, GENDER= ? , BIRTH_DATE= ?," +
                "CITY_ID = ?, USER_ID= ? WHERE ID= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String firstname = student.getFirstname();
            String lastname = student.getLastname();
            String gender = student.getGender();
            Date birthDate = student.getBirthDate();
            int cityId = student.getCityId();
            int userId = student.getUserId();
            int id = student.getId();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, String.valueOf(gender));
            ps.setDate(4, DateUtil.toSQLDate(birthDate));
            ps.setInt(5, cityId);
            ps.setInt(6, userId);
            ps.setInt(7, id);

            int n = ps.executeUpdate();

            if (n >= 1) {
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("Internal error occurred while updating Student");
        }
    }

    @Override
    public void delete(int studentId) throws StudentDAOException {
        String sql = "DELETE FROM STUDENTS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = studentId;

            ps.setInt(1, id);

            int n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("Internal error occurred while deleting Student");
        }
    }

    @Override
    public Student getStudentById(int studentId) throws StudentDAOException {
        Student student = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM STUDENTS WHERE ID= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = studentId;

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"), rs.getString("GENDER"),
                        rs.getDate("BIRTH_DATE"), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
            }
        } catch (SQLException e) {
                e.printStackTrace();
                throw new StudentDAOException("Internal error occurred while getting Student");
            } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        }
        return student;
    }

    @Override
    public List<Student> getStudentByLastname(String lastname) throws StudentDAOException {
        List<Student> students = new ArrayList<>();
        Student student = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String ln = lastname + "%";

            System.out.println(ln);

            ps.setString(1, ln);

            rs = ps.executeQuery();

            while (rs.next()) {
                student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"), rs.getString("GENDER"),
                        rs.getDate("BIRTH_DATE"), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("Internal error occurred while deleting Student");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        }
        return students;
    }

    @Override
    public List<Student> getAllStudents() throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS";
        List<Student> students = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            rs = ps.executeQuery();

            while (rs.next()){
                Student student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"), rs.getString("GENDER"),
                        rs.getDate("BIRTH_DATE"), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
                students.add(student);
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new StudentDAOException("Internal Error occurred while getting all students");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        }
        return students;
    }
}
