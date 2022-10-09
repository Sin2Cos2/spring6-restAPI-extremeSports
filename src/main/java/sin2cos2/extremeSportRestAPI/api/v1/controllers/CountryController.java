package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.services.CountryService;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping({"", "/"})
    public Set<CountryDto> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryId}")
    public CountryDto getCountryById(@PathVariable String countryId) {
        return countryService.getCountryDtoById(Long.valueOf(countryId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CountryDto saveCountry(@RequestBody CountryDto countryDto) {
        return countryService.saveCountry(countryDto);
    }

    @PutMapping("/{countryId}")
    public CountryDto updateCountry(@PathVariable String countryId,
                                    @RequestBody CountryDto countryDto) {
        return countryService.updateCountry(Long.valueOf(countryId), countryDto);
    }

    @DeleteMapping("/{countryId}")
    public void deleteCountry(@PathVariable String countryId) {
        countryService.deleteCountry(Long.valueOf(countryId));
    }


}

