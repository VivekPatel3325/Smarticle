package com.asdc.smarticle.articletag;

import com.asdc.smarticle.article.Article;
import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smarticleapi/articletag")
public class ArticleTagController {


    @Autowired
    ArticleTagService articletagService;

    @PostMapping(ApplicationUrlPath.SAVE_DATA_ARTICLETAG)
    public void saveArticleTag(Article postArticle) throws ArticleException {
        articletagService.saveArticleTag(postArticle);
        //tagService.createTag(postArticle);
    }

}
