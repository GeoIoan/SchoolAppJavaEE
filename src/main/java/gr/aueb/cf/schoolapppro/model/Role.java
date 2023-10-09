package gr.aueb.cf.schoolapppro.model;

import java.util.Objects;

public class Role extends AbstractEntity{
    private String role;

    public Role(int id, String role) {
        super.setId(id);
        this.role = role;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return Objects.equals(this.role, role.role);
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}
