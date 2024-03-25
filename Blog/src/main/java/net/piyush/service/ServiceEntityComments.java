package net.piyush.service;

import net.piyush.dtos.PayLoadComments;
import java.util.List;
public interface ServiceEntityComments {

	public abstract PayLoadComments createComments(Long id,PayLoadComments payLoadComments);
	
	public abstract List<PayLoadComments>  findCommentsByPostId(Long lngPostId);

	public abstract PayLoadComments getComentByIdAndPostId(Long lngPostId,Long lngCommentId);
	
	public abstract PayLoadComments updatePostById(Long lngPostId,Long lngCommentId,PayLoadComments payLoadComments);
	
	public abstract void deleteCommentByPostIdAndCommentId(Long lngPostId,Long lngCommentId);
	
	
}
