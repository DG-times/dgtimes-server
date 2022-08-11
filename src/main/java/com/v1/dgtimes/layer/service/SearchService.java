package com.v1.dgtimes.layer.service;

/*
설명 : SearchService 구현
    > 키워드 검색을 통한 뉴스 구현 완료
    > 코드 컨벤션 일부 적용 - 22.08.10

작성일 : 2022.08.09

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final BlackKeywordRepository blackKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final NewsRepository newsRepository;

    // 키워드 검색
    public List<SearchResponseDto> getSearchKeyword(KeywordRequestDto keywordRequestDto) {
        validKeyword(keywordRequestDto);
        Keyword keyword = searchKeyword(keywordRequestDto);
        return searchKeywordMapping(keyword);
    }


    // Keyword 테이블에서 Keyword값 찾아 결과 반환
    private Keyword searchKeyword(KeywordRequestDto keywordRequestDto) {
        return keywordRepository.findByKeyword(keywordRequestDto.getKeyword()).orElseThrow(
                () -> new RuntimeException("찾는 키워드의 검색 결과가 없습니다.")
        );
    }

    // KeywordId를 사용한 매핑 테이블 조회
    private List<SearchResponseDto> searchKeywordMapping(Keyword keyword) {
        List<News> news = newsRepository.findAllId(keyword.getId());
        if (news.size() > 0) {
           return makeSearchResponseDto(news);
        }
        return null;
    }
    
    // 여러 개의 뉴스 결과값 List<SearchResponseDto>에 저장 및 반환
    private List<SearchResponseDto> makeSearchResponseDto(List<News> news) {
        List<SearchResponseDto> searchResponseDtos = new ArrayList<>();
        for(News one_news : news) {
            SearchResponseDto searchResponseDto = new SearchResponseDto(one_news);
            searchResponseDtos.add(searchResponseDto);
        }
        return searchResponseDtos;
    }


    // Keyword 검증 메소드 입니다.
    private void validKeyword(KeywordRequestDto keywordRequestDto){
        if(keywordRequestDto.isNone())
            throw new RuntimeException("키워드를 입력해주세요.");

        if(blackKeywordRepository.countByBlackKeyword(keywordRequestDto.getKeyword()) != 0)
            throw new RuntimeException("검색한 키워드 금지어입니다.");
    }

}
