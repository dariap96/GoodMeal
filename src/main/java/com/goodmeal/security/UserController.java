package com.goodmeal.security;

import com.goodmeal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServiceImplementation userService;

    @GetMapping("/")
    public String login() {
        return "authenticated successfully";
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        System.out.println(user.getLogin());

        if(!userService.saveUser(user)) { return false; }

        return true;
    }

    // -------- NEED TO IMPLEMENT --------
    //
    // -----------------------------------
    @GetMapping("/get_userdetails")
    public ResponseEntity<UserDetails> getUserDetails(@RequestBody String username) {
        return (ResponseEntity<UserDetails>) userService.loadUserByUsername(username);
    }

    @GetMapping("/get_credentials")
    public String getUserCredentials() {
        return "USER";
    }

}
