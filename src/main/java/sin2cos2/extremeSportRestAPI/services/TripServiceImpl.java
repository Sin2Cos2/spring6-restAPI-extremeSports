package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.TripMapper;
import sin2cos2.extremeSportRestAPI.entities.Trip;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.repositories.TripRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper = TripMapper.INSTANCE;

    @Override
    public Set<TripDto> getAllTrips() {
        return tripRepository
                .findAll()
                .stream()
                .map(tripMapper::tripToTripDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TripDto> getAllTripsByLocation(Long countryId, Long regionId, Long locationId) {
        return tripRepository
                .findAllByLocationId(locationId)
                .stream()
                .filter(trip -> trip.getLocation().getCountry().getId().equals(countryId))
                .filter(trip -> trip.getLocation().getRegion().getId().equals(regionId))
                .map(tripMapper::tripToTripDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TripDto> getAllTripsBySport(Long sportId) {
        return tripRepository
                .findAllBySportId(sportId)
                .stream()
                .map(tripMapper::tripToTripDto)
                .collect(Collectors.toSet());
    }

    @Override
    public TripDto getTripById(Long tripId) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);

        if(tripOptional.isEmpty())
            throw new NotFoundException("Trip with id: " + tripId + " wasn't found");

        return tripMapper.tripToTripDto(tripOptional.get());
    }

    @Override
    public TripDto getTripByLocation(Long countryId, Long regionId, Long locationId, Long tripId) {
        TripDto tripDto = getTripById(tripId);
        String countryURI = "/api/v1/countries/" + countryId;
        String regionURI = countryURI + "/regions/" + regionId;
        String locationURI = regionURI + "/locations/" + locationId;

        if (!tripDto.getLocationTripURI().getLocationURI().contains(countryURI))
            throw new NotFoundException("No Trip with id: " + tripId + " in this country");

        if (!tripDto.getLocationTripURI().getLocationURI().contains(regionURI))
            throw new NotFoundException("No Trip with id: " + tripId + " in this region");

        if (!tripDto.getLocationTripURI().getLocationURI().contains(locationURI))
            throw new NotFoundException("No Trip with id: " + tripId + " in this location");

        return tripDto;
    }

    @Override
    public TripDto getTripBySport(Long sportId, Long tripId) {
        TripDto tripDto = getTripById(tripId);
        String sportURI = "/sports/" + sportId;

        if (!tripDto.getSportTripURI().getSportURI().contains(sportURI))
            throw new NotFoundException("No Trip with id: " + tripId + " with this sport");

        return tripDto;
    }

}
