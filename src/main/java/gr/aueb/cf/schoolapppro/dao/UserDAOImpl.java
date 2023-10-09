package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDAOImpl implements IUserDAO{

    public User insertUser(User user) throws UserDAOException {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (? , ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
             String username = user.getUsername();
             String password = user.getPassword();

             ps.setString(1, username);
             ps.setString(2, password);

             int n = ps.executeUpdate();

             if (n >= 1){
                 return user;
             } else {
                 return null;
             }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while inserting User");
        }
    }

    public User updateUser(User user) throws UserDAOException {
        String sql = "UPDATE USERS SET USERNAME= ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);

            int n = ps.executeUpdate();

            if (n >= 1){
                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while updating User");
        }
    }

    public void deleteUser(int usersId) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = usersId;

            ps.setInt(1, id);

            int n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while deleting User with id " + usersId);
        }
    }

    @Override
    public void deleteUser(String username) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE USERNAME = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String un = username;

            ps.setString(1, un);

            int n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while deleting User with username " + username);
        }
    }

    public User getById(int usersId) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE ID= ?";
        User user = null;
        ResultSet rs = null;

        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            int id = usersId;

            ps.setInt(1,id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("USERNAME")
                        ,rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
         e.printStackTrace();
         throw new UserDAOException("Internal error occurred while getting User with id + " + usersId);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public User getByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE USERNAME= ?";
        User user = null;
        ResultSet rs = null;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,username);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("USERNAME")
                        ,rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while getting User with username + " + username);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public HashMap<Integer, String> usersMap() throws UserDAOException {
        String sql = "SELECT * FROM USERS";
        User user = null;
        ResultSet rs = null;
        HashMap<Integer,String > usersMap = new HashMap<>();

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("USERNAME")
                        ,rs.getString("PASSWORD"));
                usersMap.put(user.getId(), user.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL Error occurred while getting users map");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersMap;
    }

    @Override
    public int isTeachersUsernameRegistered(int usersId) throws UserDAOException {
        String sql = "SELECT ID FROM TEACHERS WHERE USER_ID= ?";
        ResultSet rs = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            int id = usersId;

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.first()){
                System.out.println("Returned id 1: " + rs.getInt("ID"));
                return (rs.getInt("ID"));
            } else return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while checking username's availability");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int isStudentsUsernameRegistered(int usersId) throws UserDAOException {
        String sql = "SELECT ID FROM STUDENTS WHERE USER_ID= ?";
        ResultSet rs = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            int id = usersId;

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.first()){
               return rs.getInt("ID");
            } else return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Internal error occurred while checking username's availability");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
