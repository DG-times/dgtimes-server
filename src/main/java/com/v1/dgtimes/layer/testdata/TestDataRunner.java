package com.v1.dgtimes.layer.testdata;

/*
설명 : 더미데이터 생성 코드 구현

작성일 : 2022.08.11

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.layer.model.BlackKeyword;
import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.KeywordMapping;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.request.NewsRequestDto;
import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.KeywordMappingRepository;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {
    private final BlackKeywordRepository blackKeywordRepository;
    private final NewsRepository newsRepository;
    private final KeywordRepository keywordRepository;
    private final KeywordMappingRepository keywordMappingRepository;


    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//        createNewsData("코딩교육 팀스파르타, 상반기 매출 105억…’최대 실적 달성"
//                ,"코딩 교육 스타트업 팀스파르타가 올해 상반기 매출 105억원, 영업이익 31억원을 기록하며 최대 실적을 달성했다고 13일 밝혔다."
//                ,"https://news.mt.co.kr/mtview.php?no=2022071316585964426"
//                ,"https://thumb.mt.co.kr/06/2022/07/2022071316585964426_1.jpg/dims/optimize"
//        );
//        createKeywordData("코딩교육");
        createKeywordNewsData("코딩교육 팀스파르타, 상반기 매출 105억…’최대 실적 달성"
                ,"코딩 교육 스타트업 팀스파르타가 올해 상반기 매출 105억원, 영업이익 31억원을 기록하며 최대 실적을 달성했다고 13일 밝혔다."
                ,"https://news.mt.co.kr/mtview.php?no=2022071316585964426"
                ,"https://thumb.mt.co.kr/06/2022/07/2022071316585964426_1.jpg/dims/optimize",
                "코딩교육");
        createBlackKeywordData("야한단어");
    }

    private void createBlackKeywordData(String blackKeyword) throws IOException {
        // 블랙키워드 등록
        BlackKeyword black_keyword = new BlackKeyword(blackKeyword);
        blackKeywordRepository.save(black_keyword);
    }

    private Keyword createKeywordData(String inputKeyword) throws IOException {
        // 키워드 등록
        Keyword keyword = new Keyword(inputKeyword);
        keywordRepository.save(keyword);
        return keyword;
    }

    private News createNewsData(String title, String content, String mainUrl, String thumbnailUrl) throws IOException {
        // 뉴스 기사 등록
        NewsRequestDto newsRequestDto = new NewsRequestDto(title,content,mainUrl,thumbnailUrl);
        News news = new News(newsRequestDto);
        newsRepository.save(news);
        return news;
    }

    // Keyword_ID, News_ID 동시 등록을 위한 코드
    private void createKeywordNewsData(String title, String content, String mainUrl, String thumbnailUrl, String keyword) throws IOException {
        Keyword keywordEntity = createKeywordData(keyword);
        News newsEntity = createNewsData(title, content, mainUrl, thumbnailUrl);
        KeywordMapping keywordMapping = new KeywordMapping();
        keywordMapping.updateKeywordNews(keywordEntity,newsEntity);
        keywordMappingRepository.save(keywordMapping);
    }
}
