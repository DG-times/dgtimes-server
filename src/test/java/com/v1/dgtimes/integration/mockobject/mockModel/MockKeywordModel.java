package com.v1.dgtimes.integration.mockobject.mockModel;

/*
설명 : MockKeywordModel 테스트 코드 구현
   >

작성일 : 2022.08.09

마지막 수정한 사람 : 홍우석

*/

import com.v1.dgtimes.integration.DefaultIntegrationTest;
import com.v1.dgtimes.layer.model.Bookmark;
import com.v1.dgtimes.layer.model.KeywordMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class MockKeywordModel  extends DefaultIntegrationTest {
        private Long id;
        private String keyword;

        private List<MockKeywordMappingModel> mockKeywordMappingModels = new ArrayList<>();
//        private List<Bookmark> bookmarks = new ArrayList<>();

        public MockKeywordModel(KeywordRequestDto keywordRequestDto) {
                this.keyword = keywordRequestDto.getKeyword();
        }
//
        // Keyword_mapping 연관관계 생성
        public void addKeywordMapping(MockKeywordMappingModel mockKeywordMappingModel) {
                this.mockKeywordMappingModels.add(mockKeywordMappingModel);
                // 무한 후프에 빠지지 않기 위해서 작성
                if(mockKeywordMappingModel.getMockKeywordModel()!=this)
                        mockKeywordMappingModel.updateKeyword(this);
        }
//
//        // Bookmark 연관관계 생성
//        public void addBookmark(Bookmark bookmark) {
//                this.bookmarks.add(bookmark);
//                // 무한 후프에 빠지지 않기 위해서 작성
//                if(bookmark.getKeyword()!=this)
//                        bookmark.updateKeyword(this);
//        }
}
