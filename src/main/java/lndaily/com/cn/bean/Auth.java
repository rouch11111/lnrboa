package lndaily.com.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private Integer authid;
    private String authname;
    private Integer authfatherid;
    private String authurl;
    private String nameid;

    //是否选中
    private int status;
}
