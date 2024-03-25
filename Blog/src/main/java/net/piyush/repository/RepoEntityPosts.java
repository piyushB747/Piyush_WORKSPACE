package net.piyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.piyush.entity.EntityPosts;

public interface RepoEntityPosts extends JpaRepository<EntityPosts, Long>{

}
