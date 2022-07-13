package com.blogapp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	String resouceName;
	String resourceField;
	long resourceValue;
	public ResourceNotFoundException(String resouceName, String resourceField, long resourceValue) {
		super(String.format("%s not found with %s : %s", resouceName,resourceField,resourceValue));
		this.resouceName = resouceName;
		this.resourceField = resourceField;
		this.resourceValue = resourceValue;
	}
	

}
