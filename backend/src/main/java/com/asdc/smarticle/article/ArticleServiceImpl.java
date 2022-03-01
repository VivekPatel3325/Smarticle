package com.asdc.smarticle.article;

import com.asdc.smarticle.comutil.ApiError;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-02-26
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article saveArticle(Article article) throws ArticleException{
        if((article.getHeading() ==null || article.getHeading().isEmpty() ) || (article.getContent().isEmpty() || article.getContent()==null)){
            throw new ArticleException(ApiError.ARTICLE_FIELD_NOT_NULL);
        }
        return articleRepository.save(article);
    }

    @Override
    public Iterable<Article> getArticle(){
        return articleRepository.findAll() ;
    }

    @Override
    public Iterable<Article> getPublicArticle(){
        return articleRepository.findByVisibility(true);
    }

}
