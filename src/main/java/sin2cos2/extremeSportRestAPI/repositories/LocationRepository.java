package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}