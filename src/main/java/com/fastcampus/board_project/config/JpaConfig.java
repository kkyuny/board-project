package com.fastcampus.board_project.config;

import com.fastcampus.board_project.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    /*
        @EnableJpaAuditing를 통해 엔티티의 Auditing 기능을 사용한다.
        @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy이 자동으로 동작한다.
        auditorAware()를 통해 @CreatedBy, @LastModifiedBy를 채우게 된다,
        이 때의 정보는 스프링 시큐리티를 통해 로그인한 사용자의 userName을 사용한다.
        이 때 사용자 정보는 SecurityContextHolder.SecurityContext.Authentication.Principal에 저장되어 있다.
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext()) // Spring Security 컨텍스트 꺼냄
                .map(SecurityContext::getAuthentication) // 인증 정보
                .filter(Authentication::isAuthenticated) // 인증된 사용자만
                .map(Authentication::getPrincipal)// Principal 객체
                .map(BoardPrincipal.class::cast) // BoardPrincipal로 캐스팅
                .map(BoardPrincipal::getUsername); // username 추출
    }
}
