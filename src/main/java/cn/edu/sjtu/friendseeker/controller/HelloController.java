package cn.edu.sjtu.friendseeker.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @CrossOrigin
    @RequestMapping("")
    public String index() {
        String output = "Hello jenkins";
        return output;
    }
}
