package br.com.sensedia.joke.repository;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.sensedia.joke.model.CategoryRate;
import br.com.sensedia.joke.model.Joke;
import br.com.sensedia.joke.model.Rate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class JokeRespositoryTest {

    @Autowired
    private JokeRespository jokeRespository;
    
    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void before() {
	criateJoke();
	persistShouldByIdApi();
    }

    private void criateJoke() {
	Joke jokePersist = new Joke();
	jokePersist.setIdApi(183);
	jokePersist.setCategory("Misc");
	jokePersist.setJoke("é uma piada");
	jokePersist.setType("single");
	em.persist(jokePersist);
    }

    private void persistShouldByIdApi() {
	Joke joke = jokeRespository.findByIdApi(183);
	Rate rate = new Rate(joke, 2);
	em.persist(rate);
    }

    @Test
    void handleFindJokeByIdApi() {
	Joke joke = jokeRespository.findByIdApi(183);
	Assert.assertNotNull(joke);
    }

    @Test
    void handleFindCategoriesWithAvg() {
	List<CategoryRate> list = jokeRespository.findCategoryAverageRate();
	assertFalse(list.isEmpty());
    }

}
