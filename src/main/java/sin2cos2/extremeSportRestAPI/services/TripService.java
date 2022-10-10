package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;

import java.util.Set;

public interface TripService {
    Set<TripDto> getAllTrips();

    Set<TripDto> getAllTripsByLocation(Long countryId, Long regionId, Long locationId);

    Set<TripDto> getAllTripsBySport(Long sportId);

    TripDto getTripById(Long tripId);

    TripDto getTripByLocation(Long countryId, Long regionId, Long locationId, Long tripId);

    TripDto getTripBySport(Long sportId, Long tripId);

    TripDto saveTrip(Long countryId, Long regionId, Long locationId, Long sportId, TripDto tripDto);

    TripDto updateTrip(Long countryId, Long regionId, Long locationId, Long tripId, TripDto tripDto);

    void deleteTrip(Long countryId, Long regionId, Long locationId, Long tripId);
}
