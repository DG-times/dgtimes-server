package com.v1.dgtimes.integration.mockobject.mockModel;

/*
설명 : MockKeywordMappingModel 테스트 코드 구현
   >

작성일 : 2022.08.10

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.News;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class MockKeywordMappingModel {
    private Long id;

    // 연관관계 생성을 어떻게 해야할지 고민중..
    private MockNewsModel mockNewsModel;
    private MockKeywordModel mockKeywordModel;

    // 연관 관계 설정 - Keyword
    public void updateKeyword(MockKeywordModel mockKeywordModel) {
        this.mockKeywordModel = mockKeywordModel;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!mockKeywordModel.getMockKeywordMappingModels().contains(this))
            mockKeywordModel.addKeywordMapping(this);
    }

    // 연관 관계 설정 - News
    public void updateNews(MockNewsModel mockNewsModel) {
        this.mockNewsModel = mockNewsModel;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!mockNewsModel.getMockKeywordMappingModels().contains(this))
            mockNewsModel.addKeywordMapping(this);
    }

    // Keyword, News 동시 등록을 위한 연관 관계 설정
    public void updateKeywordNews(MockKeywordModel mockKeywordModel, MockNewsModel mockNewsModel) {
        this.mockKeywordModel = mockKeywordModel;
        this.mockNewsModel = mockNewsModel;
        if(!mockKeywordModel.getMockKeywordMappingModels().contains(this))
            mockKeywordModel.addKeywordMapping(this);
        if(!mockNewsModel.getMockKeywordMappingModels().contains(this))
            mockNewsModel.addKeywordMapping(this);
    }
}
