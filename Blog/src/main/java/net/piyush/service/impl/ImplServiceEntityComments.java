package net.piyush.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import net.piyush.dtos.PayLoadComments;
import net.piyush.entity.EntityComments;
import net.piyush.entity.EntityPosts;
import net.piyush.exception.ExceptionBlogAPI;
import net.piyush.exception.ExceptionResourceNotFound;
import net.piyush.repository.RepoEntityComments;
import net.piyush.repository.RepoEntityPosts;
import net.piyush.service.ServiceEntityComments;

@AllArgsConstructor
@Service
public class ImplServiceEntityComments implements ServiceEntityComments {

	private RepoEntityComments repoEntityComments;
	private RepoEntityPosts repoEntityPosts;
	private ModelMapper mapper;

	/* CREATE COMMENTS FOR POST */
	@Override
	public PayLoadComments createComments(Long id, PayLoadComments payLoadComments) {

		EntityPosts entityPosts = repoEntityPosts.findById(id)
				.orElseThrow(() -> new ExceptionResourceNotFound("Post", "Id", id));

		EntityComments entityComments = this.convertDtoToEntity(payLoadComments);
		entityComments.setPost(entityPosts);

		EntityComments e2 = repoEntityComments.save(entityComments);

		return convertEntityToDto(e2);
	}

	/* FIND COMMENTS BY POST ID */
	@Override
	public List<PayLoadComments> findCommentsByPostId(Long lngPostId) {

		List<EntityComments> l_objEntityCommentList = repoEntityComments.findByPostId(lngPostId);
		if (l_objEntityCommentList.isEmpty()) {
			throw new ExceptionResourceNotFound("Comments with Post ", "id", lngPostId);
		}
		return l_objEntityCommentList.stream().map(x -> convertEntityToDto(x)).collect(Collectors.toList());
	}

	/* FIND COMMENTS BY POST ID AND COMMENTS ID */
	@Override
	public PayLoadComments getComentByIdAndPostId(Long lngPostId, Long lngCommentId) {
		EntityPosts entityPosts = repoEntityPosts.findById(lngPostId)
				.orElseThrow(() -> new ExceptionResourceNotFound("Post", "id", lngPostId));

		EntityComments entityComments = repoEntityComments.findById(lngCommentId)
				.orElseThrow(() -> new ExceptionResourceNotFound("Comment", "id", lngCommentId));

		if (!entityPosts.getId().equals(entityComments.getPost().getId())) {
			throw new ExceptionBlogAPI(HttpStatus.BAD_REQUEST, "Comment Not Found");
		}

		return convertEntityToDto(entityComments);
	}

	/* UPDATE COMMENTS BY POST ID AND COMMENT ID */
	@Override
	public PayLoadComments updatePostById(Long lngPostId, Long lngCommentId, PayLoadComments payLoadComments) {

		EntityPosts entityPosts = repoEntityPosts.findById(lngPostId)
				.orElseThrow(() -> new ExceptionResourceNotFound("Post", "Id", lngPostId));

		EntityComments entityComments = repoEntityComments.findById(lngCommentId)
				.orElseThrow(() -> new ExceptionResourceNotFound("Comment", "Id", lngCommentId));

		if (!entityPosts.getId().equals(entityComments.getPost().getId())) {
			throw new ExceptionBlogAPI(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}

		entityComments.setName(payLoadComments.getName());
		entityComments.setBody(payLoadComments.getBody());
		entityComments.setEmail(payLoadComments.getEmail());
		entityComments.setPost(entityPosts);

		EntityComments e1 = repoEntityComments.save(entityComments);

		return convertEntityToDto(e1);
	}

	/* DELETE COMMENTS BY POST ID AND COMMENT ID */
	@Override
	public void deleteCommentByPostIdAndCommentId(Long lngPostId, Long lngCommentId) {
		EntityPosts entityPosts = repoEntityPosts.findById(lngPostId)
				.orElseThrow(() -> new ExceptionResourceNotFound("Post", "Id", lngPostId));

		EntityComments entityComments = repoEntityComments.findById(lngCommentId)
				.orElseThrow(() -> new ExceptionResourceNotFound("Comment", "Id", lngCommentId));

		if (!entityPosts.getId().equals(entityComments.getPost().getId())) {
			throw new ExceptionBlogAPI(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		} else {
			repoEntityComments.delete(entityComments);
		}
	}

	public PayLoadComments convertEntityToDtoUsingLibrary(EntityComments e1) {
		PayLoadComments p1 = mapper.map(e1, PayLoadComments.class);
		return p1;
	}

	public EntityComments convertDtoToEntityUsingLibrary(PayLoadComments commentDto) {
		EntityComments comment = mapper.map(commentDto, EntityComments.class);
		return comment;
	}

	public PayLoadComments convertEntityToDto(EntityComments e1) {
		PayLoadComments p1 = new PayLoadComments();
		p1.setBody(e1.getBody());
		p1.setEmail(e1.getEmail());
		p1.setName(e1.getName());
		p1.setId(e1.getId());
		return p1;
	}

	public EntityComments convertDtoToEntity(PayLoadComments commentDto) {
		EntityComments comment = new EntityComments();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return comment;
	}

}
