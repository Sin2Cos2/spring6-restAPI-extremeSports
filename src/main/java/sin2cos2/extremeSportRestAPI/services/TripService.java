package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;

import java.util.Set;

public interface TripService {
    Set<TripDto> getAllTrips();

    Set<TripDto> getAllTripsByLocationAndSport(Long locationId, Long sportId);

    Set<TripDto> getAllTripsByLocation(Long countryId);

    Set<TripDto> getAllTripsBySport(Long sportId);

    TripDto getTripById(Long tripId);

    TripDto saveTrip(Long locationId, Long sportId, TripDto tripDto);

    TripDto updateTrip(Long tripId, TripDto tripDto);

    void deleteTrip(Long tripId);

    void deleteTripsByLocationAndSport(Long locationId, Long sportId);

    void deleteTripsByLocation(Long locationId);

    void deleteTripsBySport(Long sportId);
}
