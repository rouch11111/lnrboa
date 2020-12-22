package lndaily.com.cn.controller;

import lndaily.com.cn.bean.Auth;
import lndaily.com.cn.bean.Role;
import lndaily.com.cn.bean.User;
import lndaily.com.cn.bean.UserRole;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.service.RoleService;
import lndaily.com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;


    @RequestMapping("ppassword")
    public String ppassword(String opassword,String npassword,String rpassword){
        return userService.updatepassword(opassword, npassword, rpassword);
    }

    @RequestMapping("findall")
    public Table<User> findall(int page, int limit){

        return userService.findall(page,limit);
    }

    @RequestMapping("saveuser")
    public String saveuser(User user){
        return userService.saveuser(user);
    }

    @RequestMapping("findcon")
    public Table<User> findcon(User user){
        //System.out.println(userService.findcon());

        return userService.findcon(user);
    }

    @RequestMapping("upphone")
    public String upphone(String telephone,Integer userid){
        User user = new User();
        user.setTelephone(telephone);
        user.setUserid(userid);

        userService.updateuser(user);
        return "success";
    }

    @RequestMapping("navlist")
    public Set<Auth> navlist(){
        Integer userid = (Integer) session.getAttribute("userid");
        Set<Auth> navset = userService.navlist(userid);
        return navset;
    }

    @RequestMapping("navchild")
    public List<Auth> navchild(Auth auth){
        Integer userid = (Integer) session.getAttribute("userid");

        return userService.navchild(auth,userid);
    }

    @RequestMapping("getroles")
    public List<Role> getroles(Integer userid){

        return userService.getroles(userid);
    }

    @RequestMapping("giverole")
    public String giverole(UserRole userRole){
        return userService.giverole(userRole);
    }
}
