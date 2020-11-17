package com.company.sneakerinvetory.sneaker;

import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.login.LoginResponse;
import org.springframework.web.bind.annotation.*;

//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "index": "1", "id" : "tester", "SneakerName" : "yeezy 350 v2", "Sku" : "fu9006", "size" : "11", "price" : "$350" }' "http://localhost:8080/editSneaker"
@RestController
public class SneakerController {

    @ResponseBody
    @RequestMapping(value = "/addSneaker", method = RequestMethod.POST)
    public LoginResponse handleAddSneaker(@RequestBody Sneaker sneaker){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean createSneaker = operation.insertData(sneaker.getSneakerName(), sneaker.getSku(), sneaker.getSize(), sneaker.getPrice(), sneaker.getid());
        if (createSneaker){
            return new LoginResponse("SessionID");
        }
        return new LoginResponse("NaN");
    }

    @ResponseBody
    @RequestMapping(value = "/editSneaker", method = RequestMethod.POST)
    public LoginResponse handleEditSneaker(@RequestBody Sneaker sneaker){
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean createSneaker = operation.editForm(sneaker.getIndex(), sneaker.getSneakerName(), sneaker.getSku(), sneaker.getSize(), sneaker.getPrice(), sneaker.getid());
        if (createSneaker){
            return new LoginResponse("SessionID");
        }
        return new LoginResponse("NaN");
    }
}
