package com.gabriel.web.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabriel.web.services.exceptions.ResourceNotFoundException;

/*
 * @ControllerAdvice define que a classe será capaz de interceptar as exceções
 * que acontecerem para que ele possa executar um tratamento para elas 
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	
	/*
	 * Esse método vai interceptar qualquer exceção desse tipo e fará o devido tratamento
	 */
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
