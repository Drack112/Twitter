package com.gmail.drack.repository;

import java.util.List;

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


    @Query(value = """
            SELECT users.id as id, users.full_name as fullName, users.username as username, users.about as about,
            users.private_profile as privateProfile, users.avatar as avatar
            FROM users
            WHERE users.id IN (
               SELECT user_subscriptions.subscriber_id FROM users
               JOIN user_subscriptions ON users.id = user_subscriptions.user_id
               WHERE users.id = ?1)
            INTERSECT
            SELECT users.id as id, users.full_name as fullName, users.username as username, users.about as about,
            users.private_profile as isPrivateProfile, users.avatar as avatar
            FROM users
            WHERE users.id IN (
            SELECT user_subscriptions.subscriber_id FROM users
            JOIN user_subscriptions ON users.id = user_subscriptions.user_id
            WHERE users.id = ?2)
            """, nativeQuery = true)
    <T> List<T> getSameFollowers(@Param("userId") Long userId, @Param("authUserId") Long authUserId, Class<T> type);
}
