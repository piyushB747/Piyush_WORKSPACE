package net.piyush.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.piyush.dtos.JsonPayLoadPost;
import net.piyush.dtos.PayLoadPost;
import net.piyush.entity.EntityPosts;
import net.piyush.exception.ExceptionResourceNotFound;
import net.piyush.repository.RepoEntityPosts;
import net.piyush.service.ServiceEntityPosts;
@Service
public class ImplServiceEntityPosts implements ServiceEntityPosts{
	
	private RepoEntityPosts repoEntityPost;
	
	private ModelMapper mapper;
	
	public ImplServiceEntityPosts(RepoEntityPosts repoEntityPost,ModelMapper mapper) {
		super();
		this.repoEntityPost = repoEntityPost;
		this.mapper = mapper;
	}
	
	
	/**CREATE POST***/
	@Override
	public PayLoadPost createPost(PayLoadPost post) {

		// convert DTO to entity
        EntityPosts entityPosts = new EntityPosts();
        entityPosts.setContent(post.getContent());
        entityPosts.setDescription(post.getDescription());
        entityPosts.setTitle(post.getTitle());
        
        System.out.println("FUCKY YOU MEN");
        EntityPosts e1=repoEntityPost.save(entityPosts);
        System.out.println("RCUK FUCKY YOU MEN");
        // convert entity to dto 
        PayLoadPost dto = convertEntityToDtoManual(e1);
		/*
		 * dto.setContent(e1.getContent()); dto.setDescription(e1.getDescription());
		 * dto.setTitle(e1.getTitle()); dto.setId(e1.getId());
		 */  
        return dto;
	} 
	
    /***FIND ALL POST WITH PAGE SIZE AND PAGE NO***/
	@Override
	public List<PayLoadPost> getAllPostWithPageination(int pageNo, int pageSize) {
		
		Pageable pageable  =  PageRequest.of(pageNo, pageSize);
		Page<EntityPosts>  listEntityPost = repoEntityPost.findAll(pageable);
		List<EntityPosts>  l_objEntityPosts =  listEntityPost.getContent();
		
		
		List<PayLoadPost> l_objPayLoadList = l_objEntityPosts
				.stream()
				.map(x -> convertEntityToDtoManual(x))
				.collect(Collectors.toList());
		
		
		return l_objPayLoadList;
	}

	/***FIND ALL POST WITH PAGE SIZE AND PAGE NO WITH JSON PAYLOAD CONTENT***/
	@Override
	public JsonPayLoadPost getAllPostWithPageinationWithJsonResponse(int pageNo, int pageSize) {
		Pageable pageable  =  PageRequest.of(pageNo, pageSize);
		Page<EntityPosts>  pageObjEntityPost = repoEntityPost.findAll(pageable);
		List<EntityPosts>  l_objEntityPosts =  pageObjEntityPost.getContent();
		List<PayLoadPost> l_objPayLoadList = l_objEntityPosts
				.stream()
				.map(x -> convertEntityToDtoManual(x))
				.collect(Collectors.toList());	
		JsonPayLoadPost postResponse = new JsonPayLoadPost();
        postResponse.setContent(l_objPayLoadList);
        postResponse.setPageNo(pageObjEntityPost.getNumber());
        postResponse.setPageSize(pageObjEntityPost.getSize());
        postResponse.setTotalElements(pageObjEntityPost.getTotalElements());
        postResponse.setTotalPages(pageObjEntityPost.getTotalPages());
        postResponse.setLast(pageObjEntityPost.isLast());
		
		return postResponse;
	}
	 
	/***FIND ALL POST WITH PAGE SIZE AND PAGE NO AND SORTING***/
	@Override
	public JsonPayLoadPost getAllPostWithPageinationAndSorting(int pageNo, int pageSize, String sortBy) {
       
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<EntityPosts> pageObjEntityPost =repoEntityPost.findAll(pageable);
		
		List<EntityPosts> l_objEntityPosts =pageObjEntityPost.getContent();
		
		List<PayLoadPost> l_objListPayLoad = l_objEntityPosts
				.stream()
				.map(x -> convertEntityToDtoManual(x))
				.collect(Collectors.toList());
		JsonPayLoadPost postResponse = new JsonPayLoadPost();
        postResponse.setContent(l_objListPayLoad);
        postResponse.setPageNo(pageObjEntityPost.getNumber());
        postResponse.setPageSize(pageObjEntityPost.getSize());
        postResponse.setTotalElements(pageObjEntityPost.getTotalElements());
        postResponse.setTotalPages(pageObjEntityPost.getTotalPages());
        postResponse.setLast(pageObjEntityPost.isLast());
		
		return postResponse;
	}

