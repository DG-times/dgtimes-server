package com.v1.dgtimes.integration.mockobject.mockRepository;

/*
설명 : MockKeywordRepository 테스트 코드 구현
   >

작성일 : 2022.08.09

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.integration.DefaultIntegrationTest;
import com.v1.dgtimes.integration.mockobject.mockModel.MockKeywordModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockKeywordRepository extends DefaultIntegrationTest {
    // 기존 엔티티에 setter를 사용하지 않으므로, Model 별도로 생성
    private List<MockKeywordModel> mockKeywordModels = new ArrayList<>();
    private Long KeywordId = 1L;

    // 키워드 저장
    public MockKeywordModel save(MockKeywordModel mockKeywordModel) {
        // 업데이트가 필요할 경우에 사용, 지금은 업데이트가 필요없으므로 일단 주석처리
//        for (MockKeywordModel existKeyword : keywords) {
//            if (existKeyword.getId().equals(keyword.getId())) {
//                String myKeyword = keyword.getKeyword();
//                existKeyword.setKeyword(myKeyword);
//                return existKeyword;
//            }
//        }
        mockKeywordModel.setId(KeywordId);
        ++KeywordId;
        mockKeywordModels.add(mockKeywordModel);
        return mockKeywordModel;
    }

    // 키워드 조회
    public Optional<MockKeywordModel> findByKeyword(String keyword) {
        for (MockKeywordModel mockKeywordModel : mockKeywordModels) {
            if(mockKeywordModel.getKeyword().equals(keyword)) {
                return Optional.of(mockKeywordModel);
            }
        }
        return Optional.empty();
    }
}
