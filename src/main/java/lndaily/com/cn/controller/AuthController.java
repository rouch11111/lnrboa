package lndaily.com.cn.controller;


import lndaily.com.cn.bean.Auth;
import lndaily.com.cn.bean.Limits;
import lndaily.com.cn.bean.Role;
import lndaily.com.cn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("findmodel")
    public List<Auth> findmodel(){
        return authService.findmodel();
    }

    @RequestMapping("findauths")
    public List<Auth> findauths(Auth auth,Integer roleid){
        //System.out.println(roleid);
        return authService.findauths(auth,roleid);
    }

    @RequestMapping("giveauths")
    public String giveauths(Limits limits, Role role){
        try {
            return authService.giveauths(limits,role);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
