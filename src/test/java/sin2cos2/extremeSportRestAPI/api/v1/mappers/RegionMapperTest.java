package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.junit.jupiter.api.Test;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Region;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegionMapperTest {

    private final RegionMapper regionMapper = RegionMapper.INSTANCE;

    @Test
    void regionToRegionDto() {
        Country country = Country.builder().id(1L).build();
        Region region = Region
                .builder()
                .name("TestRegion")
                .country(country)
                .id(2L)
                .build();

        RegionDto res = regionMapper.regionToRegionDto(region);

        assertThat(res.getName()).isEqualTo("TestRegion");
        assertThat(res.getRegionURI()).isEqualTo("/api/v1/regions/2");
        assertThat(res.getCountryURI()).isEqualTo("/api/v1/countries/1");
    }

    @Test
    void regionDtoToRegion() {
        RegionDto regionDto = RegionDto
                .builder()
                .name("TestRegion")
                .build();

        Region res = regionMapper.regionDtoToRegion(regionDto);

        assertThat(res.getName()).isEqualTo("TestRegion");
    }
}