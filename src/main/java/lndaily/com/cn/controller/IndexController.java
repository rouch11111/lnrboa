package lndaily.com.cn.controller;


import lndaily.com.cn.bean.Dept;
import lndaily.com.cn.bean.Role;
import lndaily.com.cn.bean.User;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.service.DeptService;
import lndaily.com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import java.util.List;



@Controller
public class IndexController {

    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("newwork")
    public String newwork(){
        return "newwork";
    }

    @RequestMapping("worklist")
    public String worklist(){
        return "worklist";
    }

    @RequestMapping("first")
    public  String first(){
        return "first";
    }

    @RequestMapping("upload")
    public  String upload(){
        return "upload";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("workdetail")
    public String workdetail(){
        return "workdetail";
    }

    @RequestMapping("updatework")
    public String updatework(){
        return "updatework";
    }

    @RequestMapping("empdetail")
    public String empdetail(Model model){
        Integer userid = (Integer) session.getAttribute("userid");
        User user = userService.findById(userid);
        Integer deptid = user.getDeptid();
        Dept dept = deptService.finddeptByid(deptid);
        model.addAttribute("name",user.getName());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("phone",user.getTelephone());
        model.addAttribute("deptname",dept.getDeptName());
        return "empdetail";
    }

    @RequestMapping("emppass")
    public String emppass(){
        return "emppass";
    }

    @RequestMapping("deptlist")
    public String deptlist(){
        return "deptlist";
    }

    @RequestMapping("newdept")
    public String newdept(){
        return "newdept";
    }

    @RequestMapping("emplist")
    public String emplist(Model model){
        Table<Dept> deptTable = deptService.findall();
        List<Dept> deptList = deptTable.getData();
        model.addAttribute("deptlist",deptList);
        return "emplist";
    }

    @RequestMapping("newemp")
    public String newemp(){
        return "newemp";
    }

    @RequestMapping("filelist")
    public String filelist(){
        return "filelist";
    }

    @RequestMapping("newrole")
    public String newrole(){ return "newrole";}

    @RequestMapping("rolelist")
    public String rolelist(){ return "rolelist";}

    @RequestMapping("updaterole")
    public String updaterole(Role role,Model model){

        model.addAttribute("role",role);
        return "updaterole";
    }

    @RequestMapping("giverole")
    public String giverole(int userid,Model model)
    {
        model.addAttribute("userid",userid);
        return "giverole";
    }

    @RequestMapping("newinfo")
    public String newinfo(){
        return "newinfo";
    }

    @RequestMapping("infolist")
    public String infolist(){
        return "infolist";
    }

}
