package com.company.sneakerinvetory.inventory;

import com.company.sneakerinvetory.HelloController;
import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X GET --data '{ "id" : "tester"}' "http://localhost:8080/inventory"
@CrossOrigin(origins = "https://tuc56947.github.io", allowedHeaders = "*",allowCredentials = "true")
@RestController
public class InventoryController {
    @ResponseBody
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public InventoryResponse handleInventory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");

        boolean isRedirectedSession = HelloController.validate_or_redirect_session(request,response);
        // verify session is still valid
        if (!isRedirectedSession) {


            DatabaseOperation operation = new DatabaseOperation();
            operation.createConnect();
            String username = (String) request.getSession().getAttribute("username");
           // boolean goodName = operation.checkName(username);
           // if (goodName) {
                String json = operation.queryInventory(username);
                if (!json.equals("NaN")) {
                    return new InventoryResponse(json);
           //     }
            }
            return new InventoryResponse("NaN");
        }
        // respond that new session has been created
        return new InventoryResponse("newSession");
    }
}
