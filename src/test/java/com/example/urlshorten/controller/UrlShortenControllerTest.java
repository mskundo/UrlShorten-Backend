package com.example.urlshorten.controller;

import com.example.urlshorten.model.UrlDetails;
import com.example.urlshorten.service.UrlShortenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UrlShortenControllerTest {

  @InjectMocks
  UrlShortenController urlShortenController;

  @Mock
  UrlShortenService urlShortenService;

  @Autowired
  private MockMvc mvc;

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Mock
  private HttpServletResponse httpResponse;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void performCreditAssessment_with_CustomerType_test() {
    UrlDetails request = getUrlDetails();
    ResponseEntity result = urlShortenController.getShortenUrl(request);
    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

    assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
  }

  @Test
  public void test_cderesponsesubset_post_success() throws Exception {
    UrlDetails subsetResponse = createMockUrlDetailRequest();
    mvc.perform(post("/shortUrl").content(this.json(subsetResponse))
        .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
  }

  @Test
  public void whenValidResponseCodePathVariable_thenSubsetMappingShouldBeDeletedWithStatusOk() throws Exception {
    urlShortenController.getLongUrl(httpResponse,"test");
    when(urlShortenService.getLongUrl("test")).thenReturn("test");
    Mockito.verify(urlShortenService, Mockito.times(1)).getLongUrl(any(String.class));

  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }

  private UrlDetails getUrlDetails() {
    UrlDetails urlDetails = new UrlDetails();
    urlDetails.setLongUrl("https://www.google.com/");
    return urlDetails;
  }

  private UrlDetails createMockUrlDetailRequest() {
    UrlDetails urlDetails = new UrlDetails();
    urlDetails.setLongUrl("https://www.google.com/");
    urlDetails.setShortUrl("http://localhost:8080/ytump");
    return urlDetails;
  }
}
