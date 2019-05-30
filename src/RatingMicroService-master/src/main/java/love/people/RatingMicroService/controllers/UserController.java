package love.people.RatingMicroService.controllers;

import love.people.RatingMicroService.controllers.dto.UserTokenDTO;
import love.people.RatingMicroService.entity.User;
import love.people.RatingMicroService.entity.enums.UserRole;
import love.people.RatingMicroService.repository.UserRepository;
import love.people.RatingMicroService.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${host.name}")
    private String hostName;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping
    private String addUser(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEnabled(false);
        user.setUserRole(UserRole.USER_ROLE);
        user.setUuid(UUID.randomUUID().toString());
        try {
            userRepository.save(user);
            mailSenderService.sendMail("thank you", hostName + "/users/" + user.getUuid(), login);
        } catch (Exception e) {
            return "Duplicated user login";
        }
        return "Respect";
    }

    @GetMapping("/{uuid}")
    private String confirmEmail(@PathVariable String uuid) {
        User user = userRepository.findByUuid(uuid);
        if (user.getEnabled()) {
            return "Stop doing that";
        }
        userRepository.save(user.setEnabled(true));
        return "true";
    }

    @PostMapping(value = "/auth")
    private UserTokenDTO authorise(@RequestParam String login, @RequestParam String password) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if(user != null && user.getEnabled()){
            return new UserTokenDTO(user.getLogin(), user.getUuid(), user.getUserRole().name());
        }
        return null;
    }

    @GetMapping("/important")
    private String important(){
        return "nice";
    }

    @GetMapping("/addAdmin")
    private String addAdmin(){
        try {
            User user = new User();
            user.setLogin("0@0.0");
            user.setPassword("123456");
            user.setEnabled(true);
            user.setUserRole(UserRole.ADMIN_ROLE);
            user.setUuid("0");
            userRepository.save(user);
        } catch (Exception e) {
            return "admin exists, stop that";
        }
        return "admin";
    }
}
