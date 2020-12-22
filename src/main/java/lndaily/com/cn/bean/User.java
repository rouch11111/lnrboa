package lndaily.com.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userid;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private Integer deptid;
    private Dept dept;

    //分页
    private int page;
    private int limit;
}
