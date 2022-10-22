package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.services.TripService;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripService tripService;

    @Operation(summary = "Get all trips",
            description = """
                    Specify any of query params: locationId, sportId, startDate and endDate
                    to enable filter according to the given parameters.
                    Any combinations are permitted.
                                        
                    By default param page will be set with 1.
                    """)
    @GetMapping
    public Set<TripDto> getAllTrips(@RequestParam(required = false) String locationId,
                                    @RequestParam(required = false) String sportId,
                                    @RequestParam(required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam(required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                    @RequestParam(defaultValue = "1") Integer page) {

        Long locationIdLong = locationId == null ? null : Long.valueOf(locationId);
        Long sportIdLong = sportId == null ? null : Long.valueOf(sportId);

        return tripService.getAllTrips(locationIdLong, sportIdLong, startDate, endDate, page);
    }

    @Operation(summary = "Get trip by id")
    @GetMapping("/{tripId}")
    public TripDto getTripById(@PathVariable String tripId) {
        return tripService.getTripById(Long.valueOf(tripId));
    }

    @Operation(summary = "Save new trip",
            description = "You have to specify location and sport id")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TripDto saveTrip(@RequestParam String locationId,
                            @RequestParam String sportId,
                            @RequestBody TripDto tripDto) {
        return tripService.saveTrip(Long.valueOf(locationId), Long.valueOf(sportId), tripDto);
    }

    @Operation(summary = "Update existing trip by id",
            description = """
                    If values for price, startDate or endDate will not be specified,
                    null value will be assigned by default
                    """)
    @PutMapping("/{tripId}")
    public TripDto updateTrip(@PathVariable String tripId,
                              @RequestBody TripDto tripDto) {
        return tripService.updateTrip(Long.valueOf(tripId), tripDto);
    }

    @Operation(summary = "Patch trip by id",
            description = "Specify only values you want update. Old values will be saved for unspecified properties.")
    @PatchMapping("/{tripId}")
    public TripDto patchTrip(@PathVariable String tripId,
                             @RequestBody TripDto tripDto) {
        return tripService.patchTrip(Long.valueOf(tripId), tripDto);
    }

    @Operation(summary = "Delete trips with criteria",
            description = """
                    Specify any of query params: locationId, sportId, startDate and endDate
                    to enable filter according to the given parameters.
                    Any combinations are permitted.
                    """)
    @DeleteMapping
    public void deleteTrips(@RequestParam(required = false) String locationId,
                            @RequestParam(required = false) String sportId,
                            @RequestParam(required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                            @RequestParam(required = false)
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Long locationIdLong = locationId == null ? null : Long.valueOf(locationId);
        Long sportIdLong = sportId == null ? null : Long.valueOf(sportId);

        tripService.deleteAllTripsByParams(locationIdLong, sportIdLong, startDate, endDate);
    }

    @Operation(summary = "Delete trip by id")
    @DeleteMapping("/{tripId}")
    public void deleteTrip(@PathVariable String tripId) {
        tripService.deleteTrip(Long.valueOf(tripId));
    }
}
