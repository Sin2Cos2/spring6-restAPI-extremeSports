package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.entities.Location;

import java.util.Set;

public interface LocationService {
    Set<LocationDto> getLocationsByRegion(Long regionId, int page);

    Set<LocationDto> getLocationsByCountry(Long countryId, int page);

    Set<LocationDto> getAllLocations(int page);

    LocationDto getLocationDtoById(Long locationId);

    Location getLocationById(Long locationId);

    LocationDto saveLocation(LocationDto locationDto, Long regionId, Long countryId);

    LocationDto updateLocation(LocationDto locationDto, Long locationId);

    void deleteLocation(Long locationId);

    void deleteLocationsByRegion(Long regionId);

    void deleteLocationsByCountry(Long countryId);
}
