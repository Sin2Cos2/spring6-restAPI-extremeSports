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
import sin2cos2.extremeSportRestAPI.api.v1.dtos.TripDto;
import sin2cos2.extremeSportRestAPI.services.TripService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TripController.class)
class TripControllerTest {

    private final String BASE_URL = "/api/v1/trips";
    private final BigDecimal price = new BigDecimal("888.1234");
    private final LocalDate startDate = LocalDate.of(2022, 11, 18);
    private final LocalDate endDate = LocalDate.of(2022, 12, 7);
    @MockBean
    private TripService tripService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectWriter ow;
    private TripDto tripDto;

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();

        tripDto = TripDto
                .builder()
                .price(price)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    @Test
    void getAllTrips() throws Exception {
        Set<TripDto> tripDtoSet = new HashSet<>();
        tripDtoSet.add(new TripDto());
        tripDtoSet.add(new TripDto());

        when(tripService.getAllTrips(anyInt())).thenReturn(tripDtoSet);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAllTripsByLocation() throws Exception {
        Set<TripDto> tripDtoSet = new HashSet<>();
        tripDtoSet.add(new TripDto());
        tripDtoSet.add(new TripDto());

        when(tripService.getAllTripsByLocation(anyLong(), anyInt())).thenReturn(tripDtoSet);

        mockMvc.perform(get(BASE_URL + "?locationId=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAllTripsBySport() throws Exception {
        Set<TripDto> tripDtoSet = new HashSet<>();
        tripDtoSet.add(new TripDto());
        tripDtoSet.add(new TripDto());

        when(tripService.getAllTripsBySport(anyLong(), anyInt())).thenReturn(tripDtoSet);

        mockMvc.perform(get(BASE_URL + "?sportId=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAllTripsTwoParams() throws Exception {
        Set<TripDto> tripDtoSet = new HashSet<>();
        tripDtoSet.add(new TripDto());
        tripDtoSet.add(new TripDto());
        tripDtoSet.add(new TripDto());

        when(tripService.getAllTripsByLocationAndSport(anyLong(), anyLong(), anyInt())).thenReturn(tripDtoSet);

        mockMvc.perform(get(BASE_URL + "?locationId=3&sportId=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getTripById() throws Exception {
        when(tripService.getTripById(anyLong())).thenReturn(tripDto);

        mockMvc.perform(get(BASE_URL + "/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", equalTo(price.doubleValue())))
                .andExpect(jsonPath("$.startDate", equalTo(startDate.toString())))
                .andExpect(jsonPath("$.endDate", equalTo(endDate.toString())));
    }

    @Test
    void saveTrip() throws Exception {
        TripDto toSave = new TripDto();

        when(tripService.saveTrip(anyLong(), anyLong(), any())).thenReturn(tripDto);

        mockMvc.perform(post(BASE_URL + "?locationId=3&sportId=22")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.price", equalTo(price.doubleValue())))
                .andExpect(jsonPath("$.startDate", equalTo(startDate.toString())))
                .andExpect(jsonPath("$.endDate", equalTo(endDate.toString())));
    }

    @Test
    void updateTrip() throws Exception {
        TripDto toSave = new TripDto();

        when(tripService.updateTrip(anyLong(), any())).thenReturn(tripDto);

        mockMvc.perform(put(BASE_URL + "/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", equalTo(price.doubleValue())))
                .andExpect(jsonPath("$.startDate", equalTo(startDate.toString())))
                .andExpect(jsonPath("$.endDate", equalTo(endDate.toString())));
    }

    @Test
    void patchTrip() throws Exception {
        TripDto toSave = new TripDto();

        when(tripService.patchTrip(anyLong(), any())).thenReturn(tripDto);

        mockMvc.perform(patch(BASE_URL + "/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(toSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", equalTo(price.doubleValue())))
                .andExpect(jsonPath("$.startDate", equalTo(startDate.toString())))
                .andExpect(jsonPath("$.endDate", equalTo(endDate.toString())));
    }

    @Test
    void deleteTrips() throws Exception {

        mockMvc.perform(delete(BASE_URL + "?sportId=3&locationId=44"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTrip() throws Exception {
        mockMvc.perform(delete(BASE_URL))
                .andExpect(status().isOk());
    }
}