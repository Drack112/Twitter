package com.gmail.drack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.drack.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.email - :email")
    <T> Optional<T> getUserByEmail(@Param("email") String email, Class<T> type);
}
