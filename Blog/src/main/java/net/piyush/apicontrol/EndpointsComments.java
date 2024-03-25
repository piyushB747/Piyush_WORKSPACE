package net.piyush.apicontrol;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.piyush.dtos.PayLoadComments;
import net.piyush.service.ServiceEntityComments;


@RestController
@RequestMapping("/endpointV1")
public class EndpointsComments {

	private ServiceEntityComments serviceEntityComments;
	
    public EndpointsComments(ServiceEntityComments serviceEntityComments)	{
    	super();
    	this.serviceEntityComments = serviceEntityComments;
    }
	
	
	/* CREATE COMMENTS FOR POST */
	@PostMapping("/{id}/comments")
	public ResponseEntity<?> createComments(@PathVariable(name="id")long id,@Valid @RequestBody PayLoadComments payLoadComments){
		
		PayLoadComments payLoadComments2 = serviceEntityComments.createComments(id, payLoadComments);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("created", "Comments Created")
				.contentType(MediaType.APPLICATION_JSON)
				.body(payLoadComments2);
	}
	
	/* FIND COMMENTS BY POST ID */
	@GetMapping("/{id}/comments")
	public ResponseEntity<?> findCommentsByPostId(@PathVariable(name="id")Long id){
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Comments By Post Id", "Comments By Post Id")
				.contentType(MediaType.APPLICATION_JSON)
				.body(serviceEntityComments.findCommentsByPostId(id));
	}
	
	/* FIND COMMENTS BY POST ID AND COMMENTS ID*/
	@GetMapping("/{id}/commentswithid/{commentid}")
	public ResponseEntity<?> findCommentsByPostIdAndCommentId(@PathVariable(name="id")Long id,
			@PathVariable(name="commentid")Long commentId){
	
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("Comments By Post Id", "Comments By Post Id")
				.contentType(MediaType.APPLICATION_JSON)
				.body(serviceEntityComments.getComentByIdAndPostId(id, commentId));
	}
	
	/* UPDATE COMMENTS BY POST ID AND COMMENT ID*/
	@PutMapping("/{idpost}/comments/{idcomment}")
	public ResponseEntity<?> updateComment(@PathVariable(name="idpost")long lngPostId,@PathVariable(name="idcomment")long lngCommentId,
		@Valid 	@RequestBody PayLoadComments payLoadComments){
		return new ResponseEntity<>(serviceEntityComments.updatePostById(lngPostId, lngCommentId, payLoadComments), HttpStatus.OK);
	}
	
	/* DELETE COMMENTS BY POST ID AND COMMENT ID*/
	@DeleteMapping("/{idpost}/commentsdelete/{idcomment}")
	public ResponseEntity<?> deleteComment(@PathVariable(name="idpost")long lngPostId,@PathVariable(name="idcomment")long lngCommentId){
		serviceEntityComments.deleteCommentByPostIdAndCommentId(lngPostId, lngCommentId);
		return new ResponseEntity<>("DELETED SUCCESSFULLY",HttpStatus.OK);
	}
}
