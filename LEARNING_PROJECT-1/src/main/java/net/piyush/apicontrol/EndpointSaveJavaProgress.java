package net.piyush.apicontrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.piyush.dto.DtoJavaScheduledTask;
import net.piyush.jsonresponse.JsonMessage;
import net.piyush.serjavascheduled.ServiceEntityJavaScheduled;

@RestController
@RequestMapping(value="/savedataforprogressjava", method = {RequestMethod.POST, RequestMethod.GET},
headers = "content-type=application/json",consumes = "application/json", produces = "application/json"
 ,name = "Save Data")
@CrossOrigin(origins="*")
public class EndpointSaveJavaProgress {

	@Autowired
	private ServiceEntityJavaScheduled serJavaScheduled;
	 
	@PostMapping
	public ResponseEntity<?> saveJavaProgressData(@RequestBody DtoJavaScheduledTask dto){
		
		String returnEdValue =  serJavaScheduled.saveTaskInDBForJava(dto);
		JsonMessage json = new JsonMessage();
		if(returnEdValue!=null && returnEdValue.equals("")==false) {
			json.setMessageType("success");
			json.setMessage("Added Successfully");
			return ResponseEntity.status(HttpStatus.OK).header("custom-header", "treeData")
					.contentType(MediaType.APPLICATION_JSON).body(json);	
		}
		
		json.setMessageType("error");
		json.setMessage("Please Try Letter");
		return ResponseEntity.status(HttpStatus.OK).header("custom-header", "treeData")
				.contentType(MediaType.APPLICATION_JSON).body(json);	
	}
	
}
