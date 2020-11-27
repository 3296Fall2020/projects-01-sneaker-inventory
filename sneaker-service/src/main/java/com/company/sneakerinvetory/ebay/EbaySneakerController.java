package com.company.sneakerinvetory.ebay;

        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;

// curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X GET --data '{ "sku" : "fu9006", "size" : "11", "name": "yeezy 350 black"}' "http://localhost:8080/ebayAverage"
@RestController
public class EbaySneakerController {

    @RequestMapping(value = "/ebayAverage", method = RequestMethod.GET)
    public EbayResponse handleEbayAverage(@RequestBody EbaySneaker ebaySneaker){
        boolean isValid = EbaySneaker.validateEbaySneaker(ebaySneaker);

        if (isValid){
            EbayThread thread = new EbayThread(ebaySneaker);
            thread.run();
            return new EbayResponse(thread.getPrice());
        }
        return new EbayResponse("NaN bad request");
    }
}
