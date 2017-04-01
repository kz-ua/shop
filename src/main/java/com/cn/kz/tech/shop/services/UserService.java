package com.cn.kz.tech.shop.services;

import com.cn.kz.tech.shop.model.Account;
import com.cn.kz.tech.shop.model.User;
import com.cn.kz.tech.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kz on 18.03.17.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return null;//userRepository.findAll();
    }

    public long addUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    public long deleteUser(long id) {
        userRepository.delete(id);
        return id;
    }

}
