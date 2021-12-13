package com.goodmeal.utils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface Utils {

    static String getLoginFromPrincipal(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        return principal.getName();
    }

}
