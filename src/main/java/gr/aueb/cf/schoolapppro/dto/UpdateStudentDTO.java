package gr.aueb.cf.schoolapppro.dto;

import java.util.Date;

public class UpdateStudentDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String gender;
    private String birthdate;
    private Integer city;
    private Integer username;

    public UpdateStudentDTO (){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UpdateStudentDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate =" + birthdate +
                ", city='" + city + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

