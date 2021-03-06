package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByNames(String names);

}
