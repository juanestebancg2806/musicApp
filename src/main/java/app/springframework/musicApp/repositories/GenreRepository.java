package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre,Long> {


}
