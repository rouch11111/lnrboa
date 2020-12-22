package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.Role;

import java.util.List;

public interface RoleMapper {

    public List<Role> findall();

    public void saverole(Role role);

    public Role findByname(Role role);
}
