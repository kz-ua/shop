package com.cn.kz.tech.shop.repository;

import com.cn.kz.tech.shop.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kz on 25.03.17.
 */
@RepositoryRestResource(exported = false)
public interface AccountRepository extends Repository<Account, Long> {
    Account save(Account account);

    Account
    findByUsername(String username);
}
