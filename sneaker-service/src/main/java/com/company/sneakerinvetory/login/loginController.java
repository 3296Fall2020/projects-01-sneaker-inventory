package com.company.sneakerinvetory.login;

import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.login.LoginForm;
import com.company.sneakerinvetory.login.LoginResponse;
import com.company.sneakerinvetory.sneaker.Sneaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Random;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "id" : "ternece", "password" : "password"}' "http://localhost:8080/login"
// tutorial: https://www.baeldung.com/spring-request-response-body
// https://www.baeldung.com/spring-session 
@RestController
public class loginController {


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse handleLogin(@RequestBody LoginForm userForm) throws SQLException {
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        Random random = new Random();
        int sessionId = random.nextInt(Integer.MAX_VALUE);
        String string_session = String.valueOf(sessionId);
        boolean available_user = operation.signIn(userForm.getId(), userForm.getPassword(), string_session);

        if (available_user){
            return new LoginResponse(operation.querySessionID(userForm.getId()));
        }
        operation.closeConnection();
        return  new LoginResponse("username or password incorrect");

    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public LoginResponse handleRegister(@RequestBody LoginForm userForm) throws SQLException {
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        Random random = new Random();
        int sessionId = random.nextInt(Integer.MAX_VALUE);
        String string_session = String.valueOf(sessionId);
        boolean available_user = operation.checkName(userForm.getId());

        if (!available_user){
            boolean addUser = operation.addUser(userForm.getId(), userForm.getPassword());
            if (addUser) {
                operation.signIn(userForm.getId(), userForm.getPassword(), string_session);
                return new LoginResponse(operation.querySessionID(userForm.getId()));
            }
        }
        operation.closeConnection();
        return new LoginResponse("SessionId");


    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public LoginResponse handleLogout(@RequestBody LoginForm userForm) throws SQLException {
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean available_user = operation.checkName(userForm.getId());

        if (available_user){
            operation.signOut(userForm.getId(), userForm.getPassword());
        }
        operation.closeConnection();
        return new LoginResponse("NaN");
    }

    @ResponseBody
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public LoginResponse sessionResponse(@RequestBody String userID) throws SQLException {
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean available_user = operation.checkName(userID);
        if (available_user){
            return new LoginResponse(operation.querySessionID(userID));
        }
        return new LoginResponse(operation.querySessionID("NaN"));
    }

    @ResponseBody
    @RequestMapping(value = "/addSneaker", method = RequestMethod.POST)
    public LoginResponse sessionResponse(@RequestBody Sneaker sneaker){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean createSneaker = operation.insertData(sneaker.getSneakerName(), sneaker.getSku(), sneaker.getSize(), sneaker.getDate(), sneaker.getPrice(), sneaker.getUserID());
        if (createSneaker){
            return new LoginResponse("SessionID");
        }
        return new LoginResponse("NaN");
    }



}
