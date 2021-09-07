package br.com.sensedia.joke.model.dto;

import br.com.sensedia.joke.model.Joke;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JokeDto {

    private static final String SINGLETYPE = "single";
    private Integer id;
    private String joke;

    public JokeDto(Joke joke) {
	if (joke.getType().equals(SINGLETYPE)) {
	    this.id = joke.getIdApi();
	    this.joke = joke.getJoke();
	} else {
	    this.id = joke.getIdApi();
	    this.joke = joke.getSetup() + "\n " + joke.getDelivery();
	}
    }
}