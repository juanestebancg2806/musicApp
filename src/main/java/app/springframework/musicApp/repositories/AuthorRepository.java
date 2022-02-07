package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Long> {
    @Query("Select a from Author a left join Country c on c = a.country")
    List<Author> getAuthorsInfo();

    @Query("Select a from Author a where a.names = :names") //We can use also native SQL to define our query. All we have to do is set the value of the nativeQuery attribute to true and define the native SQL query in the value attribute of the annotation:
    List<Author> findByName(@Param("names") String names);


}
