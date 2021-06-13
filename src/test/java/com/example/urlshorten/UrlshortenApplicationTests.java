package com.example.urlshorten;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UrlshortenApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UrlshortenApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
