package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.junit.jupiter.api.Test;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Region;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {

    private final LocationMapper locationMapper = LocationMapper.INSTANCE;

    @Test
    void locationToLocationDto() {
        Country country = Country.builder().id(1L).build();
        Region region = Region.builder().id(2L).build();
        Location location = Location
                .builder()
                .name("TestLocation")
                .region(region)
                .country(country)
                .id(3L)
                .build();

        LocationDto res = locationMapper.locationToLocationDto(location);

        assertThat(res.getName()).isEqualTo("TestLocation");
        assertThat(res.getLocationURI()).isEqualTo("/api/v1/locations/3");
        assertThat(res.getRegionURI()).isEqualTo("/api/v1/regions/2");
        assertThat(res.getCountryURI()).isEqualTo("/api/v1/countries/1");
    }

    @Test
    void locationDtoToLocation() {
        LocationDto locationDto = LocationDto.builder().name("TestLocation").build();

        Location res = locationMapper.locationDtoToLocation(locationDto);

        assertThat(res.getName()).isEqualTo("TestLocation");
    }
}