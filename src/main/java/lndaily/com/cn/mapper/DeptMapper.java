package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.Dept;

import java.util.List;

public interface DeptMapper {

    public List<Dept> findall();

    public int findallcount();

    public Dept finddeptByid(Integer deptid);

    public Dept finddeptByname(String deptName);

    public void savedept(String deptName);

}
