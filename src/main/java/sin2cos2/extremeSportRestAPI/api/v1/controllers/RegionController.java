package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.services.RegionService;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/countries/{countryId}/regions")
    public Set<RegionDto> getRegionsByCountry(@PathVariable String countryId) {
        return regionService.getRegionsByCountry(Long.valueOf(countryId));
    }

    @GetMapping("/regions")
    public Set<RegionDto> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping({"/regions/{regionId}", "/countries/{countryId}/regions/{regionId}"})
    public RegionDto getRegionById(@PathVariable(required = false) String countryId,
                                   @PathVariable String regionId) {
        if (countryId == null)
            return regionService.getRegionDtoById(Long.valueOf(regionId));

        return regionService.getRegionDtoById(Long.valueOf(regionId), Long.valueOf(countryId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/countries/{countryId}/regions")
    public RegionDto saveRegion(@PathVariable String countryId,
                                @RequestBody RegionDto regionDto) {
        return regionService.saveRegion(Long.valueOf(countryId), regionDto);
    }

    @PutMapping("/countries/{countryId}/regions/{regionId}")
    public RegionDto updateRegion(@PathVariable String regionId,
                                  @PathVariable String countryId,
                                  @RequestBody RegionDto regionDto) {
        return regionService.updateRegion(Long.valueOf(regionId), Long.valueOf(countryId), regionDto);
    }

    @DeleteMapping("/countries/{countryId}/regions/{regionId}")
    public void deleteRegion(@PathVariable String countryId, @PathVariable String regionId) {
        regionService.deleteRegion(Long.valueOf(regionId), Long.valueOf(countryId));
    }

}
