package lndaily.com.cn.service;

import lndaily.com.cn.bean.UserRole;
import lndaily.com.cn.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRole findchecked(Map map){
        return userRoleMapper.findchecked(map);
    }

    public UserRole findrole(Integer userid){
        return userRoleMapper.findrole(userid);
    }

    public void saveuserrole(UserRole userRole){
        userRoleMapper.saveuserrole(userRole);
    }

    public void deluserrole(UserRole userRole){
        userRoleMapper.deluserrole(userRole);
    }
}
