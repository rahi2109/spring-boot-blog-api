package com.blogapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapp.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotfoundExceptionHandler(ResourceNotFoundException ex){
		String msg=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		
		Map<String, String>map=new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName=((FieldError)error).getField();
			String msg=error.getDefaultMessage();
			map.put(fieldName,msg);
		});
		

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.BAD_REQUEST);
	}
}
