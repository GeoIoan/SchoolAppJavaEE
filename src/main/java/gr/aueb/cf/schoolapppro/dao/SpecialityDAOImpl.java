package gr.aueb.cf.schoolapppro.dao;


import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecialityDAOImpl implements ISpecialityDAO{

    @Override
    public Speciality insert(Speciality speciality) throws SpecialityDAOException {
        String sql = "INSERT INTO SPECIALITIES(SPECIALITY) VALUES (?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            String s = speciality.getSpeciality();

            ps.setString(1,s);


            int n = ps.executeUpdate();

            if (n >= 1){
                return speciality;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal error occurred while inserting Speciality");
        }
    }

    @Override
    public Speciality update(Speciality speciality) throws SpecialityDAOException {
        String sql = "UPDATE SPECIALITIES SET SPECIALITY= ? WHERE ID= ?";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            String s = speciality.getSpeciality();
            int sId = speciality.getId();

            ps.setString(1,s);
            ps.setInt(2, sId);


            int n = ps.executeUpdate();

            if (n >= 1){
                return speciality;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal error occurred while updating Speciality");
        }
    }

    @Override
    public void delete(int specialityId) throws SpecialityDAOException {
        String sql = "DELETE * FROM SPECIALITIES WHERE ID= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int sId = specialityId;

            ps.setInt(1, sId);

            int n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal error occurred while deleting Speciality");
        }
    }

    @Override
    public void delete(String speciality) throws SpecialityDAOException {
        String sql = "DELETE * FROM SPECIALITIES WHERE SPECIALITY= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String s = speciality;

            ps.setString(1, s);

            int n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal error occurred while deleting Speciality");
        }
    }

    @Override
    public Speciality getSpeciality(int specialityId) throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES WHERE ID= ?";
        Speciality speciality= null;
        ResultSet rs = null;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            int sId = specialityId;

            ps.setInt(1,sId);
            rs = ps.executeQuery();

            if (rs.next()) {
                speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal Error occurred when getting speciality with Id  " + specialityId);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return speciality;
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES";
        Speciality speciality = null;
        ResultSet rs = null;
        List<Speciality> specialities  = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            rs = ps.executeQuery();

            while (rs.next()) {
                speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                specialities.add(speciality);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal error occurred while getting all Specialities");
        } finally {
            try{
                if (rs != null) rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return specialities;
    }

    @Override
    public Map<Integer, String> getSpecialitiesMap() throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES";
        Speciality speciality = null;
        ResultSet rs = null;
        Map<Integer, String> specialityMap = new HashMap<>();


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            rs = ps.executeQuery();


            while (rs.next()) {
                speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                specialityMap.put(speciality.getId(), speciality.getSpeciality());
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Internal error occurred while getting all the specialities");
        } finally {
            try{
                if (rs != null) rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return specialityMap;
    }
}

