package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;
import sin2cos2.extremeSportRestAPI.entities.Country;

import java.util.Set;

public interface CountryService {
    Set<CountryDto> getAllCountries(int page);

    Set<CountryDto> getCountriesByName(String name);

    CountryDto getCountryDtoById(Long id);

    Country getCountryById(Long id);

    CountryDto saveCountry(CountryDto countryDto);

    CountryDto updateCountry(Long id, CountryDto countryDto);

    void deleteCountry(Long id);
}
