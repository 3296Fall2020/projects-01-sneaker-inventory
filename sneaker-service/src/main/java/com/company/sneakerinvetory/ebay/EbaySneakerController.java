package com.company.sneakerinvetory.ebay;

        import com.company.sneakerinvetory.HelloController;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X GET --data '{ "sku" : "fu9006", "size" : "11", "name": "yeezy 350 black"}' "http://localhost:8080/ebayAverage"
@RestController
public class EbaySneakerController {

    @RequestMapping(value = "/ebayAverage", method = RequestMethod.GET)
    public EbayResponse handleEbayAverage(@RequestBody SneakerForm sneakerForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
