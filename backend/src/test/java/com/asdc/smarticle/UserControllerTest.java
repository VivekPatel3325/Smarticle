package com.asdc.smarticle;

import com.asdc.smarticle.twittertagcount.TwitterTagCountController;
import com.asdc.smarticle.user.UserController;
import com.asdc.smarticle.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-02-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    UserController userController = new UserController();

    @Mock
    private UserService userService;// system under test

    @Test
    public void testNullGetUserDetailsPostedArticle(){

        //Arrange
        HttpHeaders http = null;
        List<Map<String, Object>> responseData = new ArrayList<>();
        int expected = 0;
        int actual;

        //Act
        responseData = userController.getUserDetailsPostedArticle(http);
        actual= responseData.size();

        //Assert
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void testPositiveGetUserDetailsPostedArticle(){

        //Arrange
        List<Map<String, Object>> userDetailsOfPostedArticle = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();

        //Act
        data.put("firstName","Rushi");
        data.put("lastName","Patel");
        data.put("userName","Rushi95");
        data.put("id",1);
        userDetailsOfPostedArticle.add(data);
        Mockito.when(userService.getUsersPostedArticle()).thenReturn(userDetailsOfPostedArticle);

        //Assert
        Assert.assertNotNull(userDetailsOfPostedArticle);
    }

}
