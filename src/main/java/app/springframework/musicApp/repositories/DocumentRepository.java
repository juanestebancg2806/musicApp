package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document,Long> {



}
