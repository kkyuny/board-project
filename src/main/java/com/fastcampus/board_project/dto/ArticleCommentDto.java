package com.fastcampus.board_project.dto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        Long parentCommentId,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleCommentDto of(Long id,
                             Long articleId,
                             Long parentCommentId,
                             String content,
                             LocalDateTime createdAt,
                             String createdBy,
                             LocalDateTime modifiedAt,
                             String modifiedBy) {
        return new ArticleCommentDto(id, articleId, parentCommentId, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }
}
