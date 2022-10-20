package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

    Page<Sport> getSportsByName(String name, Pageable page);
}