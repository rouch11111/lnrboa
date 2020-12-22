package lndaily.com.cn.service;

import lndaily.com.cn.bean.Role;
import lndaily.com.cn.bean.RoleAuth;
import lndaily.com.cn.mapper.RoleAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleAuthService {

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    public List<RoleAuth> findauths(Integer roleid){
        return roleAuthMapper.findauths(roleid);
    }

    public RoleAuth findischeck(Map map){
        return roleAuthMapper.findischeck(map);
    }


}
