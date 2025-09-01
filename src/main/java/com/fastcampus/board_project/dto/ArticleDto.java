package com.fastcampus.board_project.dto;

import com.fastcampus.board_project.domain.Article;
import com.fastcampus.board_project.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public ArticleDto {
    }
}