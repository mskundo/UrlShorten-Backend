package com.example.urlshorten.service;

import com.example.urlshorten.model.UrlDetails;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UrlShortenService {

  private Map<String, UrlDetails> urlList = new HashMap<>();
  Map<String, Integer> shortUrlList = new HashMap<>();

  public UrlDetails getShortenUrl(UrlDetails urlDetails) {
    String randomStr = getRandomString();
    urlDetails.setShortUrl("http://localhost:8080/"+randomStr);
    urlList.put(randomStr, urlDetails);
    Map<String, Integer> shortUrlList = getShortUrlList(urlList, urlDetails);
    urlDetails.setShortUrlList(shortUrlList);
    return urlDetails;
  }

  private Map<String, Integer> getShortUrlList(Map<String, UrlDetails> urlList, UrlDetails urlDetails) {
    Iterator<Map.Entry<String, UrlDetails>> iterator = urlList.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String , UrlDetails> it = iterator.next();
      if (urlDetails.getLongUrl().equalsIgnoreCase(String.valueOf(it.getValue().getLongUrl()))){
        shortUrlList.put(urlDetails.getShortUrl(),it.getValue().getShortUrlList().get(urlDetails.getShortUrl()));
      }
    }
    return shortUrlList;
  }

  private String getRandomString() {
    String heartBeatId = UUID.randomUUID().toString();
    String randonStr = "";
    for(int i=0;i<10;i++){
      randonStr += heartBeatId.charAt(i);
    }
    return  randonStr;
  }

  public String getLongUrl(String shortUrl) {
    int count = 0;
    String shortUrls = urlList.get(shortUrl).getShortUrl();
    Map<String, Integer> shortUrlLists = urlList.get(shortUrl).getShortUrlList();
    shortUrlLists.put(shortUrls, count++);
    String longUrl = urlList.get(shortUrl).getLongUrl();
    return longUrl;
  }
}
