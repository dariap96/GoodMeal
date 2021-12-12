package com.goodmeal.security.userdata;

import com.goodmeal.entities.User;
import com.goodmeal.repositoriesImplementations.UsersRepositoryImplementation;
import com.goodmeal.services.impl.UsersService;
import com.goodmeal.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private Utils utils = new Utils();

    private UserDtoMapper userDtoMapper = new UserDtoMapper();

    @Autowired
    UsersService usersService;

    @Autowired
    UserServiceImplementation userService;

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        if(!userService.saveUser(user)) { return false; }
        return true;
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public UserDTO getUserInfo(HttpServletRequest request) {
        String login = utils.getLoginFromPrincipal(request);

        User user = usersService.getUserByLogin(login);
        return userDtoMapper.toDTO(user);
    }

    @RequestMapping(value = "/userinfo/{userId}", method = RequestMethod.GET)
    public UserDTO getUserInfoByAdmin(@PathVariable Long userId) {
        User user = usersService.getUserById(userId);
        return userDtoMapper.toDTO(user);
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public UserDetails getUserDetails(HttpServletRequest request) {
        String login = utils.getLoginFromPrincipal(request);

        UserDetails user = userService.loadUserByUsername(login);
        return user;
    }

    @Transactional
    @RequestMapping(value = "/update-password", method = RequestMethod.PUT)
    public boolean updateUserPassword(HttpServletRequest request, @RequestBody String newPassword) {
        User user = usersService.getUserByLogin(utils.getLoginFromPrincipal(request));
        user.setPassword(newPassword);

        userService.saveUser(user);
        return true;
    }

    @Transactional
    @RequestMapping(value = "/update-password-by-admin/{login}", method = RequestMethod.PUT)
    public boolean updateUserPasswordByAdmin(@PathVariable String login, @RequestBody String newPassword) {
        User user = usersService.getUserByLogin(login);
        user.setPassword(newPassword);

        userService.saveUser(user);
        return true;
    }
}
