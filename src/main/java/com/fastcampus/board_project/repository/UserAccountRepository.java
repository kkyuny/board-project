package com.fastcampus.board_project.repository;

import com.fastcampus.board_project.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
