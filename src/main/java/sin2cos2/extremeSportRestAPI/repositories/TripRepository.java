package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Trip;

import java.util.Set;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Set<Trip> findAllByLocationId(Long locationId);

    Set<Trip> findAllBySportId(Long sportId);

    Set<Trip> findAllByLocationIdAndSportId(Long locationId, Long sportId);

    void deleteTripsByLocationIdAndSportId(Long locationId, Long sportId);

    void deleteTripsByLocationId(Long locationId);

    void deleteTripsBySportId(Long sportId);
}