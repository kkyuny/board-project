package com.fastcampus.board_project.repository;

import com.fastcampus.board_project.domain.Article;
import com.fastcampus.board_project.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // Article에 대해 기본 검색기능을 제공하는 익스큐터
        QuerydslBinderCustomizer<QArticle> // 검색 기능을 커스텀하여 사용 가능
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        // default 메서드를 사용시 인터페이스에서 직접 코드 구현이 가능하다.
        bindings.excludeUnlistedProperties(true); // 기존의 객체에 모든 필드 접근 권한을 제한한다.
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }
}
