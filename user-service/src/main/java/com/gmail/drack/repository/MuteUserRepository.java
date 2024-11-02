package com.gmail.drack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gmail.drack.model.User;

import feign.Param;

@Repository
public interface MuteUserRepository extends JpaRepository<User, Long> {
    @Query("""
            SELECT CASE WHEN count(userMuted) > 0 THEN true ELSE false END FROM User user
            LEFT JOIN user.userMutedList userMuted
            WHERE user.id = :userId
            AND userMuted.id = :mutedUserId
            """)
    boolean isUserMuted(@Param("userId") Long userId, @Param("mutedUserId") Long mutedUserId);
}
