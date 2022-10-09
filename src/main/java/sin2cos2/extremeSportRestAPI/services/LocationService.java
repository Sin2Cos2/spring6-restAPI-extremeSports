package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;

import java.util.Set;

public interface LocationService {
    Set<LocationDto> getLocationsByCountryAndRegion(Long regionId, Long countryId);

    Set<LocationDto> getAllLocations();

    LocationDto getLocationById(Long locationId, Long regionId, Long countryId);

    LocationDto saveLocation(Long regionId, Long countryId);
}
