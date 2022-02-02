package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
