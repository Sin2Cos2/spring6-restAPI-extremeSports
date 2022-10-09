package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.RegionMapper;
import sin2cos2.extremeSportRestAPI.entities.Country;
import sin2cos2.extremeSportRestAPI.entities.Region;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.repositories.RegionRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final CountryService countryService;
    private final RegionMapper regionMapper = RegionMapper.INSTANCE;

    @Override
    public Set<RegionDto> getRegionsByCountry(Long countryId) {
        return regionRepository.findByCountryId(countryId)
                .stream()
                .map(regionMapper::regionToRegionDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RegionDto> getAllRegions() {
        return regionRepository
                .findAll()
                .stream()
                .map(regionMapper::regionToRegionDto)
                .collect(Collectors.toSet());
    }

    @Override
    public RegionDto getRegionDtoById(Long regionId, Long countryId) {
        RegionDto region = getRegionDtoById(regionId);

        if (!region.getCountryURI().contains("/" + countryId))
            throw new NotFoundException("No Regions with id: " + regionId + " in this country");

        return region;
    }

    @Override
    public Region getRegionById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Region with id: " + id + " wasn't found"));
    }

    @Override
    public RegionDto getRegionDtoById(Long regionId) {
        Optional<Region> regionOptional = regionRepository.findById(regionId);

        if (regionOptional.isEmpty())
            throw new NotFoundException("Region with id: " + regionId + " wasn't found");

        Region region = regionOptional.get();

        return regionMapper.regionToRegionDto(region);
    }

    @Override
    public RegionDto saveRegion(Long countryId, RegionDto regionDto) {
        Region region = regionMapper.regionDtoToRegion(regionDto);
        Country country = countryService.getCountryById(countryId);

        region.setCountry(country);

        return regionMapper.regionToRegionDto(regionRepository.save(region));
    }

    @Override
    public RegionDto updateRegion(Long regionId, Long countryId, RegionDto regionDto) {
        Optional<Region> regionOptional = regionRepository.findById(regionId);

        if (regionOptional.isPresent()) {
            Region region = regionOptional.get();
            region.setName(regionDto.getName());

            return regionMapper.regionToRegionDto(region);
        }

        return saveRegion(countryId, regionDto);
    }

    @Override
    public void deleteRegion(Long regionId, Long countryId) {
        regionRepository.deleteById(regionId);
    }
}
