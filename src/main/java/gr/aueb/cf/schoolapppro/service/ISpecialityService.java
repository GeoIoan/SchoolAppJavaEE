package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;

import java.util.Map;

public interface ISpecialityService {
    Map<Integer, String> getSpecialitiesMap () throws SpecialityDAOException;
}
