package org.toptal.model;

public class UserUpdate {
    private String oldEmail;
    private String newEmail;
    private boolean newRole;

    public String getOldEmail() {
        return oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public boolean isNewRole() {
        return newRole;
    }

    public void setNewRole(boolean newRole) {
        this.newRole = newRole;
    }


}
