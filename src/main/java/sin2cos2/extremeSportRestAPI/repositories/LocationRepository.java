package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Location;

import java.util.Set;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Set<Location> getLocationByRegionId(Long regionId);

    Set<Location> getLocationByCountryId(Long countryId);

    void deleteLocationsByRegionId(Long regionId);

    void deleteLocationsByCountryId(Long countryId);
}