package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;

import java.util.HashMap;
import java.util.Map;

public class SpecialityServiceImpl implements ISpecialityService{

    private ISpecialityDAO dao = new SpecialityDAOImpl();

    public SpecialityServiceImpl(ISpecialityDAO dao) {
        this.dao = dao;
    }

    @Override
    public Map<Integer, String> getSpecialitiesMap() throws SpecialityDAOException {
        Map<Integer, String> specialitiesMap;
        try{
            specialitiesMap = dao.getSpecialitiesMap();
        } catch (SpecialityDAOException e){
            e.printStackTrace();
            throw e;
        }

        return specialitiesMap;
    }
}
