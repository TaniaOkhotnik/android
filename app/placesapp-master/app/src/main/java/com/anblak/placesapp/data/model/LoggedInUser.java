package com.anblak.placesapp.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String login;
    private String uuid;
    private String userRole;
    private Boolean enabled;

    public LoggedInUser(String login) {
        this.login = login;
    }

    public LoggedInUser(String login, String uuid, String userRole) {
        this.login = login;
        this.uuid = uuid;
        this.userRole = userRole;
    }

    public LoggedInUser() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
