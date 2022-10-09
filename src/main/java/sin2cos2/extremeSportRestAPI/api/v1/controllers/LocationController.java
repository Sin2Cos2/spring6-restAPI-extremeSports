package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.services.LocationService;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/countries/{countryId}/regions/{regionId}/locations")
    public Set<LocationDto> getLocationsByCountryAndRegion(@PathVariable String countryId,
                                                           @PathVariable String regionId) {
        return locationService.getLocationsByCountryAndRegion(Long.valueOf(regionId), Long.valueOf(countryId));
    }

    @GetMapping("/locations")
    public Set<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping({"/locations/{locationId}", "/countries/{countryId}/regions/{regionId}/locations/{locationId}"})
    public LocationDto getLocationById(@PathVariable(required = false) String countryId,
                                       @PathVariable(required = false) String regionId,
                                       @PathVariable String locationId) {
        return locationService
                .getLocationById(Long.valueOf(locationId),
                        regionId == null ? null : Long.valueOf(regionId),
                        countryId == null ? null : Long.valueOf(countryId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/countries/{countryId}/regions/{regionId}/locations")
    public LocationDto saveLocation(@PathVariable String countryId,
                                    @PathVariable String regionId) {
        return locationService.saveLocation(Long.valueOf(regionId), Long.valueOf(countryId));
    }
}
