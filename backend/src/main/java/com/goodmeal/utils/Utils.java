package com.goodmeal.utils;

import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@NoArgsConstructor
public class Utils {

    public String getLoginFromPrincipal(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        return login;
    }

}
