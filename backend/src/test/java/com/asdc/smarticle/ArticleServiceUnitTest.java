package com.asdc.smarticle;

import com.asdc.smarticle.article.Article;
import com.asdc.smarticle.article.ArticleRepository;
import com.asdc.smarticle.article.ArticleServiceImpl;

import com.asdc.smarticle.user.exception.ArticleException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceUnitTest {
    @Test
    void contextLoads() {
    }

    @InjectMocks
    private ArticleServiceImpl articleService;// system under test

    @Mock
    private ArticleRepository articleRepo;// Dependency is mocked.


    @MockBean
    private Article article;


    @Test
    void retrievesaveArticles() {

        Iterable<Article> articleEntity =null;

        Mockito.when(articleRepo.findAll()).thenReturn(articleEntity);

        Assert.assertEquals(null,articleService.getArticle());

    }

    @Test
    void saveArticles() {


        Article article1 = new Article();
        article.setContent("My first article Heading");
        article.setHeading("My first article");
        article.setVisibility(true);

        try {
            Mockito.when(articleService.saveArticle(article)).thenReturn(article1);
        }catch(ArticleException ae){
            ae.printStackTrace();
        }


    }

}
