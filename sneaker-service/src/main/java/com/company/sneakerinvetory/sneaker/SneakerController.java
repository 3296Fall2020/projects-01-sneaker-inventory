package com.company.sneakerinvetory.sneaker;

import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.login.LoginResponse;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "index": "1", "id" : "tester", "SneakerName" : "yeezy 350 v2", "Sku" : "fu9006", "size" : "11", "price" : "$350" }' "http://localhost:8080/editSneaker"
@RestController
public class SneakerController {

    @ResponseBody
    @RequestMapping(value = "/addSneaker", method = RequestMethod.POST)
    public LoginResponse handleAddSneaker(@RequestBody Sneaker sneaker){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean valid = Sneaker.validateAddSneaker(sneaker);
        if (valid) {
            boolean createSneaker = operation.insertData(sneaker.getShoeName(), sneaker.getSku(), sneaker.getSize(), sneaker.getPrice(), sneaker.getid());
            if (createSneaker) {
                return new LoginResponse("SessionID");
            }
        }
        operation.closeConnection();
        return new LoginResponse("NaN");
    }

    @ResponseBody
    @RequestMapping(value = "/editSneaker", method = RequestMethod.POST)
    public LoginResponse handleEditSneaker(@RequestBody Sneaker sneaker){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean valid = Sneaker.validateEditSneaker(sneaker);
        if (valid) {
            boolean createSneaker = operation.editForm(sneaker.getIndex(), sneaker.getShoeName(), sneaker.getSku(), sneaker.getSize(), sneaker.getPrice(), sneaker.getid());
            if (createSneaker) {

                return new LoginResponse("SessionID");
            }
        }
        operation.closeConnection();
        return new LoginResponse("NaN");
    }

    //check that sessionID exists first
    @ResponseBody
    @RequestMapping(value= "/deleteSneaker", method = RequestMethod.POST)
    public LoginResponse handleDeleteSneaker(@RequestBody Sneaker sneaker) throws SQLException {
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();

        boolean existing_user = operation.checkName(sneaker.getid());
        if (existing_user) {
            boolean existingSneaker = operation.querySneakerExists(sneaker.getid(), sneaker.getIndex());
            if (existingSneaker) {
                boolean delete_sneaker = operation.deleteSneakerRow(sneaker.getid(), sneaker.getIndex());
                if (delete_sneaker) {
                    return new LoginResponse("SessionID");
                }
            }
        }
        operation.closeConnection();
        return new LoginResponse("NaN");
        // check that sneaker exists based on index

    }


}