package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.LocationMapper;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Region;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.repositories.LocationRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final RegionService regionService;
    private final CountryService countryService;
    private final LocationMapper locationMapper = LocationMapper.INSTANCE;

    @Override
    public Set<LocationDto> getLocationsByCountryAndRegion(Long regionId, Long countryId) {
        return locationRepository
                .getLocationByRegionIdAndCountryId(regionId, countryId)
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LocationDto> getAllLocations() {
        return locationRepository
                .findAll()
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public LocationDto getLocationDtoById(Long locationId, Long regionId, Long countryId) {
        LocationDto locationDto = getLocationDtoById(locationId);

        String regionURI = "/regions/" + regionId;
        String countryURI = "/countries/" + countryId;

        if(!locationDto.getCountryURI().contains(countryURI))
            throw new NotFoundException("No Location with id: " + locationId + " in this country");

        if(!locationDto.getRegionURI().contains(regionURI))
            throw new NotFoundException("No Location with id: " + locationId + " in this region");

        return locationDto;
    }

    @Override
    public LocationDto getLocationDtoById(Long locationId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);

        if(locationOptional.isEmpty())
            throw new NotFoundException("Location with id: " + locationId + " wasn't found");

        Location location = locationOptional.get();
        return locationMapper.locationToLocationDto(location);
    }

    @Override
    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public LocationDto saveLocation(LocationDto locationDto, Long regionId, Long countryId) {
        Location location = locationMapper.locationDtoToLocation(locationDto);
        Region region = regionService.getRegionById(regionId);
        Country country = countryService.getCountryById(countryId);

        location.setRegion(region);
        location.setCountry(country);

        return locationMapper.locationToLocationDto(locationRepository.save(location));
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto, Long locationId, Long regionId, Long countryId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);

        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            location.setName(locationDto.getName());

            return locationMapper.locationToLocationDto(locationRepository.save(location));
        }

        return saveLocation(locationDto, regionId, countryId);
    }

    @Override
    public void deleteLocation(Long locationId, Long regionId, Long countryId) {
        locationRepository.deleteById(locationId);
    }
}
