package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.Auth;

import java.util.List;

public interface AuthMapper {

    public List<Auth> findmodel();

    public List<Auth> findauths(Integer id);

    public Auth findauth(Integer id);



    //查找authfatherid不为0的权限
    public List<Auth> findchildren();
    //通过权限名查找权限
    public Auth findByname(String name);
}
