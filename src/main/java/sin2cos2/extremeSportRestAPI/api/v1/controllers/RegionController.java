package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all regions",
            description = """
                    You can specify country id as query param to get all regions for certain country.
                        
                    By default param page will be set with 1
                    """)
    @GetMapping
    public Set<RegionDto> getAllRegions(@RequestParam(required = false) String countryId,
                                        @RequestParam(defaultValue = "1") int page) {

        if (countryId != null)
            return regionService.getRegionsByCountry(Long.valueOf(countryId), page);

        return regionService.getAllRegions(page);
    }

    @Operation(summary = "Get region by id")
    @GetMapping("/{regionId}")
    public RegionDto getRegionById(@PathVariable String regionId) {
        return regionService.getRegionDtoById(Long.valueOf(regionId));
    }

    @Operation(summary = "Save new region")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RegionDto saveRegion(@RequestParam String countryId,
                                @RequestBody RegionDto regionDto) {
        return regionService.saveRegion(Long.valueOf(countryId), regionDto);
    }

    @Operation(summary = "Update existing region by id")
    @PutMapping("/{regionId}")
    public RegionDto updateRegion(@PathVariable String regionId,
                                  @RequestBody RegionDto regionDto) {
        return regionService.updateRegion(Long.valueOf(regionId), regionDto);
    }

    @Operation(summary = "Delete region by country id",
            description = "Specify country id as query param if you want to delete all regions for certain country")
    @DeleteMapping
    public void deleteRegions(@RequestParam(required = false) String countryId) {

        if (countryId != null)
            regionService.deleteAllRegionsByCountry(Long.valueOf(countryId));
    }

    @Operation(summary = "Delete region by id")
    @DeleteMapping("/{regionId}")
    public void deleteRegion(@PathVariable String regionId) {
        regionService.deleteRegion(Long.valueOf(regionId));
    }

}
