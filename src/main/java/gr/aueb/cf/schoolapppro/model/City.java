package gr.aueb.cf.schoolapppro.model;

public class City extends AbstractEntity{

    private String cityName;

    public City(){}

    public City(Integer id, String cityName) {
        super.setId(id);
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return cityName != null ? cityName.equals(city.cityName) : city.cityName == null;
    }

    @Override
    public int hashCode() {
        return cityName != null ? cityName.hashCode() : 0;
    }
}
