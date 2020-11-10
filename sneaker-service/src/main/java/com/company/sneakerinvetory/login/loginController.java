package com.company.sneakerinvetory.login;

import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.login.LoginForm;
import com.company.sneakerinvetory.login.LoginResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "id" : "ternece", "password" : "password"}' "http://localhost:8080/login"
// tutorial: https://www.baeldung.com/spring-request-response-body
// https://www.baeldung.com/spring-session 
@RestController
public class loginController {


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse handleLogin(@RequestBody LoginForm userForm){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean available_user = operation.signIn(userForm.getId(), userForm.getPassword());
        operation.closeConnection();
        if (available_user){
            return new LoginResponse("session id login");
        }
        return  new LoginResponse("username or password incorrect");

    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public LoginResponse handleRegister(@RequestBody LoginForm userForm){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean available_user = operation.signIn(userForm.getId(), userForm.getPassword());
        operation.closeConnection();
        if (!available_user){
            operation.addUser(userForm.getId(), userForm.getPassword());
            return  new LoginResponse("session id register");
        }
        return new LoginResponse("user already exists");
    }



}
