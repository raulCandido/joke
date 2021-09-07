package br.com.sensedia.joke.exception;

public class JokeApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JokeApiException(String message) {
	super(message);
    }

    public JokeApiException(String message, Throwable cause) {
	super(message, cause);
    }
}
