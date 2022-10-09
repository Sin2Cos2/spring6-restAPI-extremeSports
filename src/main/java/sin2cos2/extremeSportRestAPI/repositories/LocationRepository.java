package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Location;

import java.util.Set;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Set<Location> getLocationByRegionIdAndCountryId(Long regionId, Long countryId);
}