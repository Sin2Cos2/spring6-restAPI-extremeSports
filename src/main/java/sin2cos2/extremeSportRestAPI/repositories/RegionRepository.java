package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Region;

import java.util.Set;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Set<Region> findByCountryId(Long id);
}