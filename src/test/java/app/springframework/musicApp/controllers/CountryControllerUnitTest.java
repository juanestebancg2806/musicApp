package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class CountryControllerUnitTest {
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    Country country1,country2;

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;


    @BeforeEach
    void setUp() {
        country1 = new Country("Colombia");
        country1.setId(1L);
        country2 = new Country("Chile");
        country2.setId(2L);
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();//Asegura que se esta usando mockito y no tomcat webserver
    }

    @Test
    void getCountriesNames() throws Exception {
        List<Country> countries = new ArrayList<>(Arrays.asList(country1,country2));
        Mockito.when(countryService.getNames()).thenReturn(countries.stream().map(c -> c.getName()).collect(Collectors.toList()));
        mockMvc.perform(MockMvcRequestBuilders.get("/countries/names").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0]", is("Colombia"))).andDo(MockMvcResultHandlers.print());
    }



    @Test
    void updateCountry() throws Exception {
        Country updatedCountry = new Country("ccolomiba");
        updatedCountry.setId(1L);
        Mockito.when(countryService.updateById(country1,country1.getId())).thenReturn(updatedCountry);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/countries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(updatedCountry));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("ccolomiba")));
    }
}