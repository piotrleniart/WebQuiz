package engine.repository;

import engine.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
