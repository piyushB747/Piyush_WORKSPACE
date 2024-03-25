package net.piyush.apicontrol;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.piyush.dtos.JsonPayLoadPost;
import net.piyush.dtos.PayLoadPost;
import net.piyush.service.ServiceEntityPosts;
import net.piyush.util.UtilsCommon;

import java.util.List;

@RestController
@RequestMapping("/endpoint/post")
@CrossOrigin(origins = "*")
public class EndpointPost {

	private ServiceEntityPosts serEntityPosts;

	public EndpointPost(ServiceEntityPosts serEntityPosts) {
		super();
		this.serEntityPosts = serEntityPosts;
	}
	
	/**CREATE POST***/
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> createPostMethod(@Valid @RequestBody PayLoadPost postDto){
		try {
			
			PayLoadPost payLoad = serEntityPosts.createPost(postDto);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.header("post created", "createdpost")
					.contentType(MediaType.APPLICATION_JSON)
        			.body(payLoad);
			
		}catch(Exception ex) {  ex.printStackTrace(); }
		return null;
	}
	
	  /***FIND ALL POST***/
	@GetMapping
	public ResponseEntity<?> getAllPosts(){
		try {
			List<PayLoadPost>  l_objListPayLoad =serEntityPosts.getAllPost();
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.header("AllPost", "AllPosts")
					.contentType(MediaType.APPLICATION_JSON)
					.body(l_objListPayLoad);
			
		}catch(Exception ex) { ex.printStackTrace(); }
		return null;
	}
	
	  /***FIND ALL POST WITH PAGE SIZE AND PAGE NO***/
		@GetMapping("/pageination")
		public ResponseEntity<?> getAllPostsWithPageination(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
				@RequestParam(value="pageSize",defaultValue = "5",required = false)int pageSize){
			try {
				List<PayLoadPost>  l_objListPayLoad =serEntityPosts.getAllPostWithPageination(pageNo, pageSize);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.header("AllPost", "AllPosts")
						.contentType(MediaType.APPLICATION_JSON)
						.body(l_objListPayLoad);
				
			}catch(Exception ex) { ex.printStackTrace(); }
			return null;
		}
		
		/***FIND ALL POST WITH PAGE SIZE AND PAGE NO WITH JSON PAYLOAD CONTENT***/
		@GetMapping("/pageination/json")
		public ResponseEntity<?> getAllPostsWithPageinationWithJsonResponse(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
				@RequestParam(value="pageSize",defaultValue = "5",required = false)int pageSize){
			try {
				JsonPayLoadPost l_objListPayLoad =serEntityPosts.getAllPostWithPageinationWithJsonResponse(pageNo, pageSize);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.header("AllPost", "AllPosts")
						.contentType(MediaType.APPLICATION_JSON)
						.body(l_objListPayLoad);
				
			}catch(Exception ex) { ex.printStackTrace(); }
			return null;
		}
		
		/***FIND ALL POST WITH PAGE SIZE AND PAGE NO AND SORT BY***/
		@GetMapping("/pageination/sorting")
		public ResponseEntity<?> getAllPostsWithPageinationWithSortBy(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
				@RequestParam(value="pageSize",defaultValue = "5",required = false)int pageSize,
				@RequestParam(value="sortBy",defaultValue = "id",required = false)String sortBy){
			try {
				JsonPayLoadPost l_objListPayLoad =serEntityPosts.getAllPostWithPageinationAndSorting(pageNo, pageSize,sortBy);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.header("AllPost", "AllPosts")
						.contentType(MediaType.APPLICATION_JSON)
						.body(l_objListPayLoad);
				
			}catch(Exception ex) { ex.printStackTrace(); }
			return null;
		}
	
		/***FIND ALL POST WITH PAGE SIZE AND PAGE NO AND SORTING WITH DIRECTION***/
		@GetMapping("/pageination/sortdir")
		public ResponseEntity<?> getAllPostsWithPageinationWithSortDirection(
				@RequestParam(value="pageNo",defaultValue = UtilsCommon.PAGE_NO,required = false)int pageNo,
				@RequestParam(value="pageSize",defaultValue = UtilsCommon.PAGE_SIZE,required = false)int pageSize,
				@RequestParam(value="sortBy",defaultValue = UtilsCommon.SORT_BY,required = false)String sortBy,
				@RequestParam(value="sortDir",defaultValue = UtilsCommon.SORT_DIR,required = false)String sortDir){
			try {
				JsonPayLoadPost l_objListPayLoad =serEntityPosts.getAllPostWithPageinationAndSortingWithDirection(pageNo, pageSize,sortBy,sortDir);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.header("AllPost", "AllPosts")
						.contentType(MediaType.APPLICATION_JSON)
						.body(l_objListPayLoad);
				
			}catch(Exception ex) { ex.printStackTrace(); }
			return null;
		}
		
		
	/***FIND POST BY ID***/
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name="id")long id){
		PayLoadPost p1 = serEntityPosts.getPostById(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("id","id")
				.contentType(MediaType.APPLICATION_JSON)
				.body(p1);
	}
	
	/***UPDATE POST BY ID***/
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePostById(@Valid @RequestBody PayLoadPost payLoad,@PathVariable(name="id") long id){
		PayLoadPost p1 = serEntityPosts.updatePostById(payLoad, id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("id","id")
				.contentType(MediaType.APPLICATION_JSON)
				.body(p1);
	}
	
	/***DELETE BY ID***/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name="id")long id){
		serEntityPosts.deletePostById(id);
		
		 return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
	}
	
}
