package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
}