package gr.aueb.cf.schoolapppro.model;

import java.util.Date;
import java.util.Objects;

public class Student extends AbstractEntity{

    private String firstname;
    private String lastname;
    private String gender;
    private Date birthDate;
    private int  cityId;
    private int userId;

    public Student () {}

    public Student(Integer id, String firstname, String lastname, String gender, Date birthDate, int cityId, int userId) {
        super.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.cityId = cityId;
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", city=" + cityId +
                ", userDetails=" + userId +
                '}';
    }

}
