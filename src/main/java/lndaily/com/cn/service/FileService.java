package lndaily.com.cn.service;

import lndaily.com.cn.bean.File;
import lndaily.com.cn.bean.User;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    public void savefile(String path,String name,String range){
        File file = new File();
        file.setFilename(name);
        file.setFilepath(path);
        Integer userid = (Integer) session.getAttribute("userid");
        if(range.equals("2")){
            User user = userService.findById(userid);
            Integer deptid = user.getDeptid();
            file.setDept_id(deptid);
        }
        if(range.equals("1")){
            file.setIsgroup((short) 1);
        }
        file.setUser_id(userid);
        fileMapper.savefile(file);
    }

    public Table<File> findall(int page, int limit){
        Map map = new HashMap();
        map.put("page",(page-1) * limit);
        map.put("limit",limit);
        List<File> fileList = fileMapper.findall(map);
        int count = fileMapper.findallcount();
        Table<File> fileTable = new Table<>();
        fileTable.setCode(0);
        fileTable.setCount(count);
        fileTable.setData(fileList);
        return fileTable;
    }

    public Table<File> findcon(File file){
        int page = file.getPage();
        int newpage = (page - 1) * file.getLimit();
        file.setPage(newpage);
        List<File> fileList = fileMapper.findcon(file);
        Table<File> fileTable = new Table<>();
        fileTable.setCode(0);
        fileTable.setData(fileList);
        fileTable.setCount(10);
        return fileTable;
    }

    public String delfile(File file){
        Integer userid = (Integer) session.getAttribute("userid");

        if(!userid.equals(file.getUser_id())){
            return "errorman";
        }
        String filepath = file.getFilepath();
        java.io.File file1 = new java.io.File(filepath);
        if (file1.exists()){//文件是否存在
            file1.delete();//删除文件
        }
        fileMapper.delfile(file);
        return "success";
    }
}
