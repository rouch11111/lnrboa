package lndaily.com.cn.controller;


import lndaily.com.cn.bean.Role;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("newrole")
    public String newrole(Role role){
        return roleService.saverole(role);
    }

    @RequestMapping("findall")
    public Table<Role> findall(){
        return roleService.findall();
    }


}
