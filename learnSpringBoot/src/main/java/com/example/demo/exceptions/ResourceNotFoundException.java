package com.example.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldname;
	long value;
	public ResourceNotFoundException(String resourceName, String fieldname, long value) {
		super(String.format("%s not found with %s : %l",resourceName,fieldname,value));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.value = value;
	}
	
	

}
