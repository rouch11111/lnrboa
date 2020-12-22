package lndaily.com.cn.controller;

import lndaily.com.cn.bean.User;
import lndaily.com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @RequestMapping("check")
    public String checklogin(String name,String password){

        User user = userService.checklogin(name, password);
        if(user == null){
            return "null";
        }

        session.setAttribute("name", user.getName());
        session.setAttribute("userid", user.getUserid());
        return "success";
    }

    @RequestMapping("logout")
    public String logout(){
        session.removeAttribute("name");
        session.removeAttribute("userid");
        return "success";
    }
}
