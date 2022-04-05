package com.asdc.smarticle;

import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.twittertagcount.TwitterTagCountService;
import com.asdc.smarticle.twittertagcount.TwitterTagCountServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterTagServiceUnitTests {


    @Test
    public void contextLoads() {
    }

    private TwitterTagCountService twitterTagCountService;

    @Test
    public void testNullGetTwitterTagCount(){
        Set<Tag> tags = null;
        twitterTagCountService = new TwitterTagCountServiceImpl();
        //Denoting the response is empty list.
        int expectedSize = 0;
        int actualSize = twitterTagCountService.getTwitterTagCount(tags).size();
        Assert.assertEquals(expectedSize,actualSize);

    }

    @Test
    public void testPositiveGetTwitterTagCount(){
        Set<Tag> tags = new HashSet<>();
        Tag tag1 = new Tag();
        tag1.setTagName("Web");
        tag1.setId(Long.valueOf(1));
        tags.add(tag1);
        twitterTagCountService = new TwitterTagCountServiceImpl();

        //Denoting the response is not empty
        Assert.assertFalse(twitterTagCountService.getTwitterTagCount(tags).isEmpty());
    }

    @Test
    public void testGetTwitterTagCount(){
        Set<Tag> tags = new HashSet<>();

        int expectedTwitterTagCountSize = 3;

        Tag tag1 = new Tag();
        tag1.setTagName("Web");
        tag1.setId(Long.valueOf(1));
        Tag tag2 = new Tag();
        tag2.setTagName("Cloud");
        Tag tag3 = new Tag();
        tag3.setTagName("Sports");
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        twitterTagCountService = new TwitterTagCountServiceImpl();

        Assert.assertEquals(expectedTwitterTagCountSize,twitterTagCountService.getTwitterTagCount(tags).size());
    }

    @Test
    public void testNegativeGetTwitterTagCount(){
        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag();
        //tagName is not set so there is essentially no tag name here to fetch the tweet count of this tag
        tags.add(tag);
        twitterTagCountService = new TwitterTagCountServiceImpl();
        Integer expectedTweetCount = 0;
        //Since the size is empty it is clear that the actual tweet count is 0
        Integer actualTweetCount = (Integer) twitterTagCountService.getTwitterTagCount(tags).size();
        Assert.assertEquals(expectedTweetCount,actualTweetCount);
    }
}