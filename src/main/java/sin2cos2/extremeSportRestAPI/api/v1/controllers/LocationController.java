package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.services.LocationService;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public Set<LocationDto> getAllLocations(@RequestParam(required = false) String countryId,
                                            @RequestParam(required = false) String regionId) {

        if (regionId != null)
            return locationService.getLocationsByRegion(Long.valueOf(regionId));

        if (countryId != null) {
            return locationService.getLocationsByCountry(Long.valueOf(countryId));
        }

        return locationService.getAllLocations();
    }

    @GetMapping("/{locationId}")
    public LocationDto getLocationById(@PathVariable String locationId) {

        return locationService.getLocationDtoById(Long.valueOf(locationId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LocationDto saveLocation(@RequestParam String countryId,
                                    @RequestParam String regionId,
                                    @RequestBody LocationDto locationDto) {
        return locationService.saveLocation(locationDto, Long.valueOf(regionId), Long.valueOf(countryId));
    }

    @PutMapping("/{locationsId}")
    public LocationDto updateLocation(@PathVariable String locationsId,
                                      @RequestBody LocationDto locationDto) {
        return locationService.updateLocation(locationDto, Long.valueOf(locationsId));
    }

    @DeleteMapping
    public void deleteLocations(@RequestParam(required = false) String countryId,
                                @RequestParam(required = false) String regionId) {

        if (regionId != null) {
            locationService.deleteLocationsByRegion(Long.valueOf(regionId));
        } else if (countryId != null){
            locationService.deleteLocationsByCountry(Long.valueOf(countryId));
        }
    }

    @DeleteMapping("/{locationsId}")
    public void deleteLocation(@PathVariable String locationsId) {
        locationService.deleteLocation(Long.valueOf(locationsId));
    }
}
