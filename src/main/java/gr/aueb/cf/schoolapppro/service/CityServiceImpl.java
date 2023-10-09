package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;

import javax.swing.*;
import java.util.HashMap;

public class CityServiceImpl implements ICityService{

    private ICityDAO cityDAO;

    public CityServiceImpl(ICityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public HashMap<Integer, String> getAllCities() throws CityDAOException {
        try {
            return cityDAO.citiesMap();
        } catch (CityDAOException e){
            e.printStackTrace();
            throw e;
        }
    }
}
