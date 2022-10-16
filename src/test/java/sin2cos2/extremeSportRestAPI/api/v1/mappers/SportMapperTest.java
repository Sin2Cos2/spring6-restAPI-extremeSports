package sin2cos2.extremeSportRestAPI.api.v1.mappers;

import org.junit.jupiter.api.Test;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.entities.Sport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.junit.jupiter.api.Assertions.*;

class SportMapperTest {

    private final SportMapper sportMapper = SportMapper.INSTANCE;

    @Test
    void sportToSportDto() {
        Sport sport = Sport
                .builder()
                .name("TestSport")
                .id(3L)
                .build();

        SportDto res = sportMapper.sportToSportDto(sport);

        assertThat(res.getName()).isEqualTo("TestSport");
        assertThat(res.getSportURI()).isEqualTo("/api/v1/sports/3");
    }

    @Test
    void sportDtoToSport() {
        SportDto sportDto = SportDto.builder().name("TestSportDto").build();

        Sport res = sportMapper.sportDtoToSport(sportDto);

        assertThat(res.getName()).isEqualTo("TestSportDto");
    }
}