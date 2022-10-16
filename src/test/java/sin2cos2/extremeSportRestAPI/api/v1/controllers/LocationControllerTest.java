package sin2cos2.extremeSportRestAPI.api.v1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sin2cos2.extremeSportRestAPI.api.v1.dtos.LocationDto;
import sin2cos2.extremeSportRestAPI.services.LocationService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LocationController.class)
class LocationControllerTest {

    @MockBean
    private LocationService locationService;

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = "/api/v1/locations";
    private ObjectWriter ow;

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void getAllLocations() throws Exception {
        Set<LocationDto> locationDtoSet = new HashSet<>();
        locationDtoSet.add(new LocationDto());
        locationDtoSet.add(new LocationDto());

        when(locationService.getAllLocations()).thenReturn(locationDtoSet);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getLocationsByRegion() throws Exception {
        Set<LocationDto> locationDtoSet = new HashSet<>();
        locationDtoSet.add(new LocationDto());
        locationDtoSet.add(new LocationDto());

        when(locationService.getLocationsByRegion(anyLong())).thenReturn(locationDtoSet);

        mockMvc.perform(get(BASE_URL + "?regionId=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getLocationsByCountry() throws Exception {
        Set<LocationDto> locationDtoSet = new HashSet<>();
        locationDtoSet.add(new LocationDto());
        locationDtoSet.add(new LocationDto());

        when(locationService.getLocationsByCountry(anyLong())).thenReturn(locationDtoSet);

        mockMvc.perform(get(BASE_URL + "?countryId=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getLocationsTwoParams() throws Exception {
        Set<LocationDto> locationDtoSet1 = new HashSet<>();
        locationDtoSet1.add(new LocationDto());
        locationDtoSet1.add(new LocationDto());

        Set<LocationDto> locationDtoSet2 = new HashSet<>();
        locationDtoSet2.add(new LocationDto());
        locationDtoSet2.add(new LocationDto());
        locationDtoSet2.add(new LocationDto());

        when(locationService.getLocationsByCountry(anyLong())).thenReturn(locationDtoSet1);
        when(locationService.getLocationsByRegion(anyLong())).thenReturn(locationDtoSet2);

        mockMvc.perform(get(BASE_URL + "?countryId=2&regionId=6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getLocationById() throws Exception {
        LocationDto locationDto = LocationDto.builder().name("saved").build();

        when(locationService.getLocationDtoById(anyLong())).thenReturn(locationDto);

        mockMvc.perform(get(BASE_URL + "/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("saved")));
    }

    @Test
    void saveLocation() throws Exception {
        LocationDto toSave = new LocationDto();
        LocationDto saved = LocationDto.builder().name("saved").build();

        when(locationService.saveLocation(any(), anyLong(), anyLong())).thenReturn(saved);

        mockMvc.perform(post(BASE_URL + "?countryId=2&regionId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("saved")));
    }

    @Test
    void updateLocation() throws Exception {
        LocationDto toSave = new LocationDto();
        LocationDto updated = LocationDto.builder().name("updated").build();

        when(locationService.updateLocation(any(), anyLong())).thenReturn(updated);

        mockMvc.perform(put(BASE_URL + "/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("updated")));
    }

    @Test
    void deleteLocations() throws Exception {

        mockMvc.perform(delete(BASE_URL + "?regionId=3"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLocation() throws Exception {

        mockMvc.perform(delete(BASE_URL))
                .andExpect(status().isOk());
    }
}