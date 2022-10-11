package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Region;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "locationURI", source = "id", qualifiedByName = "locationURI")
    @Mapping(target = "regionURI", source = "region", qualifiedByName = "regionURI")
    @Mapping(target = "countryURI", source = "country", qualifiedByName = "countryURI")
    LocationDto locationToLocationDto(Location location);

    @Mapping(target = "trips", ignore = true)
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    Location locationDtoToLocation(LocationDto locationDto);

    @Named("locationURI")
    default String locationURI(Long id) {
        return "/api/v1/locations/" + id;
    }

    @Named("regionURI")
    default String regionURI(Region region) {
        return "/api/v1/regions/" + region.getId();
    }

    @Named("countryURI")
    default String countryURI(Country country) {
        return "/api/v1/countries/" + country.getId();
    }
}
