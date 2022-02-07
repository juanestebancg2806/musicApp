package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document,Long> {
    @Query("SELECT name from Document")//si no se le pone nativeQuery en true se esta usando JPQL
    List<String> getNames();

    @Query("Select d from Document d where d.name = :name") //We can use also native SQL to define our query. All we have to do is set the value of the nativeQuery attribute to true and define the native SQL query in the value attribute of the annotation:
    List<Document> findByName(@Param("name") String name);


}
