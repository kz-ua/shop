package com.cn.kz.tech.shop.rest;

import com.cn.kz.tech.shop.model.User;
import com.cn.kz.tech.shop.repository.UserRepository;
import com.cn.kz.tech.shop.security.token.AuthenticationWithToken;
import com.cn.kz.tech.shop.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kz on 18.03.17.
 */

@RepositoryRestController
public class UserController {
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);

    private final UserRepository repository;

    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/users/user2", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_UTF8_VALUE*/)
    public  @ResponseBody List<User> listAllUsers() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthenticationWithToken user=null;
        if (principal instanceof AuthenticationWithToken) {
            user = ((AuthenticationWithToken)principal);
        }
        List<User> users = userService.findAllUsers();
        if (users == null || users.isEmpty()) {
            return users;
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return users;
    }

}
