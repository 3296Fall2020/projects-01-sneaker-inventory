package com.company.sneakerinvetory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

    // respond with sessionID in cookie
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = createSession(request, response);

        return "Hello";
    }

    // find if cookie exists within session
    public static boolean isCookieExist(HttpServletRequest request, String name){
        if (request.getCookies() != null){
            for (Cookie cookie : request.getCookies()){
                if (cookie.getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    // retrieve session cookie
    public static Cookie getCookie(HttpServletRequest request, String name){
        if (isCookieExist(request, name))
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals(name))
                    return cookie;
            }
        return null;
    }

    // create/update cookie
    public static Cookie updateCookie(HttpServletRequest request, HttpServletResponse response, String name, String value){
        Cookie cookie = getCookie(request, name);
        if (cookie != null){
            cookie.setValue(value);
        } else{
            response.addCookie(new Cookie(name, value));
        }
        return cookie;
    }

    // invalidate current session, thus creating a new one
    public static HttpSession createSession(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        session = request.getSession();

        session.setMaxInactiveInterval(60 * 6); //six minute expiration between requests
        updateCookie(request, response, "sessionID", session.getId());
        return session;
    }

    // redirect session if session has restarted since last request
    public static Boolean validate_or_redirect_session(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.isNew()){
            session.setMaxInactiveInterval(60 * 60); //six minute expiration between requests
            updateCookie(request, response, "sessionID", session.getId());
            response.sendRedirect("https://www.google.com");
            return  true;
        }
        return false;
    }


}
