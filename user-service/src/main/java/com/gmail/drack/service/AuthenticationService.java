package com.gmail.drack.service;

import com.gmail.drack.commons.dto.response.user.UserPrincipalResponse;

public interface AuthenticationService {
    Long getAuthenticatedUserId();   
    UserPrincipalResponse getUserPrincipalByEmail(String email);
}
