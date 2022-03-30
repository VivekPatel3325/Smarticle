package com.asdc.smarticle.twittertagcount;

import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.comutil.AppConstant;

import java.io.IOException;
import java.util.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Services for user entity.
 *
 * @author Rushi Patel
 * @version 1.0
 * @since 2022-03-22
 */
@Service
public class TwitterTagCountServiceImpl implements TwitterTagCountService{

    @Override
    public List<Map<String,Object>> getTwitterTagCount(Set<Tag> tags) {
        new HashMap<>();
        List<String> tagNameList = new ArrayList<>();
        List<Map<String,Object>> finalResponse = new ArrayList<>();
        for(Tag tag : tags){

            Map<String,Object> data = new HashMap<>();
            String tagName = tag.getTagName();
            tagNameList.add(tagName);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.twitter.com/2/tweets/counts/recent?query="+tagName)
                    .method("GET", null)
                    .addHeader(AppConstant.TWITTER_AUTHORIZATION_STRING, AppConstant.TWITTER_BEARER_TOKEN)
                    .addHeader("Cookie", "guest_id=v1%3A164689235684418023; guest_id_ads=v1%3A164689235684418023; guest_id_marketing=v1%3A164689235684418023; personalization_id=\"v1_qjJQs1OXPN9lfhJz3yQCQA==\"")
                    .build();
            try {
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.peekBody(AppConstant.MAX_VALUE).string());
                System.out.println(jsonObject.toString());

                org.json.JSONArray jsonArray = jsonObject.getJSONArray("data");

                for(int i=0;i<jsonArray.length();i++){
                    if(i==jsonArray.length()-1){
                        org.json.JSONObject jsonObject1 = (org.json.JSONObject) jsonArray.get(i);
                        Integer tweetCount = (Integer)jsonObject1.get("tweet_count");
                        data.put("tagName",tagName);
                        data.put("tweetCount",tweetCount);
                        finalResponse.add(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
       }

        int n = finalResponse.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < (n - i-1); j++) {
                if ((int) finalResponse.get(j).get("tweetCount") < (int) finalResponse.get(j+1).get("tweetCount")) {
                    Map<String, Object> temp = finalResponse.get(j);
                    finalResponse.set(j, finalResponse.get(j+1));
                    finalResponse.set(j+1, temp);
                }
            }
        }

        return finalResponse;
    }
}
