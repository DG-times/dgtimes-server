package com.v1.dgtimes.integration.mockobject.mockRepository;

/*
설명 : MockNewsRepository 테스트 코드 구현
   >

작성일 : 2022.08.09

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.integration.mockobject.mockModel.MockKeywordModel;
import com.v1.dgtimes.integration.mockobject.mockModel.MockNewsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockNewsRepository {
    // 기존 엔티티에 setter를 사용하지 않으므로, Model 별도로 생성
    private List<MockNewsModel> mockNewsModels = new ArrayList<>();
    private Long NewsId = 1L;

    // 뉴스 저장
    public MockNewsModel save(MockNewsModel mockNewsModel) {
        mockNewsModel.setId(NewsId);
        ++NewsId;
        mockNewsModels.add(mockNewsModel);
        return mockNewsModel;
    }

    // 뉴스 조회
    // Optional 필요없이 사용해도 되지 않는가?
    // findAllId로 해당 뉴스를 다 조회한다면 List로 반환해야될거 같음..
    public Optional<MockNewsModel> findAllId(Long id) {
        for (MockNewsModel mockNewsModel : mockNewsModels) {
            if(mockNewsModel.getId().equals(id)) {
                return Optional.of(mockNewsModel);
            }
        }
        return Optional.empty();
    }
}
