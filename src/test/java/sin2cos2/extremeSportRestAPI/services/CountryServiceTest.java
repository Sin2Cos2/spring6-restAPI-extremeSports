package sin2cos2.extremeSportRestAPI.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.repositories.CountryRepository;
import sin2cos2.extremeSportRestAPI.repositories.LocationRepository;
import sin2cos2.extremeSportRestAPI.repositories.RegionRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CountryServiceTest extends ServiceTest {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private LocationRepository locationRepository;
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        countryService = new CountryServiceImpl(countryRepository);
    }

    @Test
    void getAllCountries() {
        CountryDto countryDto = CountryDto.builder().name("test").build();
        long count = countryRepository.count();

        countryService.saveCountry(countryDto);
        assertThat(countryRepository.count()).isGreaterThan(count);

    }

    @Test
    void getCountriesByName() {

        assertThat(countryService.getCountriesByName("Russia").size()).isEqualTo(1);
    }

    @Test
    void getCountryDtoById() {
        CountryDto countryDto = countryService.getCountryDtoById(1L);

        assertThat(countryDto.getName()).isEqualTo("Russia");
    }

    @Test
    void getCountryDtoByIdNotFound() {
        Exception exception = assertThrows(NotFoundException.class, () -> countryService.getCountryDtoById(6L));

        String expectedMessage = "id: " + 6;
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void getCountryById() {
        Country country = countryService.getCountryById(1L);

        assertThat(country.getId()).isEqualTo(1L);
        assertThat(country.getName()).isEqualTo("Russia");
    }

    @Test
    void getCountryByIdNotFound() {
        Exception exception = assertThrows(NotFoundException.class, () -> countryService.getCountryById(9L));

        String expectedMessage = "id: " + 9;
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void saveCountry() {
        CountryDto country = CountryDto.builder().name("TestCountry").build();
        long count = countryRepository.count();

        country = countryService.saveCountry(country);

        assertThat(country.getCountryURI()).isNotNull();
        assertThat(country.getName()).isEqualTo("TestCountry");
        assertThat(count).isLessThan(countryRepository.count());
    }

    @Test
    void updateCountry() {
        CountryDto countryDto = CountryDto.builder().name("RussiaUpdated").build();

        CountryDto saved = countryService.updateCountry(1L, countryDto);

        assertThat(saved.getName()).isEqualTo("RussiaUpdated");
        assertTrue(saved.getCountryURI().contains("/countries/1"));
    }

    @Test
    void updateCountryNotFound() {
        long count = countryRepository.count();
        countryService.updateCountry(7L, new CountryDto());

        assertThat(count).isLessThan(countryRepository.count());
    }

    @Test
    void deleteCountry() {
        long countryCount = countryRepository.count();

        countryService.deleteCountry(1L);

        assertThat(countryCount).isGreaterThan(countryRepository.count());
        assertThat(regionRepository.findByCountryId(1L, PageRequest.of(0,10))
                .getTotalElements())
                .isEqualTo(0);
        assertThat(locationRepository.getLocationByCountryId(1L, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
    }
}