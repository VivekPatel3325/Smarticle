package com.asdc.smarticle.articletag;

import com.asdc.smarticle.article.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTagRepository extends CrudRepository<ArticleTag,Long> {

}
