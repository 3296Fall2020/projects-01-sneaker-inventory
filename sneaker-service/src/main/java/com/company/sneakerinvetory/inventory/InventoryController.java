package com.company.sneakerinvetory.inventory;

import com.company.sneakerinvetory.HelloController;
import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X GET --data '{ "id" : "tester"}' "http://localhost:8080/inventory"
@CrossOrigin(origins = "https://54.172.190.202:443", allowedHeaders = "*")
@RestController
public class InventoryController {
    @ResponseBody
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public InventoryResponse handleInventory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        boolean isRedirectedSession = HelloController.validate_or_redirect_session(request,response);

        // verify session is still valid
        if (!isRedirectedSession) {
            DatabaseOperation operation = new DatabaseOperation();
            operation.createConnect();
            String username = (String) request.getSession().getAttribute("username");
            boolean goodName = operation.checkName(username);
            if (goodName) {
                String json = operation.queryInventory(username);
                if (!json.equals("NaN")) {
                    return new InventoryResponse(json);
                }
            }
            return new InventoryResponse("NaN");
        }
        // respond that new session has been created
        return new InventoryResponse("newSession");
    }
}
