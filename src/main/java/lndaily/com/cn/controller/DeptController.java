package lndaily.com.cn.controller;

import lndaily.com.cn.bean.Dept;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("findall")
    public Table<Dept> findall(){
        return deptService.findall();
    }

    @RequestMapping("newdept")
    public String newdept(String deptName){
        return deptService.savedept(deptName);
    }

    @RequestMapping("deptlist")
    public List<Dept> deptList(){
        Table<Dept> deptTable = deptService.findall();
        return deptTable.getData();
    }
}
