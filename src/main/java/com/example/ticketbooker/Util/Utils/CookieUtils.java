package com.example.ticketbooker.Util.Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtils {
    public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return defaultValue;
                    }
                }
            }
        }
        return defaultValue;
    }

    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String path, int maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
//        cookie.setHttpOnly(true); // Cookie chỉ có thể được truy cập qua HTTP, bảo vệ khỏi JS
        cookie.setPath(path);
        if(maxAge > 0){
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }
}
