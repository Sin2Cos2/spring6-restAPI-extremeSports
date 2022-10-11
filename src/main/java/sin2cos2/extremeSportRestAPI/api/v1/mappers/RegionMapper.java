package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Region;

@Mapper
public interface RegionMapper {

    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    @Mapping(source = "id", target = "regionURI", qualifiedByName = "regionURI")
    @Mapping(source = "country", target = "countryURI", qualifiedByName = "countryURI")
    RegionDto regionToRegionDto(Region region);

    @Mapping(target = "locations", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    Region regionDtoToRegion(RegionDto regionDto);

    @Named("regionURI")
    default String regionURI(Long id) {
        return "/api/v1/regions/" + id;
    }

    @Named("countryURI")
    default String countryURI(Country country) {
        return "/api/v1/countries/" + country.getId();
    }
}
