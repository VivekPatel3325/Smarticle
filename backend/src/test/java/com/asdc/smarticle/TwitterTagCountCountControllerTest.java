package com.asdc.smarticle;

import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.twittertagcount.TwitterTagCountController;
import com.asdc.smarticle.twittertagcount.TwitterTagCountServiceImpl;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-03-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterTagCountCountControllerTest {

    @Test
    public void contextLoads() {
    }

    TwitterTagCountController twitterTagCountController;

    @Mock
    UserServiceImpl userService;

    @Mock
    TwitterTagCountServiceImpl twitterTagCountService;

    @Test
    public void testNullFetchUserTags(){

        //Arrange
        twitterTagCountController = new TwitterTagCountController();
        HttpHeaders http = null;
        List<Map<String,Object>> responseData = new ArrayList<>();
        int expected = 0;
        int actual;

        //Act
        responseData = twitterTagCountController.fetchUserTags(http);
        actual= responseData.size();

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPositiveFetchUserTags(){

        //Arrange
        String userName = "Rushi";
        User user = new User();
        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag();
        List<Map<String,Object>> responseData = new ArrayList<>();
        Map<String,Object> data = new HashMap<>();

        //Act
        tag.setTagName("Cloud Computing");
        tags.add(tag);
        user.setTags(tags);
        data.put("tagName","Cloud Computing");
        data.put("tweetCount",100);
        responseData.add(0,data);
        Mockito.when(userService.getUserByUserName(userName)).thenReturn(user);
        Mockito.when(twitterTagCountService.getTwitterTagCount(tags)).thenReturn(responseData);

        //Assert
        Assert.assertNotNull(responseData);
    }
}
