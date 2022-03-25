package com.asdc.smarticle.article;

import com.asdc.smarticle.articletag.Tag;
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
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.util.*;

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

	@Override
	public Map<String,String> getTwitterCountOfArticleTags(Long id){
		Article article = getArticleById(id);
		Set<Tag> tags = article.getTagId();
		List<String> tagNames = new ArrayList<>();
		Map<String,String> responseTweetTextAndURL = new HashMap<>();
		String query = "lang:en (";
		for(Tag tag : tags){
			//System.out.println(tag.getTagName());
			query += tag.getTagName() +" OR ";
			tagNames.add(tag.getTagName());
		}

		String searchQuery = query.substring(0, query.length()-4);
		searchQuery += ")";
		Twitter twitter = authentication();

		Query search = new Query(searchQuery);
		search.count(5);
		QueryResult tweetData;
		try {
			tweetData = twitter.search(search);

			for (Status tweet : tweetData.getTweets()) {
				String url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
				String removeURL = tweet.getText().replaceAll("((https?|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", "");
				String transformedTweetText = removeURL.replaceAll("[^\\w\\s]", "");
				System.out.println(transformedTweetText);
				responseTweetTextAndURL.put(transformedTweetText, url);

			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return responseTweetTextAndURL;
	}

	//Reference: https://www.tabnine.com/code/java/methods/twitter4j.conf.ConfigurationBuilder/setOAuthConsumerKey
	public static Twitter authentication() {
		ConfigurationBuilder confBuild = new ConfigurationBuilder();
		confBuild.setDebugEnabled(true);
		confBuild.setJSONStoreEnabled(true);
		confBuild.setOAuthConsumerKey("P9xd2SeGKmM75MPcu1e9h5Lhw");
		confBuild.setOAuthConsumerSecret("xEvpgofniw1O8wwtsmz0hxmhVCZz8xv8ifmPxQhfdE77lHSHBt");
		confBuild.setOAuthAccessToken("1499412296568877060-xrfuEHFBue9CDmRFz1zMeqrKvVPiWz");
		confBuild.setOAuthAccessTokenSecret("UPv1OsBFCIuaGOuL8Eo6qfaU3TuRNjIPToJ6kK3k8p2gu");
		return new TwitterFactory(confBuild.build()).getInstance();
	}

}
