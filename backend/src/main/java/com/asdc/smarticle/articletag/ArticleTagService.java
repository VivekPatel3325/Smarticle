package com.asdc.smarticle.articletag;

import java.util.List;

import com.asdc.smarticle.article.Article;

public interface ArticleTagService {

    ArticleTag saveArticleTag(Article article);

	List<ArticleTag> getArticleTag();
}
