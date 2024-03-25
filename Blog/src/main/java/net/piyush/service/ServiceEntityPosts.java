package net.piyush.service;

import net.piyush.dtos.JsonPayLoadPost;
import net.piyush.dtos.PayLoadPost;
import java.util.List;
public interface ServiceEntityPosts {

	public abstract PayLoadPost createPost(PayLoadPost post);
	
	public abstract List<PayLoadPost> getAllPost();
	
	public abstract List<PayLoadPost> getAllPostWithPageination(int pageNo,int pageSize);
	
	public abstract JsonPayLoadPost getAllPostWithPageinationWithJsonResponse(int pageNo,int pageSize);
	
	public abstract JsonPayLoadPost getAllPostWithPageinationAndSorting(int pageNo,int pageSize,String sortBy);
	
	public abstract JsonPayLoadPost getAllPostWithPageinationAndSortingWithDirection(int pageNo,int pageSize,String sortBy,String sortDir);
	
	public abstract PayLoadPost getPostById(Long id);
	
	public abstract PayLoadPost updatePostById(PayLoadPost post,Long id);
	
	public abstract void deletePostById(long id);
	
	
}
