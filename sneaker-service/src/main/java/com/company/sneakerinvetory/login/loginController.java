package com.company.sneakerinvetory.login;

import com.company.sneakerinvetory.HelloController;
import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.sneaker.Sneaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "id" : "ternece", "password" : "password"}' "http://localhost:8080/login"
// tutorial: https://www.baeldung.com/spring-request-response-body
// https://www.baeldung.com/spring-session 
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class loginController {


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse handleLogin(@RequestBody LoginForm userForm, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        // check that username/password exists in database
        DatabaseOperation operation = new DatabaseOperation();

        operation.createConnect();
        boolean available_user = operation.signIn(userForm.getId(), userForm.getPassword());

        if (available_user){
            // if exists, create new session with user logged in, and store credentials
            HttpSession session = HelloController.createSession(request, response);
            response.addCookie(new Cookie("username", userForm.getId()));
            response.addCookie(new Cookie("password", userForm.getPassword()));
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
            session.setAttribute("username", userForm.getId());
            session.setAttribute("password", userForm.getPassword());

            //verify login response
            return new LoginResponse("login");
        }

        operation.closeConnection();
        // wrong login response
        return  new LoginResponse("username or password incorrect");

    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public LoginResponse handleRegister(@RequestBody LoginForm userForm, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();

        boolean available_user = operation.checkName(userForm.getId());

        if (!available_user){

            boolean addUser = operation.addUser(userForm.getId(), userForm.getPassword());
            if (addUser) {
                HttpSession session = HelloController.createSession(request, response);
                response.addCookie(new Cookie("username", userForm.getId()));
                response.addCookie(new Cookie("password", userForm.getPassword()));
                session.setAttribute("username", userForm.getId());
                session.setAttribute("password", userForm.getPassword());

                //verify register response
                return new LoginResponse("register");
            }
            //SQL error preventing adding user response
            return new LoginResponse("SQL error");
        }
        operation.closeConnection();
        //username is already taken response
        return new LoginResponse("NaN");


    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public LoginResponse handleLogout(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();

        // invalidate current and create new session upon every logout request
        HttpSession session_new = HelloController.createSession(request, response);
        return new LoginResponse("session_new");
    }

    // validate userID
    @ResponseBody
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public LoginResponse sessionResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        // respond with sessionID
       HttpSession session = request.getSession();
       return new LoginResponse(session.getId());
    }




}