	/***FIND ALL POST WITH PAGE SIZE AND PAGE NO AND SORTING WITH DIRECTION***/
	@Override
	public JsonPayLoadPost getAllPostWithPageinationAndSortingWithDirection(int pageNo, int pageSize, String sortBy,
			String sortDir) {
        
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<EntityPosts> pageObjEntityPost =repoEntityPost.findAll(pageable);
		
		List<EntityPosts> l_objEntityPosts =pageObjEntityPost.getContent();
		
		List<PayLoadPost> l_objListPayLoad = l_objEntityPosts
				.stream()
				.map(x -> convertEntityToDtoManual(x))
				.collect(Collectors.toList());
		JsonPayLoadPost postResponse = new JsonPayLoadPost();
        postResponse.setContent(l_objListPayLoad);
        postResponse.setPageNo(pageObjEntityPost.getNumber());
        postResponse.setPageSize(pageObjEntityPost.getSize());
        postResponse.setTotalElements(pageObjEntityPost.getTotalElements());
        postResponse.setTotalPages(pageObjEntityPost.getTotalPages());
        postResponse.setLast(pageObjEntityPost.isLast());
		
		return postResponse;
		
	}
	
	
    /***FIND ALL POST***/
	@Override
	public List<PayLoadPost> getAllPost() {
		
		List<EntityPosts> l_objEntityPosts =  repoEntityPost.findAll();
		
		List<PayLoadPost> l_objPayLoadPosts = l_objEntityPosts
				.stream()
				.map(x -> convertEntityToDtoWithLibrary(x))
				.collect(Collectors.toList());	
		return l_objPayLoadPosts;
	}
	
	
	/***FIND POST BY ID***/
	@Override
	public PayLoadPost getPostById(Long id) {
		
		Optional<EntityPosts> entityPost = repoEntityPost.findById(id);
		if(entityPost.isPresent()) {
			
			PayLoadPost p1=new PayLoadPost();	
			EntityPosts e1 =entityPost.get();
			p1.setContent(e1.getContent());
			p1.setDescription(e1.getDescription());
			p1.setId(e1.getId());
			p1.setTitle(e1.getTitle());
			
			return p1;
		}else {
			throw new ExceptionResourceNotFound("Post", "id", id); 
		}
		
		
		/*
		EntityPosts e1 = repoEntityPost.findById(id).orElseThrow(() -> new ExceptionResourceNotFound("Post", "id", id));	
		PayLoadPost p1 = this.convertEntityToDtoManual(e1);
		return p1;
		*/
	}
	
	
	/***UPDATE POST BY ID***/
	@Override
	public PayLoadPost updatePostById(PayLoadPost post, Long id) {
		EntityPosts e1 = repoEntityPost.findById(id).orElseThrow(()-> new ExceptionResourceNotFound("POST", "id", id));
		e1.setContent(post.getContent());
		e1.setTitle(post.getTitle());
		e1.setDescription(post.getDescription());
		
		EntityPosts e2 =  repoEntityPost.save(e1);
		
		PayLoadPost p2= convertEntityToDtoManual(e2);
		return p2;
	}

	/***DELETE POST BY ID***/
	@Override
	public void deletePostById(long id) {
	
		/*
		repoEntityPost.deleteById(id);
		*/
		
		 EntityPosts post = repoEntityPost.findById(id).orElseThrow(() -> new ExceptionResourceNotFound("Post", "id", id));
		 repoEntityPost.delete(post);
	}

	public PayLoadPost convertEntityToDtoWithLibrary(EntityPosts entity) {
		PayLoadPost post = mapper.map(entity, PayLoadPost.class);
		return post;
	}

	public EntityPosts convertDtoToEntityWithLibrary(PayLoadPost dto) {
		EntityPosts e1 = mapper.map(dto, EntityPosts.class);
		return e1;
	}
	
	public PayLoadPost convertEntityToDtoManual(EntityPosts entity) {
		PayLoadPost p1=new PayLoadPost();
		p1.setContent(entity.getContent());
		p1.setDescription(entity.getDescription());
		p1.setTitle(entity.getTitle());
		p1.setId(entity.getId());
		return p1;
	}

	public EntityPosts convertDtoToEntity(PayLoadPost dto) {
		EntityPosts e1=new EntityPosts();
		e1.setContent(dto.getContent());
		e1.setDescription(dto.getDescription());
		e1.setTitle(dto.getTitle());
		e1.setId(dto.getId());
		return e1;
	}


}
