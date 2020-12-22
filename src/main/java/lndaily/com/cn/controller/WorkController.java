package lndaily.com.cn.controller;

import lndaily.com.cn.bean.User;
import lndaily.com.cn.bean.Work;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private HttpSession session;

    @RequestMapping("findall")
    public Table<Work> findall(int page, int limit){

        return workService.findall(page,limit);
    }

    @RequestMapping("newwork")
    public String newwork(Work work){
        Integer userid = (Integer) session.getAttribute("userid");
        work.setWork_userid(userid);
        //System.out.println(work);
        return workService.savework(work);

    }

    @RequestMapping("findcon")
    public Table<Work> findcon(Work work,String name){
        if(name != null && name !=""){
            User user = new User();
            user.setName(name);
            work.setUser(user);
        }

        Table<Work> workTable = workService.findcon(work);
        //return workList;

        return workTable;
    }

    @RequestMapping("updatework")
    public String updatework(Work work){
        Integer userid = (Integer) session.getAttribute("userid");
        System.out.println(work.getWorktime());
        work.setWork_userid(userid);
        return workService.updateworkByWorkId(work);
    }

    @RequestMapping("delwork")
    public String delwork(Integer workid,Integer work_userid){
        Work work = new Work();
        work.setWork_userid(work_userid);
        work.setWorkid(workid);
        return workService.delwork(work);
    }

    @RequestMapping("sevenday")
    public List sevenday(){

        return workService.sevenday();
    }

    @RequestMapping("fivemonth")
    public List<Map> fivemonth(){

        return workService.fivemonth();
    }
}
