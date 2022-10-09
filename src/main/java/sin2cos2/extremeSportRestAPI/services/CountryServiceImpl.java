package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.CountryMapper;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.repositories.CountryRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper = CountryMapper.INSTANCE;

    @Override
    public Set<CountryDto> getAllCountries() {
        return countryRepository
                .findAll()
                .stream()
                .map(countryMapper::countryToCountryDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CountryDto getCountryDtoById(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);

        if (countryOptional.isEmpty()) {
            throw new NotFoundException("Country with id: " + id + " wasn't found");
        }

        return countryMapper.countryToCountryDto(countryOptional.get());
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country with id: " + id + " wasn't found"));
    }

    @Override
    public CountryDto saveCountry(CountryDto countryDto) {
        Country country = countryMapper.countryDtoToCountry(countryDto);
        return countryMapper.countryToCountryDto(countryRepository.save(country));
    }

    @Override
    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        Optional<Country> countryOptional = countryRepository.findById(id);

        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            country.setName(countryDto.getName());

            return countryMapper.countryToCountryDto(countryRepository.save(country));
        }

        return saveCountry(countryDto);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
