package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.TripMapper;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Sport;
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
    private final LocationService locationService;
    private final SportService sportService;
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
    public Set<TripDto> getAllTripsByLocationAndSport(Long locationId, Long sportId) {
        return tripRepository
                .findAllByLocationIdAndSportId(locationId, sportId)
                .stream()
                .map(tripMapper::tripToTripDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TripDto> getAllTripsByLocation(Long locationId) {
        return tripRepository
                .findAllByLocationId(locationId)
                .stream()
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

        if (tripOptional.isEmpty())
            throw new NotFoundException("Trip with id: " + tripId + " wasn't found");

        return tripMapper.tripToTripDto(tripOptional.get());
    }

    @Override
    public TripDto saveTrip(Long locationId, Long sportId, TripDto tripDto) {
        Trip trip = tripMapper.tripDtoToTrip(tripDto);
        Location location = locationService.getLocationById(locationId);
        Sport sport = sportService.getSportById(sportId);

        trip.setLocation(location);
        trip.setSport(sport);

        return tripMapper.tripToTripDto(tripRepository.save(trip));
    }

    @Override
    public TripDto updateTrip(Long tripId, TripDto tripDto) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);

        if (tripOptional.isEmpty())
            throw new NotFoundException("Trip with id: " + tripId + " wasn't found");

        Trip trip = tripOptional.get();
        trip.setPrice(tripDto.getPrice());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());

        return tripMapper.tripToTripDto(tripRepository.save(trip));

    }

    @Override
    public TripDto patchTrip(Long tripId, TripDto tripDto) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);

        if (tripOptional.isEmpty())
            throw new NotFoundException("Trip with id: " + tripId + " wasn't found");

        Trip trip = tripOptional.get();

        if (tripDto.getPrice() != null)
            trip.setPrice(tripDto.getPrice());
        if (tripDto.getStartDate() != null)
            trip.setStartDate(tripDto.getStartDate());
        if (tripDto.getEndDate() != null)
            trip.setEndDate(tripDto.getEndDate());

        return tripMapper.tripToTripDto(tripRepository.save(trip));
    }

    @Override
    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    @Transactional
    @Override
    public void deleteTripsByLocationAndSport(Long locationId, Long sportId) {
        tripRepository.deleteTripsByLocationIdAndSportId(locationId, sportId);
    }

    @Transactional
    @Override
    public void deleteTripsByLocation(Long locationId) {
        tripRepository.deleteTripsByLocationId(locationId);
    }

    @Override
    public void deleteTripsBySport(Long sportId) {
        tripRepository.deleteTripsBySportId(sportId);
    }

}
