package lndaily.com.cn.service;

import lndaily.com.cn.bean.Auth;
import lndaily.com.cn.bean.Role;
import lndaily.com.cn.bean.RoleAuth;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.mapper.AuthMapper;
import lndaily.com.cn.mapper.RoleAuthMapper;
import lndaily.com.cn.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    public RoleMapper roleMapper;
    @Autowired
    public AuthMapper authMapper;
    @Autowired
    public RoleAuthMapper roleAuthMapper;

    public Table<Role> findall(){
        Table<Role> roleTable = new Table<>();

        List<Role> roleList = roleMapper.findall();
        roleTable.setCode(0);
        roleTable.setCount(10);
        roleTable.setData(roleList);
        return roleTable;
    }

    @Transactional
    public String saverole(Role role){
        Role role1 = roleMapper.findByname(role);
        if(role1 != null){
            if(role1.getRolename().equals(role.getRolename())){
                return "same";
            }
        }
        roleMapper.saverole(role);
        List<Auth> authList = authMapper.findchildren();
        RoleAuth roleAuth = new RoleAuth();
        for(Auth auth:authList){
            roleAuth.setRoleid(role.getRoleid());
            roleAuth.setAuthid(auth.getAuthid());
            roleAuth.setIsauth((short) 0);
            roleAuthMapper.saveroleauth(roleAuth);
        }
        return "success";
    }
}
