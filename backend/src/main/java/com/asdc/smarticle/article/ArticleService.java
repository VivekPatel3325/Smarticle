package com.asdc.smarticle.article;

import com.asdc.smarticle.user.exception.ArticleException;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface ArticleService {

    Article saveArticle(Article postArticle,String useName)  throws ArticleException;

    List<Article> getArticle(String visibility) throws ArticleException;

	Article getArticleById(Long id);

	Page<Article> getArticleByUser(String userName, int page, int totalPage);

	List<Object> getTwitterCountOfArticleTags(Long id);
	}
