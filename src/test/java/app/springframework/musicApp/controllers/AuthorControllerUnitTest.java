package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.service.AuthorService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
class AuthorControllerUnitTest {

     MockMvc mockMvc;

     ObjectMapper objectMapper = new ObjectMapper();
     ObjectWriter objectWriter = objectMapper.writer();

    Author author1,author2;


    @Mock
     AuthorService authorService;

     @InjectMocks //Aqui se inyecta el authorService
     AuthorController authorController;


    @BeforeEach
    void setUp() {
        //Create authors
        Country country1 = new Country("Colombia");
        Country country2 = new Country("Chile");
        author1 = new Author("Luis miguel","Ap");
        author2 = new Author("autor2","apAutor2");
        author1.setId(1L);
        author2.setId(2L);
        author1.setCountry(country1);
        author2.setCountry(country2);
        //-------------

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();//Asegura que se esta usando mockito y no tomcat webserver
    }

    @Test
    void getAuthors() throws Exception{

        List<Author> authors = new ArrayList<>(Arrays.asList(author1,author2));

        Mockito.when(authorService.getAuthors()).thenReturn(authors);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].names", is("Luis miguel"))).andDo(MockMvcResultHandlers.print());


    }




}