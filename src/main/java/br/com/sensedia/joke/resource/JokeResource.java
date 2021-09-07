package br.com.sensedia.joke.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sensedia.joke.model.CategoryRate;
import br.com.sensedia.joke.model.Joke;
import br.com.sensedia.joke.model.Rate;
import br.com.sensedia.joke.model.dto.JokeDto;
import br.com.sensedia.joke.model.form.RateForm;
import br.com.sensedia.joke.service.JokeService;
import br.com.sensedia.joke.service.RateJokeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/joke")
public class JokeResource {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private RateJokeService rateJokeService;

    @ApiOperation(value = "Get Joke", notes = "This method return a joke")
    @GetMapping(produces = "application/json")
    public ResponseEntity<JokeDto> consumeJokeApi(
	    @ApiParam(name = "categories", type = "String", value = "Any, Misc, Programming, Pun, Spooky, Christmas", example = "Christmas", required = false)
	    @RequestParam(value = "categories", defaultValue = "Any", required = false) String categories) {

	Joke joke = jokeService.handlePresentJoke(categories);
	jokeService.persistJoke(joke);
	return ResponseEntity.ok(new JokeDto(joke));
    }

    @ApiOperation(value = "Insert rate Joke", notes = "This method insert a rate Joke")
    @PostMapping(path = "rate", produces = "application/json")
    public ResponseEntity<Rate> insertRateJoke(@Valid @RequestBody RateForm rateJokeForm,
	    UriComponentsBuilder builder) {
	Rate joke = rateJokeForm.convertRateFormToRate(jokeService);
	Rate rateJoke = rateJokeService.saveRateJoke(joke);
	URI uri = builder.path("/{id}").buildAndExpand(rateJoke.getId()).toUri();
	return ResponseEntity.created(uri).body(rateJoke);
    }

    @ApiOperation(value = "Get average category", notes = "List of the categories sorted by their average rating")
    @GetMapping(path = "/average", produces = "application/json")
    public List<CategoryRate> findCategoriesJokeByAVG() {
	return jokeService.findCategoryRateAvg();
    }

    @ApiOperation(value = "Unrated jokes", notes = "List unrated jokes")
    @GetMapping(path = "/unrateds", produces = "application/json")
    public List<JokeDto> findUnratedJoke() {
	List<Joke> jokes = jokeService.findJokeRateIsNull();
	List<JokeDto> jokesDto = jokes.stream().map(j -> new JokeDto(j)).collect(Collectors.toList());
	return jokesDto;
    }

}
