package org.toptal.model;

public class TimeZoneDto {
    private Integer id;
    private String name;
    private String city;
    private Integer hourDiff;
    private Integer minDiff;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getHourDiff() {
        return hourDiff;
    }

    public void setHourDiff(Integer hourDiff) {
        this.hourDiff = hourDiff;
    }

    public Integer getMinDiff() {
        return minDiff;
    }

    public void setMinDiff(Integer minDiff) {
        this.minDiff = minDiff;
    }


}
