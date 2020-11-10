package com.company.sneakerinvetory.login;

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
        System.out.println(userForm.getId());
        System.out.println(userForm.getPassword());

        // call validate function

        // if validated return LoginResponse... random sessionID
        return new LoginResponse("session id");

        // else
        // return new LoginResponse("Error: Login not legit")
    }


}
