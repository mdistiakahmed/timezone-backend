package org.toptal.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Timezone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private Integer hourdiff;

    @Column
    private Integer mindiff;


    @JsonBackReference
    @ManyToOne
    private User user;

    public Integer getHourdiff() {
        return hourdiff;
    }

    public void setHourdiff(Integer hourdiff) {
        this.hourdiff = hourdiff;
    }

    public Integer getMindiff() {
        return mindiff;
    }

    public void setMindiff(Integer mindiff) {
        this.mindiff = mindiff;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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




}

