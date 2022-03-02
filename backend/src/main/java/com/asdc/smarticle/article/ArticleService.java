package com.asdc.smarticle.article;

import com.asdc.smarticle.user.exception.ArticleException;

import java.util.List;

public interface ArticleService {

    Article saveArticle(Article postArticle,String useName)  throws ArticleException;

    List<Article> getArticle(String visibility) throws ArticleException;

	}
