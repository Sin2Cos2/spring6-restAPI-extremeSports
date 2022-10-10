package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.services.SportService;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/v1/sports")
@RestController
public class SportController {

    private final SportService sportService;

    @GetMapping
    public Set<SportDto> getAllSports() {
        return sportService.getAllSports();
    }

    @GetMapping("/{sportId}")
    public SportDto getSportById(@PathVariable String sportId) {
        return sportService.getSportById(Long.valueOf(sportId));
    }

    @PostMapping
    public SportDto saveSport(@RequestBody SportDto sportDto) {
        return sportService.saveSport(sportDto);
    }

    @PutMapping("/{sportId}")
    public SportDto updateSport(@RequestBody SportDto sportDto,
                                @PathVariable String sportId) {
        return sportService.updateSport(sportDto, Long.valueOf(sportId));
    }

    @DeleteMapping("/{sportId}")
    public void deleteSport(@PathVariable String sportId) {
        sportService.deleteSport(Long.valueOf(sportId));
    }

}
