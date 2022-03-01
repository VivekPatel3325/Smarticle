package com.asdc.smarticle.articletag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagRepository extends CrudRepository<ArticleTag,Long> {

}
