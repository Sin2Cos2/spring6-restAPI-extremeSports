package sin2cos2.extremeSportRestAPI.services;

import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;

import java.util.Set;

public interface SportService {
    Set<SportDto> getAllSports();

    SportDto getSportById(Long sportId);

    SportDto saveSport(SportDto sportDto);

    SportDto updateSport(SportDto sportDto, Long sportId);

    void deleteSport(Long sportId);
}
