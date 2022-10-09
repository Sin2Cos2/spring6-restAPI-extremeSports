package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.entities.Sport;

@Mapper
public interface SportMapper {

    SportMapper INSTANCE = Mappers.getMapper(SportMapper.class);

    @Mapping(source = "id", target ="sportURI", qualifiedByName = "sportURI")
    SportDto sportToSportDto(Sport sport);

    @Mapping(target = "sportLocations", ignore = true)
    @Mapping(target = "id", ignore = true)
    Sport sportDtoToSport(SportDto sportDto);

    @Named("sportURI")
    default String setSportURI(Long id) {
        return "/api/v1/sports/" + id;
    }
}
