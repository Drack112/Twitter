package com.gmail.drack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.drack.model.User;
import com.gmail.drack.repository.projection.NotificationUserProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.email = :email")
    <T> Optional<T> getUserByEmail(@Param("email") String email, Class<T> type);

    @Query("SELECT user FROM User user WHERE user.id = :userId")
    <T> Optional<T> getUserById(@Param("userId") Long userId, Class<T> type);

    @Query("""
        SELECT CASE WHEN count(followerRequest) > 0 THEN true ELSE false END FROM User user
        LEFT JOIN user.followerRequests followerRequest
        WHERE user.id = :userId
        AND followerRequest.id = :authUserId
        """)
    boolean isMyProfileWaitingForApprove(@Param("userId") Long userId, @Param("authUserId") Long authUserId);

    @Query("""
        SELECT user FROM User user
        LEFT JOIN user.subscribers subscriber
        WHERE subscriber.id = :userId
        """)
    List<NotificationUserProjection> getUsersWhichUserSubscribed(@Param("userId") Long userId);

    @Query("""
        SELECT user.id FROM User user
        LEFT JOIN user.subscribers subscriber
        WHERE subscriber.id = :userId
        """)
    List<Long> getUserIdsWhichUserSubscribed(@Param("userId") Long userId);
}
