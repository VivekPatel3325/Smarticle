package com.asdc.smarticle.article;

import com.asdc.smarticle.user.exception.ArticleException;

import java.util.List;

public interface ArticleService {

    Article saveArticle(Article postArticle,String useName)  throws ArticleException;

    Iterable<Article> getArticle();

    Iterable<Article> getPublicArticle() throws ArticleException;

    Iterable<Article> getArticleByTagandPublic();
}
