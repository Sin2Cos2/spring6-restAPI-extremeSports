package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.junit.jupiter.api.Test;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;
import sin2cos2.extremeSportRestAPI.entities.Country;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;

class CountryMapperTest {

    private final CountryMapper countryMapper = CountryMapper.INSTANCE;

    @Test
    void countryToCountryDto() {
        Country country = Country
                .builder()
                .name("TestCountry")
                .id(1L)
                .build();

        CountryDto res = countryMapper.countryToCountryDto(country);

        assertThat(res.getName()).isEqualTo("TestCountry");
        assertThat(res.getCountryURI()).isEqualTo("/api/v1/countries/1");
    }

    @Test
    void countryDtoToCountry() {
        CountryDto countryDto = CountryDto
                .builder()
                .name("TestCountry")
                .build();

        Country country = countryMapper.countryDtoToCountry(countryDto);

        assertThat(country.getName()).isEqualTo("TestCountry");
        assertThat(country.getId()).isNull();
        assertThat(country.getRegions()).isNull();
    }
}