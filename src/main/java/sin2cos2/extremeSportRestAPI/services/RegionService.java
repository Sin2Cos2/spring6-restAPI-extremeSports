package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.entities.Region;

import java.util.Set;

public interface RegionService {
    Set<RegionDto> getRegionsByCountry(Long countryId, int page);

    Set<RegionDto> getAllRegions(int page);

    Set<RegionDto> getRegionsByName(String name);

    Region getRegionById(Long id);

    RegionDto getRegionDtoById(Long regionId);

    RegionDto saveRegion(Long countryId, RegionDto regionDto);

    RegionDto updateRegion(Long regionId, RegionDto regionDto);

    void deleteRegion(Long regionId);

    void deleteAllRegionsByCountry(Long countryId);
}
