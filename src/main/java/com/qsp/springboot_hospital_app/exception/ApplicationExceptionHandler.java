package com.qsp.springboot_hospital_app.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.springboot_hospital_app.util.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	private ResponseStructure<String> structure = new ResponseStructure<>(); 
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<>();
		
		for (ObjectError objectError : error) {
			map.put(((FieldError)objectError).getField(),((FieldError)objectError).getDefaultMessage());
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(IdNotFoundException exception){
		structure.setMessage(exception.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Id Not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
