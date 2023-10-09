package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.model.City;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICityDAO {

    City insert (City city) throws CityDAOException;
    City update (City city) throws CityDAOException;
    void delete (int cityId) throws CityDAOException;
    void delete(String cityName) throws CityDAOException;
    City getCity(int cityId) throws CityDAOException;
    List<City> getAllCities() throws CityDAOException;
    HashMap<Integer,String> citiesMap() throws CityDAOException;
}
