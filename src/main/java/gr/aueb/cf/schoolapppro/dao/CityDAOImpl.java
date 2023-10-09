package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.model.City;
import gr.aueb.cf.schoolapppro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityDAOImpl implements ICityDAO{

    @Override
    public City insert(City city) throws CityDAOException {
        String sql = "INSERT INTO CITIES(CITY) VALUES (?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            String c = city.getCityName();

            ps.setString(1,c);


            int n = ps.executeUpdate();

            if (n >= 1){
                return city;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal error occurred while inserting City");
        }
    }

    @Override
    public City update(City city) throws CityDAOException {
        String sql = "UPDATE CITIES SET CITY= ? WHERE ID= ?)";
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            String c = city.getCityName();
            int cityId = city.getId();

            ps.setString(1,c);
            ps.setInt(2,cityId);


            int n = ps.executeUpdate();

            if (n >= 1){
                return city;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal error occurred while updating City");
        }
    }

    @Override
    public void delete(int cityId) throws CityDAOException {
        String sql = "DELETE * FROM CITIES WHERE ID= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int cId = cityId;

            ps.setInt(1, cId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal error occurred while deleting City");
        }
    }

    @Override
    public void delete(String cityName) throws CityDAOException {
        String sql = "DELETE * FROM CITIES WHERE CITY= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String cName = cityName;

            ps.setString(1, cName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal error occurred while deleting City");
        }
    }

    @Override
    public City getCity(int cityId) throws CityDAOException {
        String sql = "SELECT * FROM CITIES WHERE ID= ?";
        City city= null;
        ResultSet rs = null;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            int cId = cityId;

            ps.setInt(1,cId);
            rs = ps.executeQuery();

            if (rs.next()) {
                city = new City(rs.getInt("ID"), rs.getString("CITY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal Error occurred when getting city with Id  " + cityId);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return city;
    }

    @Override
    public List<City> getAllCities() throws CityDAOException {
        List<City> cities = null;
        City city = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CITIES";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            rs = ps.executeQuery();

            while (rs.next()) {
                city = new City(rs.getInt("ID"), rs.getString("CITY"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal error occurred while getting all cities");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        }
        return cities;
    }


    @Override
    public HashMap<Integer, String> citiesMap() throws CityDAOException {
        String sql = "SELECT * FROM CITIES";
        City city = null;
        ResultSet rs = null;
        HashMap<Integer, String> citiesMap = new HashMap<>();

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            rs = ps.executeQuery();

            while (rs.next()) {
                city = new City(rs.getInt("ID"), rs.getString("CITY"));
                citiesMap.put(city.getId(), city.getCityName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("Internal error occurred while getting cities map");
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return citiesMap;
    }
}
