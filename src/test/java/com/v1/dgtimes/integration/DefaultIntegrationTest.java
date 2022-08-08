package com.v1.dgtimes.integration;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DefaultIntegrationTest {

    @Autowired
    TestRestTemplate testTemplate;

    @Getter
    @Builder
    static class SearchResponseDto {
        private int status;
        private String title;
        private String content;
        private String thumbnail;
        private String main_url;
    }

    @Getter
    @Builder
    static class DefaultResponseDto {
        private String mag;
        private int status;
    }

    @Getter
    @Builder
    static class BookmarkRequestDto{
        private String keyword;
    }
}
