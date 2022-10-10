package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.entities.Location;

import java.util.Set;

public interface LocationService {
    Set<LocationDto> getLocationsByCountryAndRegion(Long regionId, Long countryId);

    Set<LocationDto> getAllLocations();

    LocationDto getLocationDtoById(Long locationId, Long regionId, Long countryId);

    LocationDto getLocationDtoById(Long locationId);

    Location getLocationById(Long locationId);

    LocationDto saveLocation(LocationDto locationDto, Long regionId, Long countryId);

    LocationDto updateLocation(LocationDto locationDto, Long locationId, Long regionId, Long countryId);

    void deleteLocation(Long locationId, Long regionId, Long countryId);
}