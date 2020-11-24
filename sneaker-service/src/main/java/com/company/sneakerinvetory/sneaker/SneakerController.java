package com.company.sneakerinvetory.sneaker;

import com.company.sneakerinvetory.HelloController;
import com.company.sneakerinvetory.MySQLConnction.DatabaseOperation;
import com.company.sneakerinvetory.ebay.EbayReader;
import com.company.sneakerinvetory.ebay.EbayThread;
import com.company.sneakerinvetory.ebay.SneakerForm;
import com.company.sneakerinvetory.login.LoginResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{ "index": "1", "id" : "tester", "SneakerName" : "yeezy 350 v2", "Sku" : "fu9006", "size" : "11", "price" : "$350" }' "http://localhost:8080/editSneaker"
@RestController
public class SneakerController {

    @ResponseBody
    @RequestMapping(value = "/addSneaker", method = RequestMethod.POST)
    public LoginResponse handleAddSneaker(@RequestBody Sneaker sneaker, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isRedirectedSession = HelloController.validate_or_redirect_session(request,response);

        if (!isRedirectedSession) {
            HttpSession session = request.getSession();
            DatabaseOperation operation = new DatabaseOperation();
            operation.createConnect();

            boolean valid = Sneaker.validateAddSneaker(sneaker);
            if (valid) {
                SneakerForm form = new SneakerForm(sneaker.getSku(),sneaker.getShoeName(),sneaker.getSize());
                EbayThread runner = new EbayThread(form);
                runner.run();
                boolean createSneaker = operation.insertData(sneaker.getShoeName(), sneaker.getSku(), sneaker.getSize(), sneaker.getPrice(), runner.getPrice(), (String) session.getAttribute("username"));
                if (createSneaker) {
                    return new LoginResponse("SessionID");
                }
            }
            operation.closeConnection();
            return new LoginResponse("NaN");
        }

        return new LoginResponse("newSession"); //redirected
    }

    @ResponseBody
    @RequestMapping(value = "/editSneaker", method = RequestMethod.POST)
    public LoginResponse handleEditSneaker(@RequestBody Sneaker sneaker, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isRedirectedSession = HelloController.validate_or_redirect_session(request,response);

        if (!isRedirectedSession) {
            DatabaseOperation operation = new DatabaseOperation();
            operation.createConnect();

            HttpSession session = request.getSession();
            boolean valid = Sneaker.validateEditSneaker(sneaker);
            if (valid) {
                SneakerForm form = new SneakerForm(sneaker.getSku(),sneaker.getShoeName(),sneaker.getSize());
                EbayThread runner = new EbayThread(form);
                runner.run();
                boolean createSneaker = operation.editForm(sneaker.getIndex(), sneaker.getShoeName(), sneaker.getSku(), sneaker.getSize(), sneaker.getPrice(), runner.getPrice(), (String) session.getAttribute("username"));
                if (createSneaker) {
                    return new LoginResponse("SessionID");
                }
            }
            operation.closeConnection();
            return new LoginResponse("NaN");
        }
        return new LoginResponse("newSesison");
    }

    //check that sessionID exists first
    @ResponseBody
    @RequestMapping(value= "/deleteSneaker", method = RequestMethod.POST)
    public LoginResponse handleDeleteSneaker(@RequestBody Sneaker sneaker, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        boolean isRedirectedSession = HelloController.validate_or_redirect_session(request,response);

        if (!isRedirectedSession) {
            DatabaseOperation operation = new DatabaseOperation();
            operation.createConnect();

            String username = (String) request.getSession().getAttribute("username");
            boolean existing_user = operation.checkName(username);
            if (existing_user) {
                boolean existingSneaker = operation.querySneakerExists(username, sneaker.getIndex());
                if (existingSneaker) {
                    boolean delete_sneaker = operation.deleteSneakerRow(username, sneaker.getIndex());
                    if (delete_sneaker) {
                        return new LoginResponse("SessionID");
                    }
                }
            }
            operation.closeConnection();
            return new LoginResponse("NaN");
            // check that sneaker exists based on index
        }
        return new LoginResponse("newSession");

    }


}
