package lndaily.com.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Integer fileid;
    private String filename;
    private String filepath;
    private Integer dept_id;
    private short isgroup;
    private Integer user_id;

    //分页
    private int page;
    private int limit;
}
