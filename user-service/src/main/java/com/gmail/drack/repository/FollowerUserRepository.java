package com.gmail.drack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.drack.model.User;

@Repository
public interface FollowerUserRepository extends JpaRepository<User, Long> {
     @Query("""
            SELECT CASE WHEN count(follower) > 0 THEN true ELSE false END
            FROM User user
            LEFT JOIN user.followers follower
            WHERE user.id = :authUserId
            AND follower.id = :userId
            """)
    boolean isUserFollowByOtherUser(@Param("authUserId") Long authUserId, @Param("userId") Long userId);
}
