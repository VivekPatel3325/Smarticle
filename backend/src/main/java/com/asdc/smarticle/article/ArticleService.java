package com.asdc.smarticle.article;

import com.asdc.smarticle.user.exception.ArticleException;

import java.util.List;

public interface ArticleService {

    Article saveArticle(Article postArticle)  throws ArticleException;

    Iterable<Article> getArticle();

    Iterable<Article> getAllPublicandPrivateArticle();

    Iterable<Article> getPublicArticle();

    Iterable<Article> getArticleByTagandPublic();
}
