package com.company.sneakerinvetory.inventory;

import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X GET --data '{ "id" : "tester"}' "http://localhost:8080/inventory"

@RestController
public class InventoryController {
    @ResponseBody
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public InventoryResponse handleInventory(@RequestBody InventoryModel inventoryModel) throws SQLException {
        DatabaseOperation operation = new DatabaseOperation();
        operation.createConnect();
        boolean goodName = operation.checkName(inventoryModel.getId());
        if (goodName){
            String json =  operation.queryInventory(inventoryModel.getId());
            if (!json.equals("NaN")){
                return new InventoryResponse(json);
            }
        }
        return new InventoryResponse("NaN");
    }
}
