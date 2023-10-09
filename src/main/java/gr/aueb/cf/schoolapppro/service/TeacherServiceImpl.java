package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.InsertTeacherDTO;
import gr.aueb.cf.schoolapppro.dto.UpdateTeacherDTO;
import gr.aueb.cf.schoolapppro.model.Teacher;

import java.util.List;

;

public class TeacherServiceImpl implements ITeacherService{

    private ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(InsertTeacherDTO insertTeacherDTO) throws TeacherDAOException {
        Teacher teacher;
        try {
                teacher = teacherMapper(insertTeacherDTO);
                teacherDAO.insert(teacher);
        } catch (TeacherDAOException e){
            e.printStackTrace();
            throw e;
        }
        return teacher;
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherDTO updateTeacherDTO) throws TeacherDAOException {
        Teacher teacher;
        try {
                teacher = teacherMapper(updateTeacherDTO);
                teacherDAO.update(teacher);
        } catch ( TeacherDAOException e){
            e.printStackTrace();
            throw e;
        }
        return teacher;
    }

    @Override
    public void deleteTeacher(int teacherId) throws TeacherDAOException {
            try {
                teacherDAO.delete(teacherId);
            } catch (TeacherDAOException e) {
                e.printStackTrace();
                throw e;
            }
    }

    @Override
    public List <Teacher> getTeacherByLastName(String teacherLastname) throws  TeacherDAOException {
        List<Teacher> teachers;
         try{
             teachers = teacherDAO.getByLastname(teacherLastname);
                 return teachers;
         } catch (TeacherDAOException e) {
             e.printStackTrace();
             throw e;
         }
    }

    @Override
    public List<Teacher> getAllTeachers() throws TeacherDAOException {
        List<Teacher> teachers ;
        try{
            teachers = teacherDAO.getAllTeachers();
                return teachers;
        } catch ( TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Teacher teacherMapper (InsertTeacherDTO insertTeacherDTO)  {
        Teacher teacher = new Teacher();

        teacher.setFirstname(insertTeacherDTO.getFirstname());
        teacher.setLastname(insertTeacherDTO.getLastname());
        teacher.setSsn(insertTeacherDTO.getSsn());
        teacher.setUserId(insertTeacherDTO.getUsername());
        teacher.setSpecialityId(insertTeacherDTO.getSpeciality());

        return teacher;
    }

    private Teacher teacherMapper (UpdateTeacherDTO updateTeacherDTO) {
        Teacher teacher = new Teacher();

        teacher.setId(updateTeacherDTO.getId());
        teacher.setFirstname(updateTeacherDTO.getFirstname());
        teacher.setLastname(updateTeacherDTO.getLastname());
        teacher.setSsn(updateTeacherDTO.getSsn());
        teacher.setUserId(updateTeacherDTO.getUsernameId());
        teacher.setSpecialityId(updateTeacherDTO.getSpecialityId());

        return teacher;
    }
}
