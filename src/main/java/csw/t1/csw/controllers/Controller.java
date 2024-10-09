package csw.t1.csw.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class Controller {

    @GetMapping
    public String test(){
        return "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    }
}
