package br.com.sensedia.joke.model.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.sensedia.joke.model.Joke;
import br.com.sensedia.joke.model.Rate;
import br.com.sensedia.joke.service.JokeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateForm {

    @NotNull
    @JsonAlias("id")
    private Integer idJoke;

    @Min(value = 1)
    @Max(value = 5)
    private int rate;

    public Rate convertRateFormToRate(JokeService jokeService) {
	Joke joke = jokeService.findByIdApi(this.idJoke);
	return new Rate(joke, rate);
    }

}
