package com.company.sneakerinvetory.ebay;

        import com.company.sneakerinvetory.HelloController;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X GET --data '{ "sku" : "fu9006", "size" : "11", "name": "yeezy 350 black"}' "http://localhost:8080/ebayAverage"
@CrossOrigin(origins = "https://tuc56947.github.io", allowedHeaders = "*",allowCredentials = "true")
@RestController
public class EbaySneakerController {

    @RequestMapping(value = "/ebayAverage", method = RequestMethod.GET)
    public EbayResponse handleEbayAverage(@RequestBody SneakerForm sneakerForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        boolean isValid = SneakerForm.validateEbaySneaker(sneakerForm);

        boolean isRedirectedSession = HelloController.validate_or_redirect_session(request, response);

        if (!isRedirectedSession) {
            if (isValid) {
                EbayThread thread = new EbayThread(sneakerForm);
                thread.run();
                return new EbayResponse(thread.getPrice());
            }
            return new EbayResponse("NaN bad request");
        }
        return new EbayResponse("newSession");
    }
}
