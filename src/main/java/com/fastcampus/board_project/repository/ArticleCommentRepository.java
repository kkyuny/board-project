package com.fastcampus.board_project.repository;

import com.fastcampus.board_project.domain.ArticleComment;
import com.fastcampus.board_project.domain.QArticleComment;
import com.fastcampus.board_project.domain.projection.ArticleCommentProjection;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = ArticleCommentProjection.class)
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>, // Article에 대해 기본 검색기능(Predicate = where)을 제공하는 익스큐터
        QuerydslBinderCustomizer<QArticleComment> // 검색 기능을 커스텀하여 사용 가능
{
    List<ArticleComment> findByArticle_Id(Long articleId);
    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        // default 메서드를 사용시 인터페이스에서 직접 코드 구현이 가능하다.
        // jpa에서 제공하는 검색 기능의 옵션을 커스텀하여 사용할 수 있다.
        bindings.excludeUnlistedProperties(true); // 기존의 객체에 모든 필드 접근 권한을 제한한다.(명시적으로 지정한 필드만 검색 가능함.)
        bindings.including(root.content, root.createdAt, root.createdBy); // 검색허용 필드 설정
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like + 대소문자 구분 없이 검색(완전 동일 + 대소문자 구분 없을 땐 equalsIgnoreCase)
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like + 대소문자 구분 없이 검색
        // TODO: 시분초까지 모두 맞아야 검색이 가능해서 날짜까지 맞으면 검색되도록 기능 수정 예정
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 완전히 동일한 날짜일 때만 매칭
    }

}
