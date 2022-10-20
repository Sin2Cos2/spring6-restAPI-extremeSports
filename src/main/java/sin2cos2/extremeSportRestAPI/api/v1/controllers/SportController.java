package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.services.SportService;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/v1/sports")
@RestController
public class SportController {

    private final SportService sportService;

    @Operation(summary = "Get all sports",
            description = """
                    By default param page will be set with 1.
                                        
                    Specify param name to get set of sports by name.
                    Max size of set is 10.
                    """)
    @GetMapping
    public Set<SportDto> getAllSports(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(required = false) String name) {

        if (name != null)
            return sportService.getSportByName(name);

        return sportService.getAllSports(page);
    }

    @Operation(summary = "Get sport by id")
    @GetMapping("/{sportId}")
    public SportDto getSportById(@PathVariable String sportId) {
        return sportService.getSportDtoById(Long.valueOf(sportId));
    }

    @Operation(summary = "Save new sport")
    @ResponseStatus(HttpStatus.CREATED)
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
