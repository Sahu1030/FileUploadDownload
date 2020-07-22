package com.incture.excption;

public class ErrorResponse {
	
	private int code;
	private String message;
	private long timeStamp;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(int code, String message, long timeStamp) {
		super();
		this.code = code;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
