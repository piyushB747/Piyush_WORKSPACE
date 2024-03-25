package net.piyush.serjavascheduled;

import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.piyush.commons.CommonUtils;
import net.piyush.dto.DtoJavaProgress;
import net.piyush.dto.DtoJavaScheduledTask;
import net.piyush.entity.EntityJavaScheduled;
import net.piyush.repo.RepoEntityJavaScheduled;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ServiceEntityJavaScheduled {

	
	@Autowired(required = true)
	private ModelMapper modelMapper;
	
	@Autowired
	private RepoEntityJavaScheduled repoEntityJavaScheduled;
	
	public String saveTaskInDBForJava(DtoJavaScheduledTask dto) {
		try {
			
			dto.setUniqueId1Dto(CommonUtils.generateUniqueId());
			dto.setUniqueId2Dto(CommonUtils.generateUniqueId());
			
			EntityJavaScheduled e1= this.convertDtoToEntity(dto);
			
		    e1 = repoEntityJavaScheduled.save(e1);
		    System.out.println(e1.getIdJ());
            return String.valueOf(e1.getIdJ());
		}catch(Exception ex) { ex.printStackTrace(); }
	  return null;
	}

	public List<EntityJavaScheduled> returnListOfTaskDetails(){
		
		List<DtoJavaScheduledTask> l1 =new ArrayList<DtoJavaScheduledTask>();
		List<EntityJavaScheduled> l1_ObjList =repoEntityJavaScheduled.findAll();
		 
		/*
		l1= l1_ObjList
					.stream().map(this::convertEntityToDTo)
					.collect(Collectors.toList());
					*/
		return l1_ObjList;
	}
	
	/**Converting Entity Into DTO using Library**/
	public EntityJavaScheduled convertEntityToDto2(DtoJavaProgress user) {		
		
		EntityJavaScheduled userLocationDto=new EntityJavaScheduled();
		
		userLocationDto=this.modelMapper.map(user, EntityJavaScheduled.class);
		return userLocationDto;		
	}

	
	public EntityJavaScheduled convertDtoToEntity(DtoJavaScheduledTask dto) {		
	    ModelMapper modelMapper = new ModelMapper();
	    
	    
	    @SuppressWarnings("unused")
		TypeMap<DtoJavaScheduledTask, EntityJavaScheduled> typeMap = modelMapper
	            .createTypeMap(DtoJavaScheduledTask.class, EntityJavaScheduled.class)
	    .addMapping(DtoJavaScheduledTask::getUniqueId1Dto, EntityJavaScheduled::setUniqueIdJ1)
	    .addMapping(DtoJavaScheduledTask::getUniqueId2Dto, EntityJavaScheduled::setUniqueIdJ2);

	    EntityJavaScheduled entityJavaScheduled = modelMapper.map(dto, EntityJavaScheduled.class);

	    return entityJavaScheduled; 
	}
	
	public DtoJavaScheduledTask convertEntityToDTo(EntityJavaScheduled dto) {		
	    ModelMapper modelMapper = new ModelMapper();
	    
	    
	    @SuppressWarnings("unused")
		TypeMap<EntityJavaScheduled, DtoJavaScheduledTask> typeMap = modelMapper
	            .createTypeMap(EntityJavaScheduled.class, DtoJavaScheduledTask.class)
	    .addMapping(EntityJavaScheduled::getUniqueIdJ1, DtoJavaScheduledTask::setUniqueId1Dto)
	    .addMapping(EntityJavaScheduled::getUniqueIdJ2, DtoJavaScheduledTask::setUniqueId2Dto);

	    DtoJavaScheduledTask entityJavaScheduled = modelMapper.map(dto, DtoJavaScheduledTask.class);

	    return entityJavaScheduled; 
	}


	
}
