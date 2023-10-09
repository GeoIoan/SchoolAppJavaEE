package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO{

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (SSN, FIRSTNAME, LASTNAME, SPECIALITY_ID, USER_ID) " +
                "VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            int ssn = teacher.getSsn();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();
            int sId = teacher.getSpecialityId();
            int uId = teacher.getUserId();

            ps.setInt(1, ssn);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setInt(4, sId);
            ps.setInt(5, uId);

            int n = ps.executeUpdate();
            if (n >= 1) {
                return teacher;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDAOException("Internal error occurred while inserting teacher: " + teacher);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET SSN= ?, FIRSTNAME= ? , LASTNAME = ?, SPECIALITY_ID= ? , USER_ID= ? WHERE ID= ?";


        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            int ssn = teacher.getSsn();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();
            int sId = teacher.getSpecialityId();
            int uId = teacher.getUserId();
            int tId = teacher.getId();

            ps.setInt(1, ssn);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setInt(4, sId);
            ps.setInt(5, uId);
            ps.setInt(6, tId);

            int n = ps.executeUpdate();
            if (n >= 1) {
                return teacher;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new TeacherDAOException("Internal error occurred while updating teacher: " + teacher);
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("Internal error occurred while deleting teacher with id: " + id);
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()){
                Teacher teacher = new Teacher(rs.getInt("ID"), rs.getInt("ssn"), rs.getString("FIRSTNAME")
                        ,rs.getString("LASTNAME"),rs.getInt("SPECIALITY_ID"),rs.getInt("USER_ID"));
                teachers.add(teacher);
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new TeacherDAOException("Internal error occurred while getting teacher with lastname: " + lastname);
        } finally {
            try {
                if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();}
        }
        return teachers;
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE ID = ?";
        Teacher teacher = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()){
                teacher = new Teacher(rs.getInt("ID"), rs.getInt("ssn"), rs.getString("FIRSTNAME")
                        ,rs.getString("LASTNAME"),rs.getInt("SPECIALITY_ID"),rs.getInt("USER_ID"));
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new TeacherDAOException("Internal error occurred while getting teacher with id:" + id);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            rs = ps.executeQuery();

            while (rs.next()){
                Teacher teacher = new Teacher(rs.getInt("ID"), rs.getInt("ssn"), rs.getString("FIRSTNAME")
                        ,rs.getString("LASTNAME"),rs.getInt("SPECIALITY_ID"),rs.getInt("USER_ID"));
                teachers.add(teacher);
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new TeacherDAOException("Internal error occurred while getting all teachers");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        }
        return teachers;
    }
}

