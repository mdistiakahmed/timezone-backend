package org.toptal.model;

public class UserDto {

    private String username;
    private String password;
    private String email;
    private Boolean hasAdminRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getHasAdminRole() {
        return hasAdminRole;
    }

    public void setHasAdminRole(Boolean hasAdminRole) {
        this.hasAdminRole = hasAdminRole;
    }


    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

}