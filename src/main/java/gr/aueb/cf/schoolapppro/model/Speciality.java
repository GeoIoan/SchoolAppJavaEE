package gr.aueb.cf.schoolapppro.model;

public class Speciality extends AbstractEntity{
    private String speciality;

    public Speciality () {}

    public Speciality (Integer id, String speciality) {
        super.setId(id);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "speciality='" + speciality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        return speciality != null ? speciality.equals(that.speciality) : that.speciality == null;
    }

    @Override
    public int hashCode() {
        return speciality != null ? speciality.hashCode() : 0;
    }
}
