package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Trip;

import java.util.Set;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findAllByLocationId(Long locationId, Pageable page);

    Page<Trip> findAllBySportId(Long sportId, Pageable page);

    Page<Trip> findAllByLocationIdAndSportId(Long locationId, Long sportId, Pageable page);

    void deleteTripsByLocationIdAndSportId(Long locationId, Long sportId);

    void deleteTripsByLocationId(Long locationId);

    void deleteTripsBySportId(Long sportId);
}