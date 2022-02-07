package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country,Long> {
    @Query("SELECT name from Country")//si no se le pone nativeQuery en true se esta usando JPQL
    List<String> getNames();

    @Query("Select c from Country c where c.name = :name") //We can use also native SQL to define our query. All we have to do is set the value of the nativeQuery attribute to true and define the native SQL query in the value attribute of the annotation:
    List<Country> findByName(@Param("name") String name);


}
