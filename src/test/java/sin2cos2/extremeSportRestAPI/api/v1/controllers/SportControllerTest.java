package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.SportDto;
import sin2cos2.extremeSportRestAPI.services.SportService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SportController.class)
class SportControllerTest {

    @MockBean
    private SportService sportService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectWriter ow;

    private final String BASE_URL = "/api/v1/sports";

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void getAllSports() throws Exception {
        Set<SportDto> sportDtos = new LinkedHashSet<>();
        sportDtos.add(new SportDto());

        when(sportService.getAllSports(anyInt())).thenReturn(sportDtos);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getSportByName() throws Exception {
        Set<SportDto> sportDtos = new HashSet<>();
        sportDtos.add(SportDto.builder().build());
        sportDtos.add(SportDto.builder().build());

        when(sportService.getSportByName(anyString())).thenReturn(sportDtos);

        mockMvc.perform(get(BASE_URL + "?name=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getSportById() throws Exception {
        SportDto sportDto = SportDto.builder().name("TestSport").build();

        when(sportService.getSportDtoById(anyLong())).thenReturn(sportDto);

        mockMvc.perform(get(BASE_URL + "/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("TestSport")));
    }

    @Test
    void saveSport() throws Exception {
        SportDto toSave = new SportDto();
        SportDto saved = SportDto.builder().name("saved").build();

        when(sportService.saveSport(any())).thenReturn(saved);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("saved")));
    }

    @Test
    void updateSport() throws Exception {
        SportDto toSave = new SportDto();
        SportDto saved = SportDto.builder().name("updated").build();

        when(sportService.updateSport(any(), anyLong())).thenReturn(saved);

        mockMvc.perform(put(BASE_URL + "/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("updated")));
    }

    @Test
    void deleteSport() throws Exception {

        mockMvc.perform(delete(BASE_URL + "/5"))
                .andExpect(status().isOk());
    }
}