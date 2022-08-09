package com.v1.dgtimes.layer.service;

/*
설명 : SearchService 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.News;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.KeywordMappingRepository;
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
    private final KeywordMappingRepository keywordMappingRepository;

    // 키워드 검색
    public List<SearchResponseDto> getSeartchKeyword(KeywordRequestDto keywordRequestDto) {
        String keyword = keywordRequestDto.getKeyword();
        if("".equals(keyword) || keyword == null) {
            throw new RuntimeException("키워드를 입력해주세요.");
        }
        searchBlackKeyword(keyword);
        return searchKeywordMapping(searchKeyword(keyword).getId());
    }
    
    // 서브 메소드
    // Black_Keyword 테이블에서 금기어가 있는지만 확인 (반환값 필요없음)
    // findByBlackKeyword로 했을경우, value값이 null로 반환되어, cannot invoke 에러가 계속 발생하여, count로 일단 변경
    public void searchBlackKeyword(String keyword) {
        if(blackKeywordRepository.countByBlackKeyword(keyword) != 0)
            throw new RuntimeException("검색한 키워드 금지어입니다.");
    }

    // Keyword 테이블에서 Keyword값 찾아 결과 반환
    public Keyword searchKeyword(String keyword) {
        return keywordRepository.findByKeyword(keyword).orElseThrow(
                () -> new RuntimeException("찾는 키워드의 검색 결과가 없습니다.")
        );
    }

    // KeywordId를 사용한 매핑 테이블 조회
    public List<SearchResponseDto> searchKeywordMapping(Long keywordId) {
        List<SearchResponseDto> searchResponseDtos = new ArrayList<>();
        List<News> news = newsRepository.findAllId(keywordId);
        if (news.size() > 0) {
            for(News one_news : news) {
                SearchResponseDto searchResponseDto = new SearchResponseDto();
                searchResponseDto.setTitle(one_news.getTitle());
                searchResponseDto.setContent(one_news.getContent());
                searchResponseDto.setMain_url(one_news.getMain_url());
                searchResponseDto.setThumbnail(one_news.getThumbnail_url());
                searchResponseDtos.add(searchResponseDto);
            }
        }
//        List<Keyword_mapping> keyword_mappings = keywordMappingRepository.findAllByKeywordId(keywordId);
//        if(keyword_mappings.size() > 0) {
//            for(Keyword_mapping keyword_mapping : keyword_mappings) {
//                List<News> news = newsRepository.findAllById(keyword_mapping.getNews().getId());
//                if (news.size() > 0) {
//                    for(News one_news : news) {
//                        SearchResponseDto searchResponseDto = new SearchResponseDto();
//                        searchResponseDto.setTitle(one_news.getTitle());
//                        searchResponseDto.setContent(one_news.getContent());
//                        searchResponseDto.setMain_url(one_news.getMain_url());
//                        searchResponseDto.setThumbnail(one_news.getThumbnail_url());
//                        searchResponseDtos.add(searchResponseDto);
//                    }
//                }
//            }
//        }
        return searchResponseDtos;
    }
}
