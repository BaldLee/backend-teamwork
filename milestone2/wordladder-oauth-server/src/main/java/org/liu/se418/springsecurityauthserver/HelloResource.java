package org.liu.se418.springsecurityauthserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloResource {

    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }

}
