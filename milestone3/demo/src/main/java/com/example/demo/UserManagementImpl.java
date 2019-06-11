package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RestController
@SpringBootApplication
public class UserManagementImpl implements UserManagement
{

    @Autowired
    UserRepository userRepository;

    public String checkSession(HttpSession httpSession) {
        String message = "No info received";

        if (httpSession.getAttribute("user") != null) {
            return "Succeed";
        } else {
            return "Failure";
        }
    }

    public void logout(HttpSession httpSession) {
        httpSession.setAttribute("user",null);
    }

    public String checkLogin(String usn, String psw, HttpSession httpSession) {
        User user = userRepository.getUserByUsername(usn);

        if (user == null) {
            return "Unknown user";
        } else if (user.getPassword().equals(psw)) {
            if (user.getIs_valid() == 0) {
                return "Blocked user";
            }

            httpSession.setAttribute("user", usn);

            return "Succeed";
        } else {
            return "Failure";
        }
    }

    public String getInfo(HttpSession httpSession) {
        Object checkUser = httpSession.getAttribute("user");

        if(checkUser == null) {
            return "Not logged in";
        } else {
            User user = userRepository.getUserByUsername(checkUser.toString());

            return(
                    "{\"phone\" : \"" + user.getPhone() +
                            "\", \"address\" : \"" + user.getAddress() +
                            "\", \"email\" : \"" +user.getEmail() +
                            "\", \"name\" : \""+ user.getName() +"\"}"
            );
        }
    }

    public String updateProfile(HttpSession httpSession, String phone, String email, String name, String address) {
        Object checkUser = httpSession.getAttribute("user");

        if(checkUser == null) {
            return "Not logged in";
        }

        User user = userRepository.getUserByUsername(checkUser.toString());

        if (checkUser == null) {
            return "Unknown user";
        }

        user.setAddress(address);
        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);
        userRepository.save(user);

        return "Succeed";
    }

    public String signUp(HttpSession httpSession, String username, String password, String phone, String email,  String address, String realname) {
        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            return "Username used";
        }

        User newCustomer = new User();
        newCustomer.setAddress(address);
        newCustomer.setEmail(email);
        newCustomer.setName(realname);
        newCustomer.setPassword(password);
        newCustomer.setPhone(phone);
        newCustomer.setUsername(username);
        newCustomer.setIs_valid(1);
        userRepository.save(newCustomer);
        httpSession.setAttribute("user", username);

        return "Succeed";
    }
}