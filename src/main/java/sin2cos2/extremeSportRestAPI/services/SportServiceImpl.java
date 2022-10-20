package sin2cos2.extremeSportRestAPI.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.api.v1.mappers.SportMapper;
import sin2cos2.extremeSportRestAPI.entities.Sport;
import sin2cos2.extremeSportRestAPI.exceptions.NotFoundException;
import sin2cos2.extremeSportRestAPI.repositories.SportRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;
    private final SportMapper sportMapper = SportMapper.INSTANCE;

    @Override
    public Set<SportDto> getAllSports(int page) {
        return sportRepository
                .findAll(PageRequest.of(page - 1, 10))
                .stream()
                .map(sportMapper::sportToSportDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SportDto getSportDtoById(Long sportId) {
        Optional<Sport> sport = sportRepository.findById(sportId);

        if (sport.isEmpty())
            throw new NotFoundException("Sport with id: " + sportId + " wasn't found");

        return sportMapper.sportToSportDto(sport.get());
    }

    @Override
    public Sport getSportById(Long sportId) {
        return sportRepository.findById(sportId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public SportDto saveSport(SportDto sportDto) {
        Sport sport = sportMapper.sportDtoToSport(sportDto);
        sport = sportRepository.save(sport);

        return sportMapper.sportToSportDto(sport);
    }

    @Override
    public SportDto updateSport(SportDto sportDto, Long sportId) {
        Optional<Sport> sportOptional = sportRepository.findById(sportId);

        if(sportOptional.isPresent()) {
            Sport sport = sportOptional.get();
            sport.setName(sportDto.getName());

            return sportMapper.sportToSportDto(sportRepository.save(sport));
        }

        return saveSport(sportDto);
    }

    @Override
    public void deleteSport(Long sportId) {
        sportRepository.deleteById(sportId);
    }
}
