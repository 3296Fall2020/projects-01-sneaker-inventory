package com.company.sneakerinvetory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SneakerController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(){
        return "\nHello world";
    }


}
