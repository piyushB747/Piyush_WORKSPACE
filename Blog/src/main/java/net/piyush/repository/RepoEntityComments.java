package net.piyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.piyush.entity.EntityComments;
import java.util.List;

public interface RepoEntityComments extends JpaRepository<EntityComments, Long>{
	
	public abstract List<EntityComments> findByPostId(Long lngPostId);
	
}
