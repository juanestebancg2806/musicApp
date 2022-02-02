package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {

}
