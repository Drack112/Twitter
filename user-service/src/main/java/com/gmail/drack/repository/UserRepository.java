package com.gmail.drack.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.drack.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.email = :email")
    <T> Optional<T> getUserByEmail(@Param("email") String email, Class<T> type);

    @Query("SELECT user FROM User user WHERE user.id =:userId")
    <T> Optional<T> getUserById(@Param("userId") Long userId, Class<T> type);

    @Query("""
            SELECT user FROM User user
            WHERE UPPER(user.fullName) LIKE UPPER(CONCAT('%',:username,'%')) AND user.active = true
            OR UPPER(user.username) LIKE UPPER(CONCAT('%',:username,'%')) AND user.active = true
            """)
    <T> Page<T> searchUsersByUsername(@Param("username") String name, Pageable pageable,
            Class<T> type);
}
