package com.v1.dgtimes.integration;

import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.KeywordMappingRepository;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.NewsRepository;
import com.v1.dgtimes.layer.repository.BookmarkRepository;
import com.v1.dgtimes.layer.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DefaultIntegrationTest {
    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    BlackKeywordRepository blackKeywordRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    KeywordMappingRepository keywordMappingRepository;

    @Autowired
    TestRestTemplate testTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;


    @Getter
    @Builder
    public static class SearchResponseDto {
        private String title;
        private String content;
        private String thumbnailUrl;
        private String mainUrl;
        private Date date;
    }

    @Getter
    @Builder
    static class DefaultResponseDto {
        private String msg;
        private int status;
    }

    @Getter
    @Builder
    static class BookmarkRequestDto{
        private String keyword;
    }

    // Keyword DTO
    @Getter
    @Setter
    @Builder
    static class KeywordRequestDto{
        private String keyword;
    }


    // User DTO
    @Getter
    @Builder
    static class SignupRequestDto {
        private String id;
        private String pw;
        private String username;

    }

    @Getter
    @Builder
    static class SignupResponseDto {
        private String mag;
        private int status;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SignupResponseDto that = (SignupResponseDto) o;
            return status == that.status && Objects.equals(mag, that.mag);
        }
    }

}
