package com.shahian.productreviewmanagement.repository;

import com.shahian.productreviewmanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
