package com.goodmeal.security;

import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import com.goodmeal.services.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServiceImplementation userService;

    @Autowired
    UsersRepositoryImplementation usersRepository;

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

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        User user = usersRepository.getUserByLogin(login);
        return user;
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
