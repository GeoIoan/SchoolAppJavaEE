package gr.aueb.cf.schoolapppro.model;

public abstract class AbstractEntity {
    private Integer id;

    public AbstractEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
