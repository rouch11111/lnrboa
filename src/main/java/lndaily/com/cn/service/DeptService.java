package lndaily.com.cn.service;

import lndaily.com.cn.bean.Dept;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public Table<Dept> findall(){
        Table<Dept> deptTable = new Table<>();
        List<Dept> deptList = deptMapper.findall();
        deptTable.setCode(0);
        deptTable.setCount(findallcount());
        deptTable.setData(deptList);
        deptTable.setMsg("success");
        return  deptTable;
    }

    public int findallcount(){
        return deptMapper.findallcount();
    }

    /**
     *
     * @param deptid 部门id
     * @return 部门
     */
    public Dept finddeptByid(Integer deptid){
        return deptMapper.finddeptByid(deptid);
    }

    /**
     *
     * @param deptname 部门名称
     * @return 字符串
     */
    public String savedept(String deptname){
        Dept dept = deptMapper.finddeptByname(deptname);
        if(dept != null){
            if(deptname.equals(dept.getDeptName())){
                return "same";
            }
        }
        //deptMapper.savedept(deptname);
        return "success";
    }
}
