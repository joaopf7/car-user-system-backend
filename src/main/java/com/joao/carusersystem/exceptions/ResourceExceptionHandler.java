package com.joao.carusersystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(NotFoundException ex, HttpServletRequest request) {
		StandardError error = new StandardError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrationViolationException.class)
	public ResponseEntity<StandardError> dataIntegrationViolationException(DataIntegrationViolationException ex, HttpServletRequest request) {
		StandardError error = new StandardError(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> dataIntegrationViolationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		StringBuilder s = new StringBuilder();
		s.append("Missing fields: ");
		for(FieldError e: ex.getBindingResult().getFieldErrors()) {
			s.append(" - ");
			s.append(e.getDefaultMessage());
		}
		StandardError error = new StandardError(s.toString(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


}
