package com.asdc.smarticle.articletag;

import java.util.List;
import java.util.Set;

import com.asdc.smarticle.article.Article;

public interface TagService {

	List<Tag> getTags(String userName);
	
	List<Tag> createArticleTag(String tagName);
}
