package com.v1.dgtimes.integration.mockobject.mockService;

/*
설명 : MockSearchService 테스트 코드 구현
   >

작성일 : 2022.08.09

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.integration.DefaultIntegrationTest;
import com.v1.dgtimes.integration.mockobject.mockModel.MockKeywordModel;
import com.v1.dgtimes.integration.mockobject.mockRepository.MockKeywordRepository;

import java.util.Optional;

public class MockSearchService extends DefaultIntegrationTest{

//    private final BlackKeywordRepository blackKeywordRepository;
//    private final NewsRepository newsRepository;
    private final MockKeywordRepository mockKeywordRepository;
    public static final String Keyword = "코딩교육";

    public MockSearchService(MockKeywordRepository mockKeywordRepository) {
        this.mockKeywordRepository = mockKeywordRepository;
    }


    public MockKeywordModel createKeyword(KeywordRequestDto keywordRequestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        MockKeywordModel mockKeywordModel = new MockKeywordModel(keywordRequestDto);
        return mockKeywordRepository.save(mockKeywordModel);
//        return mockKeywordModel;
    }

    public Optional<MockKeywordModel> searchKeyword(KeywordRequestDto keywordRequestDto) {
        String keyword = keywordRequestDto.getKeyword();
        return mockKeywordRepository.findByKeyword(keyword);
    }

    // 키워드 검색
//    public List<SearchResponseDto> getSeartchKeyword(KeywordRequestDto keywordRequestDto) {
//        String keyword = keywordRequestDto.getKeyword();
//        if("".equals(keyword) || keyword == null) {
//            throw new RuntimeException("키워드를 입력해주세요.");
//        }
//        searchBlackKeyword(keyword);
//        return searchKeywordMapping(searchKeyword(keyword).getId());
//    }
//
//    // 서브 메소드
//    // Black_Keyword 테이블에서 금기어가 있는지만 확인 (반환값 필요없음)
//    // findByBlackKeyword로 했을경우, value값이 null로 반환되어, cannot invoke 에러가 계속 발생하여, count로 일단 변경
//    private void searchBlackKeyword(String keyword) {
//        if(blackKeywordRepository.countByBlackKeyword(keyword) != 0)
//            throw new RuntimeException("검색한 키워드 금지어입니다.");
//    }
//
//    // Keyword 테이블에서 Keyword값 찾아 결과 반환
//    private com.v1.dgtimes.layer.model.Keyword searchKeyword(String keyword) {
//        return keywordRepository.findByKeyword(keyword).orElseThrow(
//                () -> new RuntimeException("찾는 키워드의 검색 결과가 없습니다.")
//        );
//    }
//
//    // KeywordId를 사용한 매핑 테이블 조회
//    private List<SearchResponseDto> searchKeywordMapping(Long keywordId) {
//        List<News> news = newsRepository.findAllId(keywordId);
//        if (news.size() > 0) {
//            return makeSearchResponseDto(news);
//        }
//        return null;
//    }
//
//    // 여러게의 뉴스 결과값 List<SearchResponseDto>에 저장 및 반환
//    private List<SearchResponseDto> makeSearchResponseDto(List<News> news) {
//        List<SearchResponseDto> searchResponseDtos = new ArrayList<>();
//        for(News one_news : news) {
//            SearchResponseDto searchResponseDto = new SearchResponseDto(one_news);
//            searchResponseDtos.add(searchResponseDto);
//        }
//        return searchResponseDtos;
//    }
}
