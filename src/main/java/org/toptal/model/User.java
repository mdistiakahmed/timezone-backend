package org.toptal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;


    @OneToOne
    @JoinTable(name = "USER_ROLES",
            joinColumns =
                    { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns =
                    { @JoinColumn(name = "ROLE_ID") })
    private Role role;



    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timezone> timezonelist;

    public List<Timezone> getTimezonelist() {
        return timezonelist;
    }

    public void setTimezonelist(List<Timezone> timezonelist) {
        this.timezonelist = timezonelist;
    }





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}