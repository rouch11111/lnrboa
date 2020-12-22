package lndaily.com.cn.mapper;

import lndaily.com.cn.bean.Work;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface WorkMapper {

    public List<Work> findall(Map map);

    public int findallcount();

    public List<Work> findcon(Work work);

    public int findconcount(Work work);

    public Work findWork(Work work);

    public void updateworkByWorkId(Work work);

    public void savework(Work work);

    public void delwork(Integer id);

    public int sevencount(String date);

    public int fivecount(Map map);
}
