package com.asdc.smarticle.twittertagcount;

import com.asdc.smarticle.articletag.Tag;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import twitter4j.JSONArray;

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
    public Map<String, Integer> getTwitterTagCount(Set<Tag> tags) {
        HashMap<String,Integer> tagCount = new HashMap<>();

        String bearerToken = "AAAAAAAAAAAAAAAAAAAAAGLAZwEAAAAAK%2FvG7bvpg2dQfVtmWGxTzFEv45U%3DgzwT78zN44O1cUWFgH0s8ZwoldvFr2e29OTDDjOukr9FP0idsB";

        for(Tag tag : tags){

            String tagName = tag.getTagName();

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.twitter.com/2/tweets/counts/recent?query="+tagName)
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAGLAZwEAAAAAK%2FvG7bvpg2dQfVtmWGxTzFEv45U%3DgzwT78zN44O1cUWFgH0s8ZwoldvFr2e29OTDDjOukr9FP0idsB")
                    .addHeader("Cookie", "guest_id=v1%3A164689235684418023; guest_id_ads=v1%3A164689235684418023; guest_id_marketing=v1%3A164689235684418023; personalization_id=\"v1_qjJQs1OXPN9lfhJz3yQCQA==\"")
                    .build();
            try {
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.peekBody(153600).string());
                System.out.println(jsonObject.toString());

                org.json.JSONArray jsonArray = jsonObject.getJSONArray("data");

                for(int i=0;i<jsonArray.length();i++){
                    if(i==jsonArray.length()-1){
                        org.json.JSONObject jsonObject1 = (org.json.JSONObject) jsonArray.get(i);
                        Integer tweetCount = (Integer)jsonObject1.get("tweet_count");
                        tagCount.put(tagName,tweetCount);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
       }

        Map<String, Integer> sortedMap = tagCount.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        System.out.println(tagCount.size());

        return sortedMap;
    }

    //https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
    public static TreeMap<String, Integer> sortbykey(HashMap<String,Integer> tagCount)
    {
        // TreeMap to store values of HashMap
        TreeMap<String, Integer> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(tagCount);

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<String, Integer> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        return sorted;
    }


    private static String getTweetCounts(String searchString, String bearerToken) throws IOException, URISyntaxException {

        String searchResponse = null;

        CloseableHttpClient httpClient =  HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/counts/all");
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("query", searchString));
        queryParameters.add(new BasicNameValuePair("start_time", "2021-01-01T00:00:00Z"));
        uriBuilder.addParameters(queryParameters);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpGet.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            searchResponse = EntityUtils.toString(entity, "UTF-8");
        }
        return searchResponse;
    }
}
