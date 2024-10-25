package com.gmail.drack.service;

import com.gmail.drack.repository.UserPrincipalProjection;

public interface AuthenticationService {
    Long getAuthenticatedUserId();   
    UserPrincipalProjection getUserPrincipalByEmail(String email);
}
