package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;
import sin2cos2.extremeSportRestAPI.entities.Country;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mapping(source = "id", target = "countryURI", qualifiedByName = "countryURI")
    CountryDto countryToCountryDto(Country country);

    @Mapping(target = "regions", ignore = true)
    @Mapping(target = "id", ignore = true)
    Country countryDtoToCountry(CountryDto countryDto);

    @Named("countryURI")
    default String countryURI(Long id) {
        return "/api/v1/countries/" + id;
    }
}
