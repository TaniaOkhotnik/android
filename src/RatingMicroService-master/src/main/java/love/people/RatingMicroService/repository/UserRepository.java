package love.people.RatingMicroService.repository;

import love.people.RatingMicroService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);
    User findByLoginAndPassword(String login, String password);
}
