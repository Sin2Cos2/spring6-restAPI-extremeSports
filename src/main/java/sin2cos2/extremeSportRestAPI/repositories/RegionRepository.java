package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Page<Region> findByCountryId(Long id, Pageable pageable);

    void deleteRegionsByCountryId(Long countryId);
}