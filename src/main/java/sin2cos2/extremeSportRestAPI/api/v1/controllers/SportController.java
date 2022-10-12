package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all sports")
    @GetMapping
    public Set<SportDto> getAllSports() {
        return sportService.getAllSports();
    }

    @Operation(summary = "Get sport by id")
    @GetMapping("/{sportId}")
    public SportDto getSportById(@PathVariable String sportId) {
        return sportService.getSportDtoById(Long.valueOf(sportId));
    }

    @Operation(summary = "Save new sport")
    @PostMapping
    public SportDto saveSport(@RequestBody SportDto sportDto) {
        return sportService.saveSport(sportDto);
    }

    @Operation(summary = "Update existing sport by id")
    @PutMapping("/{sportId}")
    public SportDto updateSport(@RequestBody SportDto sportDto,
                                @PathVariable String sportId) {
        return sportService.updateSport(sportDto, Long.valueOf(sportId));
    }

    @Operation(summary = "Delete sport by id")
    @DeleteMapping("/{sportId}")
    public void deleteSport(@PathVariable String sportId) {
        sportService.deleteSport(Long.valueOf(sportId));
    }

}
