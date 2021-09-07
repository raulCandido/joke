package br.com.sensedia.joke.exception;

public class OutOfJokeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OutOfJokeException(String message) {
	super(message);
    }

    public OutOfJokeException(String message, Throwable cause) {
	super(message, cause);
    }
}
