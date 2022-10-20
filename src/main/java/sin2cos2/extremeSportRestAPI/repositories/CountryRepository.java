package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Page<Country> getCountriesByName(String name, Pageable page);
}