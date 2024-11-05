package com.gmail.drack.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.drack.model.User;
import com.gmail.drack.repository.projection.AuthUserProjection;
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

    @Query("""
            SELECT user FROM User user
            WHERE user.registrationDate >= :sinceDate
            OR user.updatedAt >= :sinceDate
            """)
    List<User> findByRegistrationAndUpdatedDate(LocalDateTime sinceDate, Pageable pageable);

    @Modifying
    @Query("UPDATE User user SET user.passwordResetCode = :passwordResetCode WHERE user.id = :userId")
    void updatePasswordResetCode(@Param("passwordResetCode") String passwordResetCode, @Param("userId") Long userId);

    @Query("SELECT user.passwordResetCode FROM User user WHERE user.id = :userId")
    String getPasswordResetCode(@Param("userId") Long userId);

    @Query("SELECT CASE WHEN count(user) > 0 THEN true ELSE false END FROM User user WHERE user.id = :userId")
    boolean isUserExist(@Param("userId") Long userId);

    @Query("""
            SELECT CASE WHEN count(user) > 0 THEN true ELSE false END FROM User user
            LEFT JOIN user.following following
            WHERE user.id = :userId AND user.privateProfile = false
            OR user.id = :userId AND user.privateProfile = true AND following.id = :authUserId
            """)
    boolean isUserHavePrivateProfile(@Param("userId") Long userId, @Param("authUserId") Long authUserId);

    @Query("""
        SELECT CASE WHEN count(subscriber) > 0 THEN true ELSE false END FROM User user
        LEFT JOIN user.subscribers subscriber
        WHERE user.id = :userId
        AND subscriber.id = :subscriberUserId
        """)
    boolean isMyProfileSubscribed(@Param("userId") Long userId, @Param("subscriberUserId") Long subscriberUserId);

    @Query("SELECT user FROM User user WHERE user.passwordResetCode = :code")
    Optional<AuthUserProjection> getByPasswordResetCode(@Param("code") String code);
    
    @Modifying
    @Query("UPDATE User user SET user.password = :password WHERE user.id = :userId")
    void updatePassword(@Param("password") String password, @Param("userId") Long userId);

    @Query("SELECT user.password FROM User user WHERE user.id = :userId")
    String getUserPasswordById(@Param("userId") Long userId);
}   
