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
import sin2cos2.extremeSportRestAPI.api.v1.dtos.RegionDto;
import sin2cos2.extremeSportRestAPI.services.RegionService;

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
@WebMvcTest(controllers = RegionController.class)
class RegionControllerTest {

    @MockBean
    private RegionService regionService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectWriter ow;

    private final String BASE_URL = "/api/v1/regions";

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void getAllRegions() throws Exception {
        Set<RegionDto> regionDtos = new LinkedHashSet<>();
        regionDtos.add(RegionDto.builder().build());
        regionDtos.add(RegionDto.builder().build());
        regionDtos.add(RegionDto.builder().build());

        when(regionService.getAllRegions(anyInt())).thenReturn(regionDtos);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getRegionsByName() throws Exception {
        Set<RegionDto> regionDtos = new HashSet<>();
        regionDtos.add(RegionDto.builder().build());
        regionDtos.add(RegionDto.builder().build());

        when(regionService.getRegionsByName(anyString())).thenReturn(regionDtos);

        mockMvc.perform(get(BASE_URL + "?name=Chisinau"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getRegionById() throws Exception {
        RegionDto regionDto = RegionDto.builder().name("test").build();

        when(regionService.getRegionDtoById(any())).thenReturn(regionDto);

        mockMvc.perform(get(BASE_URL + "/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    void saveRegion() throws Exception {
        RegionDto regionDto = RegionDto.builder().name("SaveTest").build();
        RegionDto toSave = RegionDto.builder().build();

        when(regionService.saveRegion(anyLong(), any())).thenReturn(regionDto);

        mockMvc.perform(post(BASE_URL + "?countryId=65")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("SaveTest")));
    }

    @Test
    void updateRegion() throws Exception {
        RegionDto regionDto = RegionDto.builder().name("SaveTest").build();
        RegionDto toSave = RegionDto.builder().build();

        when(regionService.updateRegion(anyLong(), any())).thenReturn(regionDto);

        mockMvc.perform(put(BASE_URL + "/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("SaveTest")));
    }

    @Test
    void deleteRegions() throws Exception {

        mockMvc.perform(delete(BASE_URL + "?countryId=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRegion() throws Exception {

        mockMvc.perform(delete(BASE_URL + "/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}