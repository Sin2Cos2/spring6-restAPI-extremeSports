package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationTripURI;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportTripURI;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Sport;
import sin2cos2.extremeSportRestAPI.entities.Trip;

@Mapper
public interface TripMapper {

    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mapping(source = "location.name",
            target = "locationName")
    @Mapping(source = "sport.name",
            target = "sportName")
    @Mapping(target = "locationTripURI",
            expression = "java(locationURI(trip))")
    @Mapping(target = "sportTripURI",
            expression = "java(sportURI(trip))")
    TripDto tripToTripDto(Trip trip);

    @Mapping(target = "sport", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "id", ignore = true)
    Trip tripDtoToTrip(TripDto tripDto);

    default LocationTripURI locationURI(Trip trip) {
        Location location = trip.getLocation();
        String locationURI = "/api/v1/countries/" + location.getCountry().getId() +
                "/regions/" + location.getRegion().getId() +
                "/locations/" + location.getId();

        String tripURI = locationURI + "/trips/" + trip.getId();

        return LocationTripURI.builder()
                .locationURI(locationURI)
                .tripURI(tripURI)
                .build();
    }

    default SportTripURI sportURI(Trip trip) {
        Sport sport = trip.getSport();
        String sportURI = "/api/v1/sports/" + sport.getId();
        String tripURI = sportURI + "/trips/" + trip.getId();

        return SportTripURI.builder()
                .sportURI(sportURI)
                .tripURI(tripURI)
                .build();
    }
}
