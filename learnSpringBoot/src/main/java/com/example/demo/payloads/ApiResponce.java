package com.example.demo.payloads;

public class ApiResponce {

	private String message;
	private boolean status;
	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponce(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
