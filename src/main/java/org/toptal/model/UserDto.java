package org.toptal.model;

public class UserDto {

    private String email;
    private String password;
    private boolean hasAdminRole;

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

    public Boolean getHasAdminRole() {
        return hasAdminRole;
    }

    public void setHasAdminRole(Boolean hasAdminRole) {
        this.hasAdminRole = hasAdminRole;
    }

}