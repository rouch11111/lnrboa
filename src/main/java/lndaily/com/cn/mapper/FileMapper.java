package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.File;

import java.util.List;
import java.util.Map;

public interface FileMapper {

    public void savefile(File file);

    public List<File> findall(Map map);

    public int findallcount();

    public List<File> findcon(File file);

    public void delfile(File file);
}
