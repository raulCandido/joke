package br.com.sensedia.joke.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sensedia.joke.model.Joke;

@FeignClient(name = "joke", url = "https://v2.jokeapi.dev/joke")
public interface JokeApiClient {

    @GetMapping(value = "/{categories}?safe-mode")
    Joke getJokeApi(@RequestParam(value = "categories") String c);
}
