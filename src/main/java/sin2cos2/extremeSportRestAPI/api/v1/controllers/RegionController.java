package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.services.RegionService;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public Set<RegionDto> getAllRegions(@RequestParam(required = false) String countryId) {

        if (countryId != null)
            return regionService.getRegionsByCountry(Long.valueOf(countryId));

        return regionService.getAllRegions();
    }

    @GetMapping("/{regionId}")
    public RegionDto getRegionById(@PathVariable String regionId) {
        return regionService.getRegionDtoById(Long.valueOf(regionId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RegionDto saveRegion(@RequestParam String countryId,
                                @RequestBody RegionDto regionDto) {
        return regionService.saveRegion(Long.valueOf(countryId), regionDto);
    }

    @PutMapping("/{regionId}")
    public RegionDto updateRegion(@PathVariable String regionId,
                                  @RequestBody RegionDto regionDto) {
        return regionService.updateRegion(Long.valueOf(regionId), regionDto);
    }

    @DeleteMapping
    public void deleteRegions(@RequestParam String countryId) {
        regionService.deleteAllRegionsByCountry(Long.valueOf(countryId));
    }

    @DeleteMapping("/{regionId}")
    public void deleteRegion(@PathVariable String regionId) {
        regionService.deleteRegion(Long.valueOf(regionId));
    }

}
