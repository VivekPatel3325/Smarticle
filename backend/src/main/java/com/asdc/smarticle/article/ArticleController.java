package com.asdc.smarticle.article;

import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.httpresponse.ResponseVO;
import com.asdc.smarticle.security.JwtUtils;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.asdc.smarticle.httpresponse.BaseController;

/**
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-02-26
 */
@CrossOrigin
@RestController
@RequestMapping("/smarticleapi/article")
public class ArticleController extends BaseController{

    @Autowired
    ArticleService articleService;
    

	@Autowired
	JwtUtils jwtUtils;



    @GetMapping(ApplicationUrlPath.RETRIEVE_POST_ARTICLE)
    public Iterable<Article> retrieveArticle() {
        Iterable<Article> articles_list= articleService.getArticle();
        System.out.println(articles_list);
        return articles_list;
    }
    @GetMapping(ApplicationUrlPath.RETRIEVE_PUBLIC_POST_ARTICLE)
    public Iterable<Article> retrievePublicArticle() throws ArticleException {
        Iterable<Article> articles_list= articleService.getPublicArticle() ;
        System.out.println(articles_list);
        return articles_list;
    }

    @PostMapping(ApplicationUrlPath.USER_POST_REQ_PATH)
    public ResponseVO<String> saveArticle(@RequestHeader HttpHeaders http,@RequestBody Article postArticle) throws ArticleException {
        
    	String jwtToken = http.getFirst("jwt-token");
		
			String userName = jwtUtils.getUserNameFromJwt(jwtToken);
    	articleService.saveArticle(postArticle,userName) ;
        return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
    }

}
