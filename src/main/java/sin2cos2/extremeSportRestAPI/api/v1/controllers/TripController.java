package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.services.TripService;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public Set<TripDto> getAllTrips(@RequestParam(required = false) String locationId,
                                    @RequestParam(required = false) String sportId) {

        if (locationId != null && sportId != null) {
            return tripService.getAllTripsByLocationAndSport(Long.valueOf(locationId), Long.valueOf(sportId));
        }
        if (locationId != null) {
            return tripService.getAllTripsByLocation(Long.valueOf(locationId));
        }
        if (sportId != null) {
            return tripService.getAllTripsBySport(Long.valueOf(sportId));
        }

        return tripService.getAllTrips();
    }

    @GetMapping("/{tripId}")
    public TripDto getTripById(@PathVariable String tripId) {
        return tripService.getTripById(Long.valueOf(tripId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TripDto saveTrip(@RequestParam String locationId,
                            @RequestParam String sportId,
                            @RequestBody TripDto tripDto) {
        return tripService.saveTrip(Long.valueOf(locationId), Long.valueOf(sportId), tripDto);
    }

    @PutMapping("/{tripId}")
    public TripDto updateTrip(@PathVariable String tripId,
                              @RequestBody TripDto tripDto) {
        return tripService.updateTrip(Long.valueOf(tripId), tripDto);
    }

    @DeleteMapping
    public void deleteTrips(@RequestParam(required = false) String locationId,
                            @RequestParam(required = false) String sportId) {

        if (locationId != null && sportId != null) {
            tripService.deleteTripsByLocationAndSport(Long.valueOf(locationId), Long.valueOf(sportId));
        } else if (locationId != null) {
            tripService.deleteTripsByLocation(Long.valueOf(locationId));
        } else if (sportId != null) {
            tripService.deleteTripsBySport(Long.valueOf(sportId));
        }
    }

    @DeleteMapping("/{tripId}")
    public void deleteTrip(@PathVariable String tripId) {
        tripService.deleteTrip(Long.valueOf(tripId));
    }
}
