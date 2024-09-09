package com.desafio.uol3.repository;

import com.desafio.uol3.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
