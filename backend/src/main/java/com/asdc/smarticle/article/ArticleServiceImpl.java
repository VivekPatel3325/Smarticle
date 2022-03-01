package com.asdc.smarticle.article;

import com.asdc.smarticle.comutil.ApiError;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Iterable<Article> getPublicArticle() throws ArticleException {
        try {
            return articleRepository.findByVisibility();
        }catch (Exception e){
            throw new ArticleException(ApiError.ARTICLE_NOT_PRESENT);
        }
    }

    @Override
    public Iterable<Article> getArticleByTagandPublic() {
        return null;
    }



}
