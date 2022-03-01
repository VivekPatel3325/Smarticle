package com.asdc.smarticle.article;

import com.asdc.smarticle.user.exception.ArticleException;

/**
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-02-26
 */
public interface ArticleService {

    Article saveArticle(Article postArticle)  throws ArticleException;

    Iterable<Article> getArticle();

    Iterable<Article> getPublicArticle();
}
