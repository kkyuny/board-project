package com.fastcampus.board_project.service;

import com.fastcampus.board_project.domain.constant.SearchType;
import com.fastcampus.board_project.dto.ArticleDto;
import com.fastcampus.board_project.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList(){
        // Given

        // When
        List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle(){
        // Given

        // When
        //ArticleDto article = sut.searchArticles(1L);

        // Then
        //assertThat(article).isNotNull();
    }

    @DisplayName("[view][POST] 새 게시글 등록 ")
    @Test
    void givenNewArticleInfo_whenRequesting_thenSavesNewArticle() throws Exception {
        // Given

        // When & Then
    }

    @DisplayName("[view][POST] 게시글 수정")
    @Test
    void givenUpdatedArticleInfo_whenRequesting_thenUpdatesNewArticle() throws Exception {
        // Given
        long articleId = 1L;
    }

    @DisplayName("[view][POST] 게시글 삭제 - 정상 호출")
    @Test
    void givenArticleIdToDelete_whenRequesting_thenDeletesArticle() throws Exception {
        // Given
        long articleId = 1L;
        // When & Then
    }
}