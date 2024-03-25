package net.piyush.apicontrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.piyush.dto.DtoJavaScheduledTask;
import net.piyush.entity.EntityJavaScheduled;
import net.piyush.jsonresponse.JsonMessage;
import net.piyush.jsonresponse.JsonResponseData;
import net.piyush.serjavascheduled.ServiceEntityJavaScheduled;

@RestController
@RequestMapping(value="/fetchdetailofprogressjava", method = {RequestMethod.POST, RequestMethod.GET},
headers = "content-type=application/json",consumes = "application/json", produces = "application/json"
 ,name = "Fetch Progress Data")
@CrossOrigin(origins="*")
public class EndpointProgressJavaDetail {

	
	@Autowired
	private ServiceEntityJavaScheduled serviceEntityJavaScheduled;
	
	@GetMapping
	public ResponseEntity<?> saveJavaProgressData(){
		
		List<EntityJavaScheduled>  l1 =serviceEntityJavaScheduled.returnListOfTaskDetails();
		JsonResponseData json = new JsonResponseData();
		if(l1!=null && l1.isEmpty()==false) {
        	json.setMessage("There Are Data");
        	json.setMessageType("success");
        	json.setL_objList(l1);
        	
        	System.out.println(l1);
        	
        	return ResponseEntity
        			.status(HttpStatus.OK)
        			.header("custom-header","progressData")
        			.contentType(MediaType.APPLICATION_JSON)
        			.body(json);
        }

		
		json.setMessage("There is No Data");
    	json.setMessageType("error");
		return ResponseEntity.status(HttpStatus.OK).header("custom-header","progressData")
    			.contentType(MediaType.APPLICATION_JSON)
    			.body(json);
	}
	
}
