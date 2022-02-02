package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Long> {


}
