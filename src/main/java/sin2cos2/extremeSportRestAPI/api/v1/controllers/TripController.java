package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.services.TripService;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TripController {

    private final TripService tripService;

    @GetMapping("/trips")
    public Set<TripDto> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/countries/{countryId}/regions/{regionId}/locations/{locationId}/trips")
    public Set<TripDto> getAllTripsByLocation(@PathVariable String countryId,
                                              @PathVariable String regionId,
                                              @PathVariable String locationId) {
        return tripService.getAllTripsByLocation(Long.valueOf(countryId),
                Long.valueOf(regionId),
                Long.valueOf(locationId));
    }

    @GetMapping("/sports/{sportId}/trips")
    public Set<TripDto> getAllTripsBySport(@PathVariable String sportId) {
        return tripService.getAllTripsBySport(Long.valueOf(sportId));
    }

    @GetMapping("/trips/{tripId}")
    public TripDto getTripById(@PathVariable String tripId) {
        return tripService.getTripById(Long.valueOf(tripId));
    }

    @GetMapping("/countries/{countryId}/regions/{regionId}/locations/{locationId}/trips/{tripId}")
    public TripDto getTripByLocation(@PathVariable String countryId,
                                     @PathVariable String regionId,
                                     @PathVariable String locationId,
                                     @PathVariable String tripId) {
        return tripService
                .getTripByLocation(Long.valueOf(countryId),
                        Long.valueOf(regionId),
                        Long.valueOf(locationId),
                        Long.valueOf(tripId));
    }

    @GetMapping("/sports/{sportId}/trips/{tripId}")
    public TripDto getTripBySport(@PathVariable String sportId,
                                  @PathVariable String tripId) {
        return tripService.getTripBySport(Long.valueOf(sportId), Long.valueOf(tripId));
    }

}
