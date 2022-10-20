package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;

import java.util.Set;

public interface TripService {
    Set<TripDto> getAllTrips(int page);

    Set<TripDto> getAllTripsByLocationAndSport(Long locationId, Long sportId, int page);

    Set<TripDto> getAllTripsByLocation(Long countryId, int page);

    Set<TripDto> getAllTripsBySport(Long sportId, int page);

    TripDto getTripById(Long tripId);

    TripDto saveTrip(Long locationId, Long sportId, TripDto tripDto);

    TripDto updateTrip(Long tripId, TripDto tripDto);

    TripDto patchTrip(Long tripId, TripDto tripDto);

    void deleteTrip(Long tripId);

    void deleteTripsByLocationAndSport(Long locationId, Long sportId);

    void deleteTripsByLocation(Long locationId);

    void deleteTripsBySport(Long sportId);
}
