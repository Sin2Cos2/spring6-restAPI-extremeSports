package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sin2cos2.extremeSportRestAPI.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {
    Page<Trip> findAllByLocationId(Long locationId, Pageable page);

    Page<Trip> findAllBySportId(Long sportId, Pageable page);

    Page<Trip> findAllByLocationIdAndSportId(Long locationId, Long sportId, Pageable page);
}