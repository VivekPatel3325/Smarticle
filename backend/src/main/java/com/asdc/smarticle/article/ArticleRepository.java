package com.asdc.smarticle.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Optional<Article> findById(Long id);

	List<Article> findAllByOrderByCreationDateAsc();
	
	List<Article> findByVisibility(boolean visibility);

}
