package net.piyush.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.piyush.dtos.PayLoadErrorDetails;

@RestControllerAdvice
public class ExceptionGlobalHandler extends ResponseEntityExceptionHandler{
   /***  
    Handling Specific exception
    *  specific exceptions are the class which we have created like ExceptionBlogAPI.class And
    *  ExceptionResourceNotFound.class 
    ***/
	
	@ExceptionHandler(ExceptionResourceNotFound.class)
	public ResponseEntity<?> handleExceptionResourceNotFound(ExceptionResourceNotFound exception,WebRequest webRequest){
		
		PayLoadErrorDetails payLoadErrorDetails = new PayLoadErrorDetails();
		payLoadErrorDetails.setDateTimeStamp(new Date());
		payLoadErrorDetails.setErrorMessage(exception.getMessage());
		payLoadErrorDetails.setDetailMesssage(webRequest.getDescription(false));
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.header("NOT FOUND", "NOT FOUND")
				.contentType(MediaType.APPLICATION_JSON)
				.body(payLoadErrorDetails);
	}

	@ExceptionHandler(ExceptionBlogAPI.class)
	public ResponseEntity<?> handleExceptionBlogAPI(ExceptionBlogAPI exception,WebRequest webRequest){
	
		PayLoadErrorDetails payLoadErrorDetails = new PayLoadErrorDetails();
		payLoadErrorDetails.setDateTimeStamp(new Date());
		payLoadErrorDetails.setErrorMessage(exception.getMessage());
		payLoadErrorDetails.setDetailMesssage(webRequest.getDescription(false));
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.header("BAD REQUEST", "BAD REQUEST")
				.contentType(MediaType.APPLICATION_JSON)
				.body(payLoadErrorDetails);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest webRequest){
	System.out.println("My Name Is Khan");
		PayLoadErrorDetails payLoadErrorDetails = new PayLoadErrorDetails();
		payLoadErrorDetails.setDateTimeStamp(new Date());
		payLoadErrorDetails.setErrorMessage(exception.getMessage());
		payLoadErrorDetails.setDetailMesssage(webRequest.getDescription(false));
		
		PayLoadErrorDetails errorDetails = new PayLoadErrorDetails(new Date(), exception.getMessage(),
	                webRequest.getDescription(false));
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header("INTERNAL SERVER ERROR", "INTERNAL SERVER ERROR")
				.contentType(MediaType.APPLICATION_JSON)
				.body(errorDetails);
	}
	
	  
	/*THIS METHOD WILL HANDLE VALIDATION ERROR*/
	@Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	
		Map<String, String> errorMap  = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String strFieldName = ((FieldError)error).getField();
			String strMessage = error.getDefaultMessage();
			errorMap.put(strFieldName, strMessage);
		});
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
}
