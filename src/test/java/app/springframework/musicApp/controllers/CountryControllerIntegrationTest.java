package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.http.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();



    @Test
    void getCountries() {

        assertAll("Countries testing",
                () -> assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/countries", List.class).size() == 2)

        );
    }

    @Test
    void getCountriesNames(){
        ResponseEntity<List> response = this.restTemplate.getForEntity("http://localhost:"+port+"/countries/names",List.class);
        //List<Country> countries = response.getBody();
        List<String> names = response.getBody();//countries.stream().map(c -> c.getName()).collect(Collectors.toList());
        assertAll("Countries names testing",
                ()-> assertTrue(names.contains("Colombia")),
                ()-> assertTrue(names.contains("Chile")));
    }



    @Test
    void createCountry() {
        Country country = new Country("Peru");

       // assertEquals("200 OK",this.restTemplate.postForEntity("http://localhost:"+port+"/countries",country,String.class).getStatusCodeValue());
        assertThat( this.restTemplate.postForEntity("http://localhost:"+port+"/countries",country,Country.class).getStatusCodeValue() == 200);
        //this.restTemplate.postForEntity("http://localhost:"+port+"/countries",country,Country.class);
    }

    @Test
    void updateCountry() {
        Country country = new Country("Ccolombia");
        HttpEntity<Country> request = new HttpEntity<Country>(country);
        this.restTemplate.exchange("http://localhost:"+port+"/countries/1",HttpMethod.PUT,request,Void.class);
        //assertThat(this.restTemplate.put("http://localhost:"+port+"/countries/1",country));

    }


}