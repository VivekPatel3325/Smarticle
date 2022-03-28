package com.asdc.smarticle.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asdc.smarticle.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Optional<Article> findById(Long id);

	List<Article> findAllByOrderByCreationDateAsc();
	
	List<Article> findByVisibility(boolean visibility);

	Page<Article> findByUserId(User user, Pageable pagination);

	List<Article> findAll();
}
