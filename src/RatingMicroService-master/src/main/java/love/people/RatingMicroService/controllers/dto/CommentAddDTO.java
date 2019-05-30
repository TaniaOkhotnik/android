package love.people.RatingMicroService.controllers.dto;

public class CommentAddDTO {
    private String userUUID;
    private Long placeId;
    private String message;

    public CommentAddDTO() {
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
