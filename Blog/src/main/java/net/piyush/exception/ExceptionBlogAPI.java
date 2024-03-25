package net.piyush.exception;

import org.springframework.http.HttpStatus;

public class ExceptionBlogAPI extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	
	public ExceptionBlogAPI(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}


  
	
	
}
