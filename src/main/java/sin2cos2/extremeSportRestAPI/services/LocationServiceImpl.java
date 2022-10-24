package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.LocationMapper;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Region;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.exceptions.WrongHierarchyBind;
import sin2cos2.extremeSportRestAPI.repositories.LocationRepository;

import java.util.Objects;
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
    public Set<LocationDto> getLocationsByRegion(Long regionId, int page) {
        return locationRepository
                .getLocationByRegionId(regionId, PageRequest.of(page - 1, 10))
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LocationDto> getLocationsByCountry(Long countryId, int page) {
        return locationRepository
                .getLocationByCountryId(countryId, PageRequest.of(page - 1, 10))
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LocationDto> getAllLocations(int page) {
        return locationRepository
                .findAll(PageRequest.of(page - 1, 10))
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<LocationDto> getLocationsByName(String name) {
        return locationRepository.getLocationsByName(name, PageRequest.of(0, 10))
                .stream()
                .map(locationMapper::locationToLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public LocationDto getLocationDtoById(Long locationId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);

        if (locationOptional.isEmpty())
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

        Region region = regionService.getRegionById(regionId);

        if (!Objects.equals(region.getCountry().getId(), countryId))
            throw new WrongHierarchyBind("Region " + region.getName() + " doesn't belong to country with id: " + countryId);

        Location location = locationMapper.locationDtoToLocation(locationDto);
        Country country = countryService.getCountryById(countryId);

        location.setRegion(region);
        location.setCountry(country);

        return locationMapper.locationToLocationDto(locationRepository.save(location));
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto, Long locationId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);

        if (locationOptional.isEmpty())
            throw new NotFoundException("Location with id: " + locationId + " wasn't found");

        Location location = locationOptional.get();
        location.setName(locationDto.getName());

        return locationMapper.locationToLocationDto(locationRepository.save(location));
    }

    @Override
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }

    @Transactional
    @Override
    public void deleteLocationsByRegion(Long regionId) {
        locationRepository.deleteLocationsByRegionId(regionId);
    }

    @Transactional
    @Override
    public void deleteLocationsByCountry(Long countryId) {
        locationRepository.deleteLocationsByCountryId(countryId);
    }
}
