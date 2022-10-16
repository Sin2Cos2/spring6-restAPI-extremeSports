package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.junit.jupiter.api.Test;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.entities.Location;
import sin2cos2.extremeSportRestAPI.entities.Sport;
import sin2cos2.extremeSportRestAPI.entities.Trip;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TripMapperTest {

    private final TripMapper tripMapper = TripMapper.INSTANCE;

    @Test
    void tripToTripDto() {
        Location location = Location.builder().id(33L).name("testLoc").build();
        Sport sport = Sport.builder().id(67L).name("testSport").build();
        Trip trip = Trip
                .builder()
                .location(location)
                .sport(sport)
                .price(new BigDecimal("788.23"))
                .startDate(LocalDate.of(2020, 6, 5))
                .endDate(LocalDate.of(2020, 7, 5))
                .id(5L)
                .build();

        TripDto res = tripMapper.tripToTripDto(trip);

        assertThat(res.getTripURI()).isEqualTo("/api/v1/trips/5");
        assertThat(res.getLocationName()).isEqualTo("testLoc");
        assertThat(res.getLocationURI()).isEqualTo("/api/v1/locations/33");
        assertThat(res.getSportName()).isEqualTo("testSport");
        assertThat(res.getSportURI()).isEqualTo("/api/v1/sports/67");
        assertThat(res.getPrice()).isEqualTo("788.23");
        assertThat(res.getStartDate()).isEqualTo("2020-06-05");
        assertThat(res.getEndDate()).isEqualTo("2020-07-05");

    }

    @Test
    void tripDtoToTrip() {
        TripDto tripDto = TripDto
                .builder()
                .price(new BigDecimal("888.1234"))
                .startDate(LocalDate.of(2022, 11, 18))
                .endDate(LocalDate.of(2022, 12, 7))
                .build();

        Trip res = tripMapper.tripDtoToTrip(tripDto);

        assertThat(res.getPrice()).isEqualTo("888.1234");
        assertThat(res.getStartDate()).isEqualTo("2022-11-18");
        assertThat(res.getEndDate()).isEqualTo("2022-12-07");
    }
}