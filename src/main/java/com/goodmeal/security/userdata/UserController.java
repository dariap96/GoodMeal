package com.goodmeal.security.userdata;

import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private UserDtoMapper userDtoMapper = new UserDtoMapper();

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
    public UserDTO getUserInfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        User user = usersRepository.getUserByLogin(login);

        return userDtoMapper.toDTO(user);
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    @ResponseBody
    public UserDetails getUserDetails(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String login = principal.getName();

        UserDetails user = userService.loadUserByUsername(login);
        return user;
    }

    @RequestMapping(value = "/update-password/{login}", method = RequestMethod.PUT)
    public boolean updateUserPassword(@PathVariable String login, @RequestBody String newPassword) {
        User user = usersRepository.getUserByLogin(login);
        user.setPassword(newPassword);
        usersRepository.save(user);
        return true;
    }
}
