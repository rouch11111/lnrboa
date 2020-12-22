package lndaily.com.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    private Integer workid;
    private String workname;
    private String workcontent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date worktime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endtime;

    private String workfile;
    private String workfilename;
    private Integer work_userid;

    private String time;

    //分页
    private int page;
    private int limit;

    //private int work_counts;

    private User user;
}
