package com.example.urlshorten.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlDetails {

  private String longUrl;
  private String shortUrl;
  private Map<String, Integer> shortUrlList = new HashMap<>();

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public Map<String, Integer> getShortUrlList() {
    return shortUrlList;
  }

  public void setShortUrlList(Map<String, Integer> shortUrlList) {
    this.shortUrlList = shortUrlList;
  }
}
