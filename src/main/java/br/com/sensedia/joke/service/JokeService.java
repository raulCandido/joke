package br.com.sensedia.joke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sensedia.joke.client.JokeApiClient;
import br.com.sensedia.joke.exception.JokeApiException;
import br.com.sensedia.joke.exception.OutOfJokeException;
import br.com.sensedia.joke.model.CategoryRate;
import br.com.sensedia.joke.model.Joke;
import br.com.sensedia.joke.repository.JokeRespository;

@Service
public class JokeService {

    @Autowired
    private JokeRespository jokeRepository;

    @Autowired
    private JokeApiClient jokeApiClient;

    private static final int QTDAPI = 342;

    public boolean isJokeExist(Integer id) {
	return jokeRepository.existsJokeByIdApi(id);
    }

    public Joke getJokeApi(String categories) {
	return jokeApiClient.getJokeApi(categories);
    }

    public void checkErrorInJokeApi(Joke joke) {
	if (joke.getError()) {
	    throw new JokeApiException("JOKE API IS BROKEN MAN");
	}
    }

    public void persistJoke(Joke joke) {
	jokeRepository.save(joke);
    }

    public Long findCount() {
	return jokeRepository.count();
    }

    public List<CategoryRate> findCategoryRateAvg() {
	List<CategoryRate> categoryRates = jokeRepository.findCategoryAverageRate();
	if (categoryRates.isEmpty()) {
	    throw new ResourceNotFoundException();
	}
	return categoryRates;

    }

    public List<Joke> findJokeRateIsNull() {
	List<Joke> jokes = jokeRepository.findByRateIsNull();
	if (jokes.isEmpty()) {
	    throw new ResourceNotFoundException();
	}
	return jokes;
    }

    public Joke findByIdApi(Integer id) {
	Joke joke = jokeRepository.findByIdApi(id);

	if (joke == null) {
	    throw new ResourceNotFoundException();
	}
	return joke;
    }

    public Joke handlePresentJoke(String categories) {
	Joke joke = new Joke();

	boolean isPresentJoke = true;

	while (isPresentJoke) {
	    if (findCount() >= QTDAPI) {
		throw new OutOfJokeException("You're out of jokes");
	    }
	    joke = getJokeApi(categories);
	    checkErrorInJokeApi(joke);
	    isPresentJoke = isJokeExist(joke.getIdApi());
	}
	return joke;
    }

}
