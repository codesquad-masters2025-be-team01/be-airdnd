package com.dmz.airdnd.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmz.airdnd.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
