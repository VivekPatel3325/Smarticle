package com.asdc.smarticle.article;

import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.httpresponse.ResponseVO;
import com.asdc.smarticle.security.JwtUtils;
import com.asdc.smarticle.user.exception.ArticleException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class ArticleController extends BaseController {

	@Autowired
	ArticleService articleService;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping(ApplicationUrlPath.RETRIEVE_ARTICLE)
	public List<Article> retrieveArticle(@RequestParam String visibility) throws ArticleException {
		List<Article> articles_list = articleService.getArticle(visibility);
		System.out.println(articles_list);
		return articles_list;
	}

	@PostMapping(ApplicationUrlPath.USER_POST_REQ_PATH)
	public ResponseVO<String> saveArticle(@RequestHeader HttpHeaders http, @RequestBody Article postArticle)
			throws ArticleException {

		String jwtToken = http.getFirst("jwt-token");

		String userName = jwtUtils.getUserNameFromJwt(jwtToken);
		articleService.saveArticle(postArticle, userName);
		return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
	}

	@GetMapping(ApplicationUrlPath.GET_ARTICLE_BY_ID)
	public List<Article> getArticleById(@RequestHeader HttpHeaders http, @RequestParam Long id) {
		Article article = articleService.getArticleById(id);
		List<Article> articleList = new ArrayList<Article>();
		articleList.add(article);
		return articleList;
	}

	@GetMapping(ApplicationUrlPath.GET_ARTICLE_BY_USER)
	public Page<Article> getArticleByUser(@RequestHeader HttpHeaders http, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int totalPage) {
		String jwtToken = http.getFirst("jwt-token");
		String userName = "";
		if (jwtToken != null && !jwtToken.isBlank()) {
			userName = jwtUtils.getUserNameFromJwt(jwtToken);
		}
		Page<Article> articleList = articleService.getArticleByUser(userName, page, totalPage);
		return articleList;
	}
}
