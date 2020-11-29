package com.company.sneakerinvetory;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@CrossOrigin(origins = "https://tuc56947.github.io", allowedHeaders = "*",allowCredentials = "true")
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
        response.setHeader("SET-COOKIE", "JSESSIONID="+session.getId() +";SameSite=none; Secure");
        response.setHeader("Set-Cookie", "JSESSIONID="+session.getId() +";SameSite=none; Secure");

        return session;
    }

    // redirect session if session has restarted since last request
    public static Boolean validate_or_redirect_session(HttpServletRequest request, HttpServletResponse response) throws IOException {

       // String sessionValid = request.getHeader("JSESSIONID");
        Cookie[] cookies = request.getCookies();
        boolean sessionExists = false;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("JSESSIONID") && (cookie.getValue() != null)){
                sessionExists = true;
            }
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }

        if (!sessionExists){
            createSession(request,response);
          //  response.sendRedirect("https://terence21.github.io/terence/#/login");

            return  true;
        }
        return false;
    }


}
