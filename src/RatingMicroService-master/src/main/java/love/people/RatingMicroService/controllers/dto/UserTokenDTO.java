package love.people.RatingMicroService.controllers.dto;

public class UserTokenDTO {
    private String login;
    private String uuid;
    private String userRole;

    public UserTokenDTO() {
    }

    public UserTokenDTO(String login, String uuid, String userRole) {
        this.login = login;
        this.uuid = uuid;
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
