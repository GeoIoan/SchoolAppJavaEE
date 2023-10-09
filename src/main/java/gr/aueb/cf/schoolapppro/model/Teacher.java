package gr.aueb.cf.schoolapppro.model;

import java.util.Objects;

public class Teacher extends AbstractEntity{

    private int ssn;
    private String firstname;
    private String lastname;

    private int specialityId;

    private int userId;

    public Teacher () {}

    public Teacher (Integer id, int ssn, String firstname, String lastname, int specialityId, int userId) {
        super.setId(id);
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialityId = specialityId;
        this.userId = userId;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
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

    public int getSpecialityId() {return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "ssn=" + ssn +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", speciality=" + specialityId +
                ", userDetails=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (ssn != teacher.ssn) return false;
        if (specialityId != teacher.specialityId) return false;
        if (userId != teacher.userId) return false;
        if (!Objects.equals(firstname, teacher.firstname)) return false;
        return Objects.equals(lastname, teacher.lastname);
    }

    @Override
    public int hashCode() {
        int result = ssn;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + specialityId;
        result = 31 * result + userId;
        return result;
    }
}
