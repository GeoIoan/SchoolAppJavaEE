package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.Speciality;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface ISpecialityDAO {

    Speciality insert (Speciality speciality) throws SpecialityDAOException;
    Speciality update (Speciality speciality) throws SpecialityDAOException;
    void delete (int specialityId) throws SpecialityDAOException;
    void delete(String speciality) throws SpecialityDAOException;
    Speciality getSpeciality (int specialityId) throws SpecialityDAOException;
    List<Speciality> getAllSpecialities() throws SpecialityDAOException;
    Map<Integer, String> getSpecialitiesMap() throws SpecialityDAOException;
}
