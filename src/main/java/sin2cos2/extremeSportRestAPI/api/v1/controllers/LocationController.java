package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all locations",
            description = """
                    Specify country id as query param to get all locations of certain country.
                                
                    Specify region id as query param to get all locations of certain region.
                                
                    In case when both params are specified, will be returned set of locations by region id.
                    
                    By default param page will be set with 1.
                    """)
    @GetMapping
    public Set<LocationDto> getAllLocations(@RequestParam(required = false) String countryId,
                                            @RequestParam(required = false) String regionId,
                                            @RequestParam(defaultValue = "1") int page) {

        if (regionId != null)
            return locationService.getLocationsByRegion(Long.valueOf(regionId), page);

        if (countryId != null) {
            return locationService.getLocationsByCountry(Long.valueOf(countryId), page);
        }

        return locationService.getAllLocations(page);
    }

    @Operation(summary = "Get location by id")
    @GetMapping("/{locationId}")
    public LocationDto getLocationById(@PathVariable String locationId) {

        return locationService.getLocationDtoById(Long.valueOf(locationId));
    }

    @Operation(summary = "Save new location")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LocationDto saveLocation(@RequestParam String countryId,
                                    @RequestParam String regionId,
                                    @RequestBody LocationDto locationDto) {
        return locationService.saveLocation(locationDto, Long.valueOf(regionId), Long.valueOf(countryId));
    }

    @Operation(summary = "Update existing location by id")
    @PutMapping("/{locationsId}")
    public LocationDto updateLocation(@PathVariable String locationsId,
                                      @RequestBody LocationDto locationDto) {
        return locationService.updateLocation(locationDto, Long.valueOf(locationsId));
    }

    @Operation(summary = "Delete locations with criteria",
            description = """
                    Specify country id as query param to delete all locations of certain country.
                                        
                    Specify region id as query param to delete all locations of certain region.
                                        
                    In case when both params are specified, locations will be deleted by region id.
                    """)
    @DeleteMapping
    public void deleteLocations(@RequestParam(required = false) String countryId,
                                @RequestParam(required = false) String regionId) {

        if (regionId != null) {
            locationService.deleteLocationsByRegion(Long.valueOf(regionId));
        } else if (countryId != null) {
            locationService.deleteLocationsByCountry(Long.valueOf(countryId));
        }
    }

    @Operation(summary = "Delete location by id")
    @DeleteMapping("/{locationsId}")
    public void deleteLocation(@PathVariable String locationsId) {
        locationService.deleteLocation(Long.valueOf(locationsId));
    }
}
