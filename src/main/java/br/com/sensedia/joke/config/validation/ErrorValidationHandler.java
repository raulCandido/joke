package br.com.sensedia.joke.config.validation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.sensedia.joke.exception.JokeApiException;
import br.com.sensedia.joke.exception.MessageError;
import br.com.sensedia.joke.exception.OutOfJokeException;

@RestControllerAdvice
public class ErrorValidationHandler {

    @ExceptionHandler(OutOfJokeException.class)
    public ResponseEntity<MessageError> outOfJokeException(OutOfJokeException e) {
	MessageError messageError = new MessageError(e.getMessage());
	return ResponseEntity.status(HttpStatus.OK).body(messageError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageError> resourceNotFoundException(ResourceNotFoundException e) {
	MessageError messageError = new MessageError(e.getMessage());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
    }

    @ExceptionHandler(JokeApiException.class)
    public ResponseEntity<MessageError> jokeApiException(JokeApiException e) {
	MessageError messageError = new MessageError(e.getMessage());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }

}
