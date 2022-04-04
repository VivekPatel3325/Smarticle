package com.asdc.smarticle.article;

import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.articletag.TagRepository;
import com.asdc.smarticle.comutil.ApiError;
import com.asdc.smarticle.comutil.AppConstant;
import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import com.asdc.smarticle.user.UserService;
import com.asdc.smarticle.user.exception.ArticleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	UserService userService;
	

	@Override
	public Article saveArticle(Article article, String userName) throws ArticleException {

		User user = userRepository.findByUserName(userName);
		article.setUserId(user);

		if (isContentEmpty(article) || isHeadingEmpty(article)) {
			throw new ArticleException(ApiError.ARTICLE_FIELD_NOT_NULL);
		}
 
		return articleRepository.save(article);
	} 
	 
	/** 
	 * @author Vivekkumar Patel 
	 * This method checks that header of the article is empty or not.
	 * @param article instance containng details such as content,heading,visibility etc.
	 * @return true if header of the article is empty else false.
	 */
	public boolean isHeadingEmpty(Article article) {
		return article.getContent() == null ||article.getContent().isEmpty() ;
	} 

	/**
	 * @author Vivekkumar Patel 
	 * This method checks that content of the article is empty or not.
	 * @param article instance containng details such as content,heading,visibility etc.
	 * @return true if content of the article is empty else false.
	 */

	public boolean isContentEmpty(Article article) {
		return article.getHeading() == null || article.getHeading().isEmpty();
	}

	/**
	 * @author Vivekkumar Patel 
	 * This method retrieve list of articles filtered by visibility,user,tags and sort by date or number of likes 
	 * @param visibility Is article visible before login or not.If visibility=1 article is visible before login else not.If visibility=ALL
	 * then data is retrieved isrespective of visibility.
	 * @param filterPojo Instance of FilterPojo containing pagination and metadata to apply filter.
	 * @return List of article after applying sorting and filter with pagination.
	 */
	@Override
	public Page<Article> getArticle(String visibility,FilterPojo filterPojo) throws ArticleException {
		Page<Article> articleList = null;
		Pageable pagination = PageRequest.of(filterPojo.getPage(), filterPojo.getTotalPage(),Sort.by(filterPojo.getSortBy()));
		
		try {

			if (visibility.equalsIgnoreCase(ApplicationUrlPath.ALL_ARTICLE)) {
				articleList=getDataWithOutVisibility(filterPojo,pagination);
				
				
			} else {
				articleList=getDataWithVisibility(filterPojo,visibility,pagination);
			}

		} catch (Exception e) {
			throw new ArticleException(ApiError.ARTICLE_NOT_PRESENT);
		}
		return articleList;
	}

	/**
	 * @author Vivekkumar Patel 
	 * This method retrieve list of articles filtered by visibility,user,tags and sort by date or number of likes 
	 * @param visibility Is article visible before login or not.If visibility=1 article is visible before login else not.
	 * @param filterPojo Instance of FilterPojo containing pagination and metadata to apply filter.
	 * @param pagination Instance of Page containing pagination details details 
	 * @return List of article after applying sorting and filter with pagination.
	 */
	private Page<Article> getDataWithVisibility(FilterPojo filterPojo, String visibility, Pageable pagination) {
		// TODO Auto-generated method stub
		Page<Article> articleList = null;

		boolean isVisibility = visibility.equals("1") ? true : false;

		// articleList = articleRepository.findByVisibility(visibility.equals("1") ?
		// true : false);

		if (isUserListEmpty(filterPojo) && isTagListEmpty(filterPojo)) {
			// Get the data if userlist and tag list is empty
			articleList = articleRepository.findAllByVisibility(isVisibility, pagination);
		} else if (isTagListEmpty(filterPojo) && !isUserListEmpty(filterPojo)) {

			// Get the data if user list is not empty but tag list is empty
			List<User> userList = userService.getUserList(filterPojo.getUserIdList());
			articleList = articleRepository.findAllByUserIdInAndVisibility(userList, isVisibility, pagination);

		} else if (!isTagListEmpty(filterPojo) && isUserListEmpty(filterPojo)) {

			// Get the data if tag list is not empty but user list is empty
			articleList = articleRepository.findAllByTagIdInAndVisibility(filterPojo.getTagList(), isVisibility,
					pagination);

		} else {

			// get the data if tag list and user list is not empty
			List<User> userList = userService.getUserList(filterPojo.getUserIdList());
			articleList = articleRepository.findAllByUserIdInAndTagIdInAndVisibility(userList, filterPojo.getTagList(),
					isVisibility, pagination);
		}

		return articleList;
	}
	
	
	/**
	 * @author Vivekkumar Patel 
	 * This method checks that list of taglist is empty or not in instance of  FilterPojo.
	 * @param filterPojo Instance of FilterPojo containing pagination and metadata to apply filter.
	 * @return true if list of taglist  is empty else false.
	 */
	private boolean isTagListEmpty(FilterPojo filterPojo) {
		return filterPojo.getTagList()!=null  && filterPojo.getTagList().size()==0;
	}

	/**
	 * @author Vivekkumar Patel 
	 * This method checks that list of userid is empty or not in instance of  FilterPojo.
	 * @param filterPojo Instance of FilterPojo containing pagination and metadata to apply filter.
	 * @return true if list of userid  is empty else false.
	 */
	private boolean isUserListEmpty(FilterPojo filterPojo) {
		return filterPojo.getUserIdList() !=null  && filterPojo.getUserIdList().size()==0;
	}

	 
	/**
	 * @author Vivekkumar Patel 
	 * This method retrieve list of articles filtered by user,tags and sort by date or number of likes 
	 * @param filterPojo Instance of FilterPojo containing pagination and metadata to apply filter.
	 * @param pagination Instance of Page containing pagination details details 
	 * @return List of article after applying sorting and filter with pagination.
	 */
	private Page<Article> getDataWithOutVisibility(FilterPojo filterPojo, Pageable pagination) {
		Page<Article> articleList = null;
		if (isUserListEmpty(filterPojo) && isTagListEmpty(filterPojo)) {
			// Get the data if userlist and tag list is empty
			articleList = articleRepository.findAll(pagination);
		} else if (isTagListEmpty(filterPojo) && !isUserListEmpty(filterPojo)) {

			// Get the data if user list is not empty but tag list is empty
			List<User> userList = userService.getUserList(filterPojo.getUserIdList());
			articleList = articleRepository.findAllByUserIdIn(userList, pagination);

		} else if (!isTagListEmpty(filterPojo) && isUserListEmpty(filterPojo)) {

			// Get the data if tag list is not empty but user list is empty
			articleList = articleRepository.findAllByTagIdIn(filterPojo.getTagList(), pagination);

		} else {

			// get the data if tag list and user list is not empty
			List<User> userList = userService.getUserList(filterPojo.getUserIdList());
			articleList = articleRepository.findAllByUserIdInAndTagIdIn(userList, filterPojo.getTagList(), pagination);
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
	public List<Map<String,Object>> getTwitterCountOfArticleTags(Long id){
		Article article = getArticleById(id);
		Set<Tag> tags = article.getTagId();
		if(tags.size()==0){
			return new ArrayList<Map<String,Object>>();
		}
		List<String> tagNames = new ArrayList<>();
		Map<String,String> responseTweetTextAndURL = new HashMap<>();
		List<Map<String,Object>> responseTweetData = new ArrayList<>();
		String query = "lang:en (";
		for(Tag tag : tags){
			query += tag.getTagName() +" OR ";
			tagNames.add(tag.getTagName());
		}

		String searchQuery = query.substring(0, query.length()-4);
		searchQuery += ")";
		Twitter twitter = authentication();

		Query search = new Query(searchQuery);
		search.count(AppConstant.MAX_TWEET);
		QueryResult tweetData;
		try {
			tweetData = twitter.search(search);

			for (Status tweet : tweetData.getTweets()) {
				//List<Object> tweetDataList = new ArrayList<>();
				Map<String,Object> tweetDataMap = new HashMap();
				String tweetLink = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
				String removeURL = tweet.getText().replaceAll("((https?|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", "");
				String transformedTweetText = removeURL.replaceAll("[^\\w\\s]", "");
				String authorName = tweet.getUser().getName();
				Date creationDate = tweet.getUser().getCreatedAt();
				int retweetCount = tweet.getRetweetCount();
				String userImage = tweet.getUser().getProfileImageURL();
				//System.out.println(" "+tweetLink+" "+authorName+" "+creationDate+" "+retweetCount+" "+userImage);
				tweetDataMap.put("userImageURL",userImage);
				tweetDataMap.put("authorName",authorName);
				tweetDataMap.put("tweetLink",tweetLink);
				tweetDataMap.put("tweetText",transformedTweetText);
				tweetDataMap.put("creationDate",creationDate);
				tweetDataMap.put("retweetCount",retweetCount);
				responseTweetData.add(tweetDataMap);
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return responseTweetData;
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

	@Override
	public void setLike(Article article, String userName) {
		User user = userRepository.findByUserName(userName);
		Article article1 = articleRepository.getById(article.getId());
		if(article1.getLike().contains(user)) {
			article1.getLike().remove(user);
		}else {
			article1.getLike().add(user);
		}
		//article1.getLike().add(user);
		article1.setLike(article1.getLike());
		articleRepository.save(article1);
	}  
} 
