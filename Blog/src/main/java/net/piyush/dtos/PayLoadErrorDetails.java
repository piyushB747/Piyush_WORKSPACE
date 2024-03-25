package net.piyush.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PayLoadErrorDetails {

	public Date dateTimeStamp;
	
	public String errorMessage;
	
	public String detailMesssage;

	public PayLoadErrorDetails(Date dateTimeStamp, String errorMessage, String detailMesssage) {
		super();
		this.dateTimeStamp = dateTimeStamp;
		this.errorMessage = errorMessage;
		this.detailMesssage = detailMesssage;
	}
	
	
	
}
