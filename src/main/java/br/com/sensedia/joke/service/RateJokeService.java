package br.com.sensedia.joke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sensedia.joke.model.Rate;
import br.com.sensedia.joke.repository.RateRepository;

@Service
public class RateJokeService {

    @Autowired
    private RateRepository rateRepository;

    public Rate saveRateJoke(Rate rateJoke) {
	return rateRepository.save(rateJoke);
    }
}
