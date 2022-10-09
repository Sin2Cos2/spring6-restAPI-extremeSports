package sin2cos2.extremeSportRestAPI.services;

import org.springframework.stereotype.Service;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;

import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {
    @Override
    public Set<LocationDto> getLocationsByCountryAndRegion(Long regionId, Long countryId) {
        return null;
    }

    @Override
    public Set<LocationDto> getAllLocations() {
        return null;
    }

    @Override
    public LocationDto getLocationById(Long locationId, Long regionId, Long countryId) {
        return null;
    }

    @Override
    public LocationDto saveLocation(Long regionId, Long countryId) {
        return null;
    }
}
