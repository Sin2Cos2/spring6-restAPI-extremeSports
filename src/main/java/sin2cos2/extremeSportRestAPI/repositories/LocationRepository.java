package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Location;

import java.util.Set;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> getLocationByRegionId(Long regionId, Pageable page);

    Page<Location> getLocationByCountryId(Long countryId, Pageable page);

    void deleteLocationsByRegionId(Long regionId);

    void deleteLocationsByCountryId(Long countryId);
}