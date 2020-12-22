package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.UserRole;

import java.util.Map;

public interface UserRoleMapper {

    public UserRole findchecked(Map map);

    public UserRole findrole(Integer userid);

    public void saveuserrole(UserRole userRole);

    public void deluserrole(UserRole userRole);
}
