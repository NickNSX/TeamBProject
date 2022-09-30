package com.project.common;

import javax.servlet.http.HttpSession;

import com.project.common.exceptions.AuthenticationException;
import com.project.common.exceptions.AuthorizationException;
import com.project.user.UserResponse;


public class SecurityUtils {

    private SecurityUtils() {
        super();
    }

    
    public static void enforceAuthentication(HttpSession httpSession) {

        if (httpSession == null) {
            throw new AuthenticationException("Could not fid session. Please log in.");
        }
    }

    public static void enforcePermissions(HttpSession userSession, String expectedRole) {
        if (!((UserResponse) userSession.getAttribute("authUser")).getRole().toUpperCase().equals(expectedRole.toUpperCase())) {
            throw new AuthorizationException();
        }
    }
}
