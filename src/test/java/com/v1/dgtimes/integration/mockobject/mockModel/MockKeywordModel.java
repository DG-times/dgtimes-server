package com.v1.dgtimes.integration.mockobject.mockModel;

import com.v1.dgtimes.integration.DefaultIntegrationTest;
import com.v1.dgtimes.layer.model.Bookmark;
import com.v1.dgtimes.layer.model.KeywordMapping;
import lombok.AllArgsConstructor;
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

        private List<KeywordMapping> keyword_mappings = new ArrayList<>();
        private List<Bookmark> bookmarks = new ArrayList<>();

        public MockKeywordModel(KeywordRequestDto keywordRequestDto) {
                this.keyword = keywordRequestDto.getKeyword();
        }
//
//        // Keyword_mapping 연관관계 생성
//        public void addKeywordMapping(KeywordMapping keyword_mapping) {
//                this.keyword_mappings.add(keyword_mapping);
//                // 무한 후프에 빠지지 않기 위해서 작성
//                if(keyword_mapping.getKeyword()!=this)
//                        keyword_mapping.updateKeyword(this);
//        }
//
//        // Bookmark 연관관계 생성
//        public void addBookmark(Bookmark bookmark) {
//                this.bookmarks.add(bookmark);
//                // 무한 후프에 빠지지 않기 위해서 작성
//                if(bookmark.getKeyword()!=this)
//                        bookmark.updateKeyword(this);
//        }
}
