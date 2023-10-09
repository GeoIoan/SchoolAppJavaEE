package gr.aueb.cf.schoolapppro.service;


import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertTeacherDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateTeacherDTO;
import gr.aueb.cf.schoolapppro.model.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher insertTeacher (InsertTeacherDTO insertTeacherDTO) throws  TeacherDAOException;

    Teacher updateTeacher (UpdateTeacherDTO updateTeacherDTO) throws  TeacherDAOException;

    void deleteTeacher (int teacherId) throws TeacherDAOException;

    List <Teacher> getTeacherByLastName(String teacherLastname) throws TeacherDAOException;

    List<Teacher> getAllTeachers() throws TeacherDAOException;
}
