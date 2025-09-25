package com.fastcampus.board_project.config;

import com.fastcampus.board_project.domain.Article;
import com.fastcampus.board_project.domain.ArticleComment;
import com.fastcampus.board_project.domain.Hashtag;
import com.fastcampus.board_project.domain.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfig {

    @Bean // Spring Data REST의 동작을 커스터마이징할 수 있는 설정 빈 등록
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        // response 시 엔티티의 id도 결과에 포함하여 response 하는 설정이다.
        return RepositoryRestConfigurer.withConfig((config, cors) ->
                config
                        .exposeIdsFor(UserAccount.class)
                        .exposeIdsFor(Article.class)
                        .exposeIdsFor(ArticleComment.class)
                        .exposeIdsFor(Hashtag.class)
        );
    }

}
