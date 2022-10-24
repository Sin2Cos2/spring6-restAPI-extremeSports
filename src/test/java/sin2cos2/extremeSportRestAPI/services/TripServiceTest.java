package sin2cos2.extremeSportRestAPI.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.repositories.*;
import sin2cos2.extremeSportRestAPI.specifications.SearchCriteria;
import sin2cos2.extremeSportRestAPI.specifications.SearchOperation;
import sin2cos2.extremeSportRestAPI.specifications.TripSpecification;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TripServiceTest extends ServiceTest {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private TripRepository tripRepository;
    private TripService tripService;

    @BeforeEach
    void setUp() {
        CountryService countryService = new CountryServiceImpl(countryRepository);
        RegionService regionService = new RegionServiceImpl(regionRepository, countryService);
        LocationService locationService = new LocationServiceImpl(locationRepository, regionService, countryService);

        SportService sportService = new SportServiceImpl(sportRepository);
        tripService = new TripServiceImpl(tripRepository, locationService, sportService);
    }

    @Test
    void getAllTrips() {

        assertThat(tripService.getAllTrips(null, null, null, null, 1)
                .size()).isEqualTo(5);
    }

    @Test
    void getAllTripsByLocationAndSport() {

        assertThat(tripService.getAllTrips(1L, 2L, null, null,1)
                .size()).isEqualTo(1);
    }

    @Test
    void getAllTripsByLocation() {

        assertThat(tripService.getAllTrips(10L, null, null, null, 1)
                .size()).isEqualTo(2);
    }

    @Test
    void getAllTripsBySport() {

        assertThat(tripService.getAllTrips(null, 2L, null, null, 1)
                .size()).isEqualTo(3);
    }

    @Test
    void getTripById() {
        TripDto tripDto = tripService.getTripById(2L);

        assertThat(tripDto.getPrice()).isEqualTo(new BigDecimal("600").setScale(4));
        assertThat(tripDto.getStartDate()).isEqualTo("2020-05-07");
        assertThat(tripDto.getEndDate()).isEqualTo("2020-05-30");
        assertThat(tripDto.getSportName()).isEqualTo("Paragliding");
        assertThat(tripDto.getLocationName()).isEqualTo("Falesti");
    }

    @Test
    void saveTrip() {
        long count = tripRepository.count();
        BigDecimal price = new BigDecimal("765.53");
        LocalDate starDate = LocalDate.of(2022, 6, 3);
        LocalDate endDate = LocalDate.of(2022, 8, 3);
        TripDto toSave = TripDto.builder()
                .price(price)
                .startDate(starDate)
                .endDate(endDate)
                .build();

        TripDto saved = tripService.saveTrip(1L, 1L, toSave);

        assertThat(count).isLessThan(tripRepository.count());
        assertThat(saved.getLocationName()).isEqualTo("Falesti");
        assertThat(saved.getSportName()).isEqualTo("Scuba Diving");
        assertThat(saved.getPrice()).isEqualTo(price);
        assertThat(saved.getStartDate()).isEqualTo(starDate);
        assertThat(saved.getEndDate()).isEqualTo(endDate);
    }

    @Test
    void updateTrip() {
        long count = tripRepository.count();
        BigDecimal price = new BigDecimal("10098.23");
        LocalDate starDate = LocalDate.of(2020, 6, 3);
        LocalDate endDate = LocalDate.of(2021, 8, 3);
        TripDto toUpdate = TripDto.builder()
                .price(price)
                .startDate(starDate)
                .endDate(endDate)
                .build();

        TripDto updated = tripService.updateTrip(2L, toUpdate);
        assertThat(count).isEqualTo(tripRepository.count());
        assertThat(updated.getLocationName()).isEqualTo("Falesti");
        assertThat(updated.getSportName()).isEqualTo("Paragliding");
        assertThat(updated.getPrice()).isEqualTo(price);
        assertThat(updated.getStartDate()).isEqualTo(starDate);
        assertThat(updated.getEndDate()).isEqualTo(endDate);
    }

    @Test
    void patchTrip() {
        long count = tripRepository.count();
        BigDecimal price = new BigDecimal("10098.23");
        LocalDate endDate = LocalDate.of(2021, 8, 3);
        TripDto toPatch = TripDto.builder()
                .price(price)
                .endDate(endDate)
                .build();

        TripDto updated = tripService.patchTrip(2L, toPatch);
        assertThat(count).isEqualTo(tripRepository.count());
        assertThat(updated.getLocationName()).isEqualTo("Falesti");
        assertThat(updated.getSportName()).isEqualTo("Paragliding");
        assertThat(updated.getPrice()).isEqualTo(price);
        assertThat(updated.getStartDate()).isEqualTo("2020-05-07");
        assertThat(updated.getEndDate()).isEqualTo(endDate);
    }

    @Test
    void deleteTrip() {
        long count = tripRepository.count();

        tripService.deleteTrip(1L);

        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsByLocationAndSport() {
        long count = tripRepository.count();

        tripService.deleteAllTripsByParams(1L, 2L, null, null);

        assertThat(tripRepository.findAllByLocationIdAndSportId(1L, 2L, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsByLocation() {
        long count = tripRepository.count();

        tripService.deleteAllTripsByParams(1L, null, null, null);

        assertThat(tripRepository.findAllByLocationId(1L, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsBySport() {
        long count = tripRepository.count();

        tripService.deleteAllTripsByParams(null, 2L, null, null);

        assertThat(tripRepository.findAllBySportId(2L, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsByStartDate() {
        long count = tripRepository.count();
        LocalDate startDate = LocalDate.of(2020, 5, 6);

        tripService.deleteAllTripsByParams(null, null, startDate, null);

        assertThat(tripRepository.findAllByStartDate(startDate, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsByEndDate() {
        long count = tripRepository.count();
        LocalDate endDate = LocalDate.parse("2020-05-06");

        tripService.deleteAllTripsByParams(null, null, endDate, null);

        assertThat(tripRepository.findAllByEndDate(endDate, PageRequest.of(0, 10))
                .getTotalElements())
                .isEqualTo(0);
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsByLocationAndSportAndStartDate() {
        LocalDate startDate = LocalDate.parse("2020-05-06");

        TripSpecification tripSpecification = new TripSpecification();
        tripSpecification.add(new SearchCriteria("location.id", 1L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("sport.id", 2L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("startDate", startDate, SearchOperation.GREATER_THAN_EQUAL));

        long count = tripRepository.count();
        long beforeDelete = tripRepository.findAll(tripSpecification).size();

        tripService.deleteAllTripsByParams(1L, 2L, startDate, null);

        assertThat(beforeDelete).isGreaterThan(tripRepository.findAll(tripSpecification).size());
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteTripsByLocationAndSportAndEndDate() {
        LocalDate endDate = LocalDate.parse("2022-05-06");

        TripSpecification tripSpecification = new TripSpecification();
        tripSpecification.add(new SearchCriteria("location.id", 2L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("sport.id", 2L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("endDate", endDate, SearchOperation.LESS_THAN_EQUAL));

        long count = tripRepository.count();
        long beforeDelete = tripRepository.findAll(tripSpecification).size();

        tripService.deleteAllTripsByParams(2L, 2L, null, endDate);

        assertThat(beforeDelete).isGreaterThan(tripRepository.findAll(tripSpecification).size());
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void noDeleteTripsByLocationAndStartDate() {
        LocalDate startDate = LocalDate.parse("2022-05-06");

        TripSpecification tripSpecification = new TripSpecification();
        tripSpecification.add(new SearchCriteria("location.id", 1L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("startDate", startDate, SearchOperation.GREATER_THAN_EQUAL));

        long count = tripRepository.count();
        long beforeDelete = tripRepository.findAll(tripSpecification).size();

        tripService.deleteAllTripsByParams(1L, null, startDate, null);

        assertThat(beforeDelete).isEqualTo(tripRepository.findAll(tripSpecification).size());
        assertThat(count).isEqualTo(tripRepository.count());
    }

    @Test
    void deleteTripsAllParams() {
        LocalDate startDate = LocalDate.parse("2020-05-06");
        LocalDate endDate = LocalDate.parse("2020-06-06");

        TripSpecification tripSpecification = new TripSpecification();
        tripSpecification.add(new SearchCriteria("location.id", 1L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("sport.id", 2L, SearchOperation.EQUAL));
        tripSpecification.add(new SearchCriteria("startDate", startDate, SearchOperation.GREATER_THAN_EQUAL));
        tripSpecification.add(new SearchCriteria("endDate", endDate, SearchOperation.LESS_THAN_EQUAL));

        long count = tripRepository.count();
        long beforeDelete = tripRepository.findAll(tripSpecification).size();

        tripService.deleteAllTripsByParams(1L, 2L, startDate, endDate);

        assertThat(beforeDelete).isGreaterThan(tripRepository.findAll(tripSpecification).size());
        assertThat(count).isGreaterThan(tripRepository.count());
    }

    @Test
    void deleteNoTrips() {
        long count = tripRepository.count();

        tripService.deleteAllTripsByParams(null, null, null, null);
        assertThat(count).isEqualTo(tripRepository.count());
    }
}