package com.goodmeal.security;

import com.goodmeal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.concurrent.ExecutionException;

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

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    @ResponseBody
    public UserDetails getUserDetails(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        UserDetails user = userService.loadUserByUsername(login);
        return user;
    }

}
