package com.goodmeal.security.userdata;

import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import com.goodmeal.services.impl.RolesService;
import com.goodmeal.services.impl.UsersService;
import com.goodmeal.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    private UserDtoMapper userDtoMapper = new UserDtoMapper();

    @Autowired
    RolesService rolesService;

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
        String login = Utils.getLoginFromPrincipal(request);

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
        String login = Utils.getLoginFromPrincipal(request);

        UserDetails user = userService.loadUserByUsername(login);
        return user;
    }

    @Transactional
    @RequestMapping(value = "/grant-admin-access/{login}", method = RequestMethod.GET)
    public boolean grantAdminAccess(HttpServletRequest request, @PathVariable String login) {
        User user = usersService.getUserByLogin(login);
        Set<Role> userRoleSet = user.getRoleSet();
        Role adminRole = rolesService.getRoleByRole("ADMIN");

        userRoleSet.add(adminRole);
        user.setRoleSet(userRoleSet);
        usersService.saveUser(user);
        return true;
    }

    @Transactional
    @RequestMapping(value = "/disable-admin-access/{login}", method = RequestMethod.GET)
    public boolean disableAdminAccess(HttpServletRequest request, @PathVariable String login) {
        User user = usersService.getUserByLogin(login);
        Role userRole = rolesService.getRoleByRole("USER");
        Set<Role> userRoleSet = user.getRoleSet();

        int counter = 0;
        for(Role role : userRoleSet) {
            if(role.getRole().equals("USER")) { counter++; }
        }

        if (counter <= 0) { return false; }

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userRole);
        user.setRoleSet(roleSet);
        usersService.saveUser(user);
        return true;
    }

    @Transactional
    @RequestMapping(value = "/update-password", method = RequestMethod.PUT)
    public boolean updateUserPassword(HttpServletRequest request, @RequestBody String newPassword) {
        User user = usersService.getUserByLogin(Utils.getLoginFromPrincipal(request));
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
