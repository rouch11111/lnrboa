package lndaily.com.cn.service;

import lndaily.com.cn.bean.Auth;
import lndaily.com.cn.bean.Limits;
import lndaily.com.cn.bean.Role;
import lndaily.com.cn.bean.RoleAuth;
import lndaily.com.cn.mapper.AuthMapper;
import lndaily.com.cn.mapper.RoleAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private RoleAuthMapper roleAuthMapper;

    public List<Auth> findmodel(){
        return authMapper.findmodel();
    }

    public List<Auth> findauths(Auth auth,Integer roleid){
        Map map = new HashMap();
        map.put("roleid",roleid);
        List<Auth> authList = authMapper.findauths(auth.getAuthid());
        for(Auth at:authList){
            map.put("authid",at.getAuthid());
            RoleAuth roleAuth = roleAuthMapper.findischeck(map);
            if(roleAuth.getIsauth() == 1){
                at.setStatus(1);
            }
        }
        return authList;
    }

    public List<Auth> findByFahter(Integer fatherid){
        return authMapper.findauths(fatherid);
    }

    public Auth findauth(Integer id){
        return authMapper.findauth(id);
    }



    public String giveauths(Limits limits, Role role) throws Exception {
        Class cls = limits.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            //System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(limits));
            Auth auth = authMapper.findByname(f.getName());
            Map map = new HashMap();
            map.put("roleid",role.getRoleid());
            map.put("authid",auth.getAuthid());
            if("on".equals(f.get(limits))){
                map.put("status",1);

            }
            else{
                map.put("status",0);
            }
            roleAuthMapper.updateauth(map);
        }
        return "success";
    }

}
