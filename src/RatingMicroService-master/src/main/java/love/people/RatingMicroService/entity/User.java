package love.people.RatingMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import love.people.RatingMicroService.entity.enums.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(unique = true)
    private String login;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    @JsonIgnore
    private String uuid;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "places_user", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_place"))
    private List<Place> places;
    @JsonIgnore
    private Boolean isEnabled;
    @JsonIgnore
    @Enumerated
    private UserRole userRole;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public User setEnabled(Boolean enabled) {
        isEnabled = enabled;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
