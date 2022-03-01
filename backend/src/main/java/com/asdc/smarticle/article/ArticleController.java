package com.asdc.smarticle.article;

import com.asdc.smarticle.articletag.ArticleTagService;
import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.tag.TagService;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author Khushboo Patel
 * @version 1.0
 * @since 2022-02-26
 */
@RestController
@RequestMapping("/smarticleapi/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTagService articletagService;

    @Autowired
    TagService tagService;

    @GetMapping(ApplicationUrlPath.RETRIEVE_POST_ARTICLE)
    public Iterable<Article> retrieveArticle() {
        Iterable<Article> articles_list= articleService.getArticle();
        System.out.println(articles_list);
        return articles_list;

    }
    @GetMapping(ApplicationUrlPath.RETRIEVE_PUBLIC_POST_ARTICLE)
    public Iterable<Article> retrievePublicArticle() {
        Iterable<Article> articles_list= articleService.getPublicArticle();
        System.out.println(articles_list);
        return articles_list;

    }

    @PostMapping(ApplicationUrlPath.USER_POST_REQ_PATH)
    public void saveArticle(@RequestBody Article postArticle) throws ArticleException {
        articleService.saveArticle(postArticle) ;
        articletagService.saveArticleTag(postArticle);
        //tagService.createTag(postArticle);
    }

    @GetMapping(ApplicationUrlPath.RETRIEVE_PUBLIC_AND_PRIVATE_ARICLE)
    public Iterable<Article> retrievePublicandPrivateArticle(){
        Iterable<Article> all_article_list=articleService.getAllPublicandPrivateArticle();
        return all_article_list;
    }

    @GetMapping(ApplicationUrlPath.RETRIEVE_PUBLIC_AND_PRIVATE_ARICLE)
    public Iterable<Article> retrieveArticleByTag(){
        Iterable<Article> all_article_list=articleService.getArticleByTagandPublic();
        return all_article_list;
    }

}
