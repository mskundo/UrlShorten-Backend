package com.example.urlshorten.service;

import com.example.urlshorten.model.UrlDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UrlShortenServiceTest {

  @InjectMocks
  UrlShortenService urlShortenService;

  @Test
  public void TestGetUrl(){
    UrlDetails urlDetails = urlShortenService.getShortenUrl(getUrlDetails());
    assertNotNull(urlDetails);
  }

  private UrlDetails getUrlDetails() {
    UrlDetails urlDetails = new UrlDetails();
    urlDetails.setLongUrl("https://www.google.com/");
    return urlDetails;
  }

}
