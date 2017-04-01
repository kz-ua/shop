package com.cn.kz.tech.shop.repository;

import com.cn.kz.tech.shop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kz on 18.03.17.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /*@Override
    List<User> findAll();*/
}
