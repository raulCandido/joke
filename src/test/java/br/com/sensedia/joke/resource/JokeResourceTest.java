package br.com.sensedia.joke.resource;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JokeResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaNaoPermitirColocarAvaliacaoMaiorQueCinco() throws Exception {
	URI uri = new URI("/v1/joke/rate");
	String json = "{\r\n" + "    \"id\": 8,\r\n" + "    \"rate\": 6\r\n" + "}";
	mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void deveriaNaoPermitirColocarAvaliacaoMenorQueUm() throws Exception {
	URI uri = new URI("/v1/joke/rate");
	String json = "{\r\n" + "    \"id\": 8,\r\n" + "    \"rate\": 0\r\n" + "}";
	mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void deveriaRetornarUmJoke() throws Exception {
	URI uri = new URI("/v1/joke");
	mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
    }
}
