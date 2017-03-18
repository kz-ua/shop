package com.cn.kz.tech.shop.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by kz on 18.03.17.
 */
@Entity
public class Role {
    private long roleId;
    private String rolename;
    private Collection<Account> accountsByRoleId;

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "rolename")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (rolename != null ? !rolename.equals(role.rolename) : role.rolename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<Account> getAccountsByRoleId() {
        return accountsByRoleId;
    }

    public void setAccountsByRoleId(Collection<Account> accountsByRoleId) {
        this.accountsByRoleId = accountsByRoleId;
    }
}
