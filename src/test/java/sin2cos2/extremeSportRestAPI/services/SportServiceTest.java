package sin2cos2.extremeSportRestAPI.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.entities.Sport;
import sin2cos2.extremeSportRestAPI.repositories.SportRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SportServiceTest extends ServiceTest {

    @Autowired
    private SportRepository sportRepository;

    private SportService sportService;

    @BeforeEach
    void setUp() {
        sportService = new SportServiceImpl(sportRepository);
    }

    @Test
    void getAllSports() {

        assertThat(sportService.getAllSports(1).size()).isEqualTo(3);
    }

    @Test
    void getSportDtoById() {
        SportDto sportDto = sportService.getSportDtoById(1L);

        assertThat(sportDto.getName()).isEqualTo("Scuba Diving");
        assertTrue(sportDto.getSportURI().contains("/sports/1"));
    }

    @Test
    void getSportById() {
        Sport sport = sportService.getSportById(2L);

        assertThat(sport.getName()).isEqualTo("Paragliding");
        assertThat(sport.getId()).isEqualTo(2L);
        assertThat(sport.getTrips().size()).isEqualTo(3);
    }

    @Test
    void saveSport() {
        SportDto toSave = SportDto.builder().name("SavedSport").build();

        SportDto saved = sportService.saveSport(toSave);

        assertThat(saved.getSportURI()).isNotNull();
        assertThat(saved.getName()).isEqualTo("SavedSport");
    }

    @Test
    void updateSport() {
        SportDto toUpdate = SportDto.builder().name("UpdatedSkateboarding").build();

        SportDto updated = sportService.updateSport(toUpdate, 3L);

        assertTrue(updated.getSportURI().contains("/sports/3"));
        assertThat(updated.getName()).isEqualTo("UpdatedSkateboarding");
    }

    @Test
    void deleteSport() {
        long count = sportRepository.count();

        sportService.deleteSport(2L);

        assertThat(count).isGreaterThan(sportRepository.count());
    }
}