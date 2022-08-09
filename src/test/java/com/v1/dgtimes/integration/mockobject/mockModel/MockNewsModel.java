package com.v1.dgtimes.integration.mockobject.mockModel;

/*
설명 : MockNewsModel 테스트 코드 구현
   >

작성일 : 2022.08.10

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.layer.model.KeywordMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MockNewsModel {
    private Long id;
    private String title;
    private String content;
    private String main_url;
    private String thumbnail_url;
    private Date date;

    private List<MockKeywordMappingModel> mockKeywordMappingModels = new ArrayList<>();

    // Keyword_mapping 연관관계 생성
    public void addKeywordMapping(MockKeywordMappingModel mockKeywordMappingModel) {
        this.mockKeywordMappingModels.add(mockKeywordMappingModel);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(mockKeywordMappingModel.getMockNewsModel()!=this)
            mockKeywordMappingModel.updateNews(this);
    }
}
