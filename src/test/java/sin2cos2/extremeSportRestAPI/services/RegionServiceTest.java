package sin2cos2.extremeSportRestAPI.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.entities.Region;
import sin2cos2.extremeSportRestAPI.repositories.CountryRepository;
import sin2cos2.extremeSportRestAPI.repositories.LocationRepository;
import sin2cos2.extremeSportRestAPI.repositories.RegionRepository;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegionServiceTest extends ServiceTest {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private LocationRepository locationRepository;

    private RegionService regionService;

    @BeforeEach
    void setUp() {
        CountryService countryService = new CountryServiceImpl(countryRepository);
        regionService = new RegionServiceImpl(regionRepository, countryService);
    }

    @Test
    void getRegionsByCountry() {
        Set<RegionDto> regionDtoSet = regionService.getRegionsByCountry(1L, 1);

        assertThat(regionDtoSet.size()).isEqualTo(1);
    }

    @Test
    void getAllRegions() {
        Set<RegionDto> regionDtoSet = regionService.getAllRegions(1);

        assertThat(regionDtoSet.size()).isEqualTo(4);
    }

    @Test
    void getRegionById() {
        Region region = regionService.getRegionById(2L);

        assertThat(region.getId()).isEqualTo(2L);
        assertThat(region.getName()).isEqualTo("Moscow");
        assertThat(region.getCountry().getId()).isEqualTo(1L);
        assertThat(region.getLocations().size()).isEqualTo(2);
    }

    @Test
    void getRegionDtoById() {
        RegionDto regionDto = regionService.getRegionDtoById(1L);
        assertThat(regionDto.getName()).isEqualTo("Chisinau");
        assertTrue(regionDto.getRegionURI().contains("/regions/1"));
        assertTrue(regionDto.getCountryURI().contains("/countries/2"));
    }

    @Test
    void saveRegion() {
        long regionCount = regionRepository.count();

        RegionDto regionDto = RegionDto.builder().name("TestSaveRegion").build();
        regionDto = regionService.saveRegion(1L, regionDto);

        assertThat(regionDto.getName()).isEqualTo("TestSaveRegion");
        assertTrue(regionDto.getCountryURI().contains("/countries/1"));
        assertThat(regionDto.getRegionURI()).isNotNull();
        assertThat(regionCount).isLessThan(regionRepository.count());
    }

    @Test
    void updateRegion() {
        long regionCount = regionRepository.count();

        RegionDto toUpdate = RegionDto.builder().name("UpdatedChisinau").build();
        RegionDto updated = regionService.updateRegion(1L, toUpdate);

        assertThat(updated.getName()).isEqualTo("UpdatedChisinau");
        assertTrue(updated.getRegionURI().contains("/regions/1"));
        assertTrue(updated.getCountryURI().contains("/countries/2"));
        assertThat(regionCount).isEqualTo(regionRepository.count());
    }

    @Test
    void deleteRegion() {
        long regionCount = regionRepository.count();

        regionService.deleteRegion(1L);

        assertThat(regionRepository.count()).isLessThan(regionCount);
        assertThat(locationRepository.getLocationByRegionId(1L, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
    }

    @Test
    void deleteAllRegionsByCountry() {
        regionService.deleteAllRegionsByCountry(3L);

        assertThat(regionRepository.findByCountryId(3L, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
    }
}