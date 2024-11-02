package com.gmail.drack;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import com.gmail.drack.commons.utils.TestConstants;
import com.gmail.drack.repository.projection.AuthUserProjection;
import com.gmail.drack.repository.projection.NotificationUserProjection;
import com.gmail.drack.repository.projection.UserCommonProjection;

public class UserServiceTestHelper {

    private static final ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

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
