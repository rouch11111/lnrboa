package lndaily.com.cn.service;

import lndaily.com.cn.bean.*;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.mapper.UserMapper;
import lndaily.com.cn.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleAuthService roleAuthService;
    @Autowired
    private AuthService authService;
    //登录
    public User checklogin(String username,String password){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Map<String,String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        User user = userMapper.checklogin(map);

        return  user;
    }

    public User findById(Integer id){
        return userMapper.findById(id);
    }
    //修改个人密码
    public String updatepassword(String opassword,String npassword,String rpassword){
        Integer userid = (Integer) session.getAttribute("userid");
        User user = userMapper.findById(userid);
        String ops = DigestUtils.md5DigestAsHex(opassword.getBytes());
        if(!ops.equals(user.getPassword())){
            return "olderror";
        }
        if(npassword == null || npassword == ""){
            return "null";
        }
        if(!npassword.equals(rpassword)){
            return "nosame";
        }
        String nps = DigestUtils.md5DigestAsHex(npassword.getBytes());
        user.setPassword(nps);
        userMapper.updatepassword(user);
        return "success";
    }

    public Table<User> findall(int page, int limit){
        Table<User> userTable = new Table<>();
        Map map = new HashMap();
        map.put("page",(page-1) * limit);
        map.put("limit",limit);
        List<User> userList = userMapper.findall(map);
        for(User user:userList){
            Integer deptid = user.getDeptid();
            Dept dept = deptService.finddeptByid(deptid);
            user.setDept(dept);
        }
        userTable.setCode(0);
        userTable.setCount(userMapper.findallcount());
        userTable.setMsg("success");
        userTable.setData(userList);
        return userTable;
    }

    public void updateuser(User user){
        userMapper.updateuser(user);
    }
    //新增员工
    public String saveuser(User user){
        if(user.getName() == "" || user.getName().isEmpty()){
            return "namenull";
        }
        if(user.getUsername() == "" || user.getUsername().isEmpty()){
            return "usernamenull";
        }
        User user1 = userMapper.findByusername(user);
        if(user1 != null){
            return "username";
        }
        if(user.getPassword() == "" || user.getPassword().isEmpty()){
            return "pwnull";
        }
        String npass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(npass);
        userMapper.saveuser(user);
        return "success";
    }
    //获取员工表格
    public Table<User> findcon(User user){
        int page = user.getPage();
        int newpage = (page - 1) * user.getLimit();
        user.setPage(newpage);

        List<User> userList = userMapper.findcon(user);
        for(User u:userList){
            Integer deptid = u.getDeptid();
            Dept dept = deptService.finddeptByid(deptid);
            u.setDept(dept);
        }
        Table<User> userTable = new Table<>();
        userTable.setCode(0);
        userTable.setCount(userMapper.findconcount(user));
        userTable.setData(userList);
        return userTable;
    }
    //获取首页一级菜单
    public Set<Auth> navlist(Integer userid){
        UserRole userRole = userRoleService.findrole(userid);
        List<RoleAuth> roleAuthList = roleAuthService.findauths(userRole.getRoleid());
        Set<Auth> authset = new HashSet();

        for(RoleAuth roleAuth:roleAuthList){
            Auth auth = authService.findauth(roleAuth.getAuthid());
            Auth at = authService.findauth(auth.getAuthfatherid());
            //System.out.println(auth);
            authset.add(at);
        }
        return authset;
    }
    //获取首页二级菜单
    public List<Auth> navchild(Auth auth,Integer userid){
        List<Auth> authList = authService.findByFahter(auth.getAuthid());
        Map map = new HashMap();
        UserRole role = userRoleService.findrole(userid);
        map.put("roleid",role.getRoleid());

        for(int i = 0;i < authList.size();i++){
            Integer authid = authList.get(i).getAuthid();
            map.put("authid",authid);
            RoleAuth roleAuth = roleAuthService.findischeck(map);
            if(roleAuth.getIsauth() != 1){
                authList.remove(i);
            }
        }

        return authList;
    }
    //在员工角色页面显示角色
    public List<Role> getroles(Integer userid){
        Table<Role> roleTable = roleService.findall();
        List<Role> roleList = roleTable.getData();
        for(Role role:roleList){
            Map map = new HashMap();
            map.put("roleid",role.getRoleid());
            map.put("userid",userid);
            UserRole userRole = userRoleService.findchecked(map);
            if(userRole != null){
                role.setStatus(1);
            }
        }
        return roleList;
    }
    //在员工角色页面给员工赋予角色
    public String giverole(UserRole userRole){
        userRoleService.deluserrole(userRole);
        userRoleService.saveuserrole(userRole);
        return "success";
    }
}
