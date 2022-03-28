package com.asdc.smarticle.articletag;

import java.util.List;
import java.util.Set;

import com.asdc.smarticle.article.Article;

/**
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-03-27
 */
public interface TagService {

	List<Tag> getTags(String userName);
	
	List<Tag> createArticleTag(String tagName);
}
