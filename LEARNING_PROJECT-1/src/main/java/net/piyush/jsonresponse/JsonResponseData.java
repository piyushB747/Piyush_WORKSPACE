package net.piyush.jsonresponse;

import java.util.List;

import lombok.Data;
import net.piyush.entity.EntityJavaScheduled;

@Data
public class JsonResponseData {
	
	public String message;
	public String messageType;
	List<EntityJavaScheduled>  l_objList; 
}
