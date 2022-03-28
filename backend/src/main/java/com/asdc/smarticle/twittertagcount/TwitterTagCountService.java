package com.asdc.smarticle.twittertagcount;

import com.asdc.smarticle.articletag.Tag;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.util.*;

public interface TwitterTagCountService {
    Map<String, Integer> getTwitterTagCount(Set<Tag> tags);
}
