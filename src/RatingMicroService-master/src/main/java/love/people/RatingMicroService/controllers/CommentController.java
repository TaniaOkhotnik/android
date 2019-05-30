package love.people.RatingMicroService.controllers;

import love.people.RatingMicroService.controllers.dto.CommentAddDTO;
import love.people.RatingMicroService.entity.Comment;
import love.people.RatingMicroService.entity.Place;
import love.people.RatingMicroService.entity.User;
import love.people.RatingMicroService.repository.CommentRepository;
import love.people.RatingMicroService.repository.PlaceRepository;
import love.people.RatingMicroService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @PostMapping
    private String addComment(@RequestBody CommentAddDTO commentAddDTO) {
        Comment comment = new Comment();
        User user = userRepository.findByUuid(commentAddDTO.getUserUUID());
        if (user != null && placeRepository.findById(commentAddDTO.getPlaceId()).isPresent()) {
            Place place = placeRepository.findById(commentAddDTO.getPlaceId()).get();
            comment.setUser(user);
            comment.setPlace(place);
            comment.setComment(commentAddDTO.getMessage());
            commentRepository.save(comment);
            return "Respect";
        }
        return "User or Place - doesn't exist";
    }
}
