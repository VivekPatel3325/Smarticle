package com.asdc.smarticle.article;

import com.asdc.smarticle.articletag.TagRepository;
import com.asdc.smarticle.comutil.ApiError;
import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TagRepository tagRepository;

	@Override
	public Article saveArticle(Article article, String userName) throws ArticleException {

		User user = userRepository.findByUserName(userName);
		article.setUserId(user);
		System.out.println(article.getTagId());

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

	@Override
	public Article getArticleById(Long id) {
		Optional<Article> article = articleRepository.findById(id);
		if (article.isPresent()) {
			return article.get();
		}
		return null;
	}

	@Override
	public Page<Article> getArticleByUser(String userName, int page, int totalPage) {
		Pageable pagination = PageRequest.of(page, totalPage,Sort.by("creationDate"));
		Page<Article> listArticle = null;
		User user = userRepository.findByUserName(userName);
		listArticle = articleRepository.findByUserId(user,pagination);
		return listArticle;
	}

}
