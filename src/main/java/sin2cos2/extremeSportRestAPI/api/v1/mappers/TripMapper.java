package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Sport;
import sin2cos2.extremeSportRestAPI.entities.Trip;

@Mapper
public interface TripMapper {

    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "sport.name", target = "sportName")
    @Mapping(source = "sport", target = "sportURI", qualifiedByName = "sportURI")
    @Mapping(source = "location", target = "locationURI", qualifiedByName = "locationURI")
    @Mapping(source = "id", target = "tripURI", qualifiedByName = "tripURI")
    TripDto tripToTripDto(Trip trip);

    @Mapping(target = "sport", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "id", ignore = true)
    Trip tripDtoToTrip(TripDto tripDto);

    @Named("tripURI")
    default String tripURI(Long id) {
        return "/api/v1/trips/" + id;
    }

    @Named("locationURI")
    default String locationURI(Location location) {
        return "/api/v1/locations/" + location.getId();
    }

    @Named("sportURI")
    default String sportURI(Sport sport) {
        return "/api/v1/sports/" + sport.getId();
    }
}
