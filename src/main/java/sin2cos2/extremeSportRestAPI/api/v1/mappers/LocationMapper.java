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

    @Mapping(target = "locationURI",
            expression = "java(locationURI(location.getId(), location.getRegion(), location.getCountry()))")
    @Mapping(target = "regionURI",
            expression = "java(regionURI(location.getRegion(), location.getCountry()))")
    @Mapping(source = "country", target = "countryURI", qualifiedByName = "countryURI")
    LocationDto locationToLocationDto(Location location);

    @Mapping(target = "trips", ignore = true)
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    Location locationDtoToLocation(LocationDto locationDto);

    @Named("locationURI")
    default String locationURI(Long id, Region region, Country country) {
        return "/api/v1/countries/" + country.getId() + "/regions/" + region.getId() + "/locations/" + id;
    }

    @Named("regionURI")
    default String regionURI(Region region, Country country) {
        return "/api/v1/countries/" + country.getId() + "/regions/" + region.getId();
    }

    @Named("countryURI")
    default String countryURI(Country country) {
        return "/api/v1/countries/" + country.getId();
    }
}
