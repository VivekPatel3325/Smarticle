package com.asdc.smarticle.article;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Article,Long> {

    Optional<Article> findById(Long id);

    Iterable<Article> findAll();

    @Query(value = "SELECT * FROM articles a WHERE a.visibility=1;",
            nativeQuery = true)
    Iterable<Article> findByVisibility();

    @Query(value = "SELECT * FROM articles a WHERE a.tagid in (12,1) or visibility=true;",
            nativeQuery = true)
    Iterable<Article> findByTagid();
}
