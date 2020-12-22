package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public User checklogin(Map map);

    public User findById(Integer id);

    public User findByusername(User user);

    public void updatepassword(User user);

    public void updateuser(User user);

    public List<User> findall(Map map);

    public List<User> findcon(User user);

    public int findallcount();

    public int findconcount(User user);

    public void saveuser(User user);
}
