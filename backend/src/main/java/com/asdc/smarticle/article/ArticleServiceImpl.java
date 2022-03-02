package com.asdc.smarticle.article;

import com.asdc.smarticle.comutil.ApiError;
import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Article saveArticle(Article article, String userName) throws ArticleException {

		User user = userRepository.findByUserName(userName);
		article.setUserId(user);

		if ((article.getHeading() == null || article.getHeading().isEmpty())
				|| (article.getContent().isEmpty() || article.getContent() == null)) {
			throw new ArticleException(ApiError.ARTICLE_FIELD_NOT_NULL);
		}

		return articleRepository.save(article);
	}

	@Override
	public List<Article> getArticle(String visibility) throws ArticleException {

		System.out.println(visibility);
		List<Article> articleList = null;
		try {

			if (visibility.equalsIgnoreCase(ApplicationUrlPath.ALL_ARTICLE)) {
				articleList = articleRepository.findAll();
			} else {
				articleList = articleRepository.findByVisibility(visibility.equals("1") ? true : false);
			}

		} catch (Exception e) {
			throw new ArticleException(ApiError.ARTICLE_NOT_PRESENT);
		}
		return articleList;
	}

}
