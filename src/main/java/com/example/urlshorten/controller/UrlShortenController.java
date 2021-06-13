package com.example.urlshorten.controller;

import com.example.urlshorten.model.UrlDetails;
import com.example.urlshorten.service.UrlShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin("*")
@RestController
public class UrlShortenController {

  @Autowired
  UrlShortenService urlShortenService;

  @PostMapping("/shortUrl")
  public ResponseEntity getShortenUrl(@RequestBody UrlDetails urlDetails) {
    UrlDetails response = urlShortenService.getShortenUrl(urlDetails);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{shortUrl}")
  public void getLongUrl (HttpServletResponse httpResponse, @PathVariable ("shortUrl") String shortUrl) throws IOException {
    String longUrl = urlShortenService.getLongUrl(shortUrl);
    httpResponse.sendRedirect(longUrl);
  }
}
