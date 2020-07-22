package com.incture.excption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandller {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(TagException exe)
	{
		
		ErrorResponse eer = new ErrorResponse();
		eer.setCode(HttpStatus.NOT_FOUND.value());
		eer.setMessage(exe.getMessage());
		eer.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(eer,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exe)
	{
		
		ErrorResponse eer = new ErrorResponse();
		eer.setCode(HttpStatus.BAD_REQUEST.value());
		eer.setMessage(exe.getMessage());
		eer.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(eer,HttpStatus.BAD_REQUEST);
		
	}
	


}
