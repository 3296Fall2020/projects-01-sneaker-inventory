package com.company.sneakerinvetory.login;

import com.company.sneakerinvetory.HelloController;
import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.login.LoginForm;
import com.company.sneakerinvetory.login.LoginResponse;
import com.company.sneakerinvetory.sneaker.Sneaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Random;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "id" : "ternece", "password" : "password"}' "http://localhost:8080/login"
// tutorial: https://www.baeldung.com/spring-request-response-body
// https://www.baeldung.com/spring-session 
@RestController
public class loginController {


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse handleLogin(@RequestBody LoginForm userForm, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        // check that username/password exists in database
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();

        // GET http
        boolean available_user = operation.signIn(userForm.getId(), userForm.getPassword());

        if (available_user){
            HttpSession session = HelloController.createSession(request, response);
            response.addCookie(new Cookie("username", userForm.getId()));
            response.addCookie(new Cookie("password", userForm.getPassword()));
            session.setAttribute("username", userForm.getId());
            session.setAttribute("password", userForm.getPassword());

            return new LoginResponse("login");
        }

        operation.closeConnection();
        return  new LoginResponse("username or password incorrect");

    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST) // return error as string in future
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

                return new LoginResponse("register");
            }
        }
        operation.closeConnection();
        return new LoginResponse("NaN");


    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public LoginResponse handleLogout(@RequestBody LoginForm userForm, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();

        HttpSession session_new = HelloController.createSession(request, response);
        return new LoginResponse("session_new");
    }

    // validate userID
    @ResponseBody
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public LoginResponse sessionResponse(HttpServletRequest request, HttpServletResponse response) throws SQLException {
       HttpSession session = request.getSession();
       return new LoginResponse(session.getId());
    }




}
