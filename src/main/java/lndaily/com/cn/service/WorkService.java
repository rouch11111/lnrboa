package lndaily.com.cn.service;

import lndaily.com.cn.bean.User;
import lndaily.com.cn.bean.Work;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.mapper.UserMapper;
import lndaily.com.cn.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkService {

    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;

    public String getday(int i){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        date = calendar.getTime();
        String tiem = sdf.format(date);
        return tiem;
    }

    public int getmonth(int i){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) - i;
        return month;
    }

    public int getyear(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public Table<Work> findall(int page, int limit){
        Map map = new HashMap();
        map.put("page",(page-1) * limit);
        map.put("limit",limit);
        List<Work> list = workMapper.findall(map);
        int count = workMapper.findallcount();
        for(Work work:list){
            Integer work_userid = work.getWork_userid();
            User user = userMapper.findById(work_userid);
            work.setUser(user);
        }
        Table<Work> workTable = new Table();

            workTable.setCode(0);
            workTable.setCount(count);
            workTable.setData(list);
            workTable.setMsg("successful");

        return workTable;
    }

    public Table<Work> findcon(Work work){
        int page = work.getPage();
        int npage = (page - 1) * work.getLimit();
        work.setPage(npage);
        String time = work.getTime();
        if(time != null && time != ""){
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
            String[] strings = time.split("~");
            try {
                Date starttime = sdf.parse(strings[0]);
                Date endtime = sdf.parse(strings[1]);
                Calendar   calendar = new GregorianCalendar();
                calendar.setTime(endtime);
                calendar.add(calendar.DATE,1);
                endtime=calendar.getTime();
                work.setStarttime(starttime);
                work.setEndtime(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Table<Work> workTable = new Table();
        List<Work> workList = workMapper.findcon(work);
        int count = workMapper.findconcount(work);
        if(workList != null){
            workTable.setCode(0);

            workTable.setMsg("successful");
            for(Work w:workList){
                Integer work_userid = w.getWork_userid();
                User user = userMapper.findById(work_userid);
                w.setUser(user);

            }
        }
        workTable.setCount(count);
        workTable.setData(workList);
        return workTable;
    }

    public Work findWork(Work work){
        return workMapper.findWork(work);
    }

    public String updateworkByWorkId(Work work){

        Work work1 = findWork(work);
        if(!work.getWork_userid().equals(work1.getWork_userid())){
            return "error";
        }

        Date worktime = work.getWorktime();
        Calendar   calendar = new GregorianCalendar();
        calendar.setTime(worktime);
        calendar.add(calendar.DATE,1);
        Date time = calendar.getTime();
        work.setWorktime(time);
        workMapper.updateworkByWorkId(work);

        return "success";
    }

    public String savework(Work work){

        if(work.getWorkname() == null || work.getWorkname().isEmpty()){
            return "namenull";
        }

        if(work.getWorktime() == null){
            return "timenull";
        }
        Date worktime = work.getWorktime();
        Calendar   calendar = new GregorianCalendar();
        calendar.setTime(worktime);
        calendar.add(calendar.DATE,1);
        Date time = calendar.getTime();
        work.setWorktime(time);
        workMapper.savework(work);
        return "success";
    }

    public String delwork(Work work){
        //workMapper.delwork(workid);
        Integer userid = (Integer) session.getAttribute("userid");
        if(!work.getWork_userid().equals(userid)){
            return "error";
        }
        Work work1 = workMapper.findWork(work);
        //定义文件路径
        String filePath = work1.getWorkfile();
        //这里因为我文件是相对路径 所以需要在路径前面加一个点
        File file = new File(filePath);
        //System.out.println(filePath);
        //System.out.println(file);
        if (file.exists()){//文件是否存在
            file.delete();//删除文件
        }
        workMapper.delwork(work.getWorkid());
        return "success";
    }

    public List sevenday(){
        List list = new ArrayList();
        for(int i=-6;i<1;i++){
            String date = getday(i);
            int count = workMapper.sevencount(date);
            list.add(count);
        }

        return list;
    }

    public List<Map> fivemonth(){

        List<Map> fivelist = new ArrayList<>();
        int j = 100;
        for(int i=-1;i<4;i++){
            int year = getyear();
            int month = getmonth(i);

            Map fivemap = new HashMap();
            Map map = new HashMap();
            map.put("year",year);
            map.put("month",month);
            int count = workMapper.fivecount(map);
            fivemap.put("name",month+"月份");
            fivemap.put("value",count);
            fivelist.add(fivemap);
            if(month == 1 && i < 3){
                j =  i;
                break;
            }
        }
        if(j != 100){
            for(int i = j; i<4; i++){
                int year = getyear()-1;
                int month = getmonth(i);
                Map fivemap = new HashMap();
                Map map = new HashMap();
                map.put("year",year);
                map.put("month",month);
                int count = workMapper.fivecount(map);
                fivemap.put("name",month+"月份");
                fivemap.put("value",count);
                fivelist.add(fivemap);
            }
        }

        return fivelist;
    }

}
