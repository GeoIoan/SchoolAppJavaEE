package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;

import javax.swing.*;
import java.util.HashMap;

public interface ICityService {
    HashMap<Integer, String> getAllCities() throws CityDAOException;
}
