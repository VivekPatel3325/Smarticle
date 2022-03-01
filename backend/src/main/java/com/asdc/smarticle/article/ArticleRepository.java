package com.asdc.smarticle.article;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-02-26
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article,Long> {

    Optional<Article> findById(Long id);

    Iterable<Article> findAll();

    Iterable<Article> findByVisibility(boolean isVisible);

}
