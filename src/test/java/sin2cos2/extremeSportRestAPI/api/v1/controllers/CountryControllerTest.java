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
import sin2cos2.extremeSportRestAPI.api.v1.dtos.CountryDto;
import sin2cos2.extremeSportRestAPI.services.CountryService;

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
@WebMvcTest(controllers = CountryController.class)
class CountryControllerTest {

    private final String BASE_URL = "/api/v1/countries/";
    @MockBean
    private CountryService countryService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectWriter ow;

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void getAllCountries() throws Exception {

        int page = 1;
        Set<CountryDto> countryDtos = new LinkedHashSet<>();
        countryDtos.add(CountryDto.builder().build());
        countryDtos.add(CountryDto.builder().build());

        when(countryService.getAllCountries(page)).thenReturn(countryDtos);

        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getCountriesByName() throws Exception {
        Set<CountryDto> countryDtos = new HashSet<>();
        countryDtos.add(CountryDto.builder().build());

        when(countryService.getCountriesByName(anyString())).thenReturn(countryDtos);

        mockMvc.perform(get(BASE_URL + "?name=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getCountryById() throws Exception {
        CountryDto countryDto = CountryDto.builder().name("test").build();

        when(countryService.getCountryDtoById(any())).thenReturn(countryDto);

        mockMvc.perform(get(BASE_URL + "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    void saveCountry() throws Exception {
        CountryDto countryDto = CountryDto.builder().name("test").build();
        CountryDto test = CountryDto.builder().build();

        when(countryService.saveCountry(any())).thenReturn(countryDto);

        mockMvc.perform(post(BASE_URL)
                        .content(ow.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    void updateCountry() throws Exception {
        CountryDto countryDto = CountryDto.builder().name("test").build();
        CountryDto test = CountryDto.builder().build();

        when(countryService.updateCountry(anyLong(), any())).thenReturn(countryDto);

        mockMvc.perform(put(BASE_URL + "3")
                        .content(ow.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    void deleteCountry() throws Exception {

        mockMvc.perform(delete(BASE_URL + "3"))
                .andExpect(status().isOk());
    }
}