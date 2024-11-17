package com.gmail.drack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import com.gmail.drack.commons.utils.TestConstants;
import com.gmail.drack.repository.projection.AuthUserProjection;
import com.gmail.drack.repository.projection.BlockedUserProjection;
import com.gmail.drack.repository.projection.NotificationUserProjection;
import com.gmail.drack.repository.projection.UserCommonProjection;

public class UserServiceTestHelper {

    private static final ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
    private static final PageRequest pageable = PageRequest.of(0, 20);

    public static Page<BlockedUserProjection> createBlockedUserProjections() {
        BlockedUserProjection blockedUserProjection1 = factory.createProjection(
                BlockedUserProjection.class,
                Map.of(
                        "id", 1L,
                        "fullName", TestConstants.FULL_NAME,
                        "username", TestConstants.USERNAME,
                        "about", TestConstants.ABOUT,
                        "avatar", TestConstants.AVATAR_SRC_1,
                        "privateProfile", false,
                        "isUserBlocked", true
                ));
        BlockedUserProjection blockedUserProjection2 = factory.createProjection(
                BlockedUserProjection.class,
                Map.of(
                        "id", 2L,
                        "fullName", TestConstants.FULL_NAME,
                        "username", TestConstants.USERNAME,
                        "about", TestConstants.ABOUT,
                        "avatar", TestConstants.AVATAR_SRC_1,
                        "privateProfile", false,
                        "isUserBlocked", true
                ));
        return new PageImpl<>(Arrays.asList(blockedUserProjection1, blockedUserProjection2), pageable, 20);
    }

    public static NotificationUserProjection createNotificationUserProjection() {
        return factory.createProjection(
                NotificationUserProjection.class,
                Map.of(
                        "id", 1L,
                        "fullName", TestConstants.FULL_NAME,
                        "username", TestConstants.USERNAME
                ));
    }

    public static AuthUserProjection createAuthUserProjection() {
        return factory.createProjection(
                AuthUserProjection.class,
                new HashMap<>() {{
                    put("id", 1L);
                    put("email", TestConstants.USER_EMAIL);
                    put("fullName", TestConstants.FULL_NAME);
                    put("username", TestConstants.USERNAME);
                    put("location", TestConstants.LOCATION);
                    put("about", TestConstants.ABOUT);
                    put("website", TestConstants.WEBSITE);
                    put("avatar", TestConstants.AVATAR_SRC_1);
                }});
    }

    public static UserCommonProjection createUserCommonProjection() {
        return factory.createProjection(
                UserCommonProjection.class,
                Map.of(
                        "id", TestConstants.USER_ID,
                        "email", TestConstants.USER_EMAIL,
                        "fullName", TestConstants.FULL_NAME,
                        "activationCode", TestConstants.ACTIVATION_CODE,
                        "passwordResetCode", TestConstants.PASSWORD_RESET_CODE
                ));
    }
}
